package com.bms.bookmyshow.Services;

import com.bms.bookmyshow.Exceptions.InvalidShowException;
import com.bms.bookmyshow.Exceptions.InvalidUserException;
import com.bms.bookmyshow.Exceptions.ShowSeatNotAvailableException;
import com.bms.bookmyshow.Models.*;
import com.bms.bookmyshow.Repositories.ShowRepository;
import com.bms.bookmyshow.Repositories.ShowSeatRepository;
import com.bms.bookmyshow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository,PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) throws InvalidUserException, InvalidShowException, ShowSeatNotAvailableException {
//        Steps:
//        1. Get user with userid
//        2. Get show with showid
//        3. Get showseats with show seat id
//        --------- Take a Lock --------------
//        4. Check if seats are available or not
//        5. if not throw an exception
//        6. if yes mark the seat status as blocked
//        7. Save the updated status to DB
//        --------- Release a Lock ----------------
//        8. Create booking object with pending status
//        9. Return the booking object


        //        1. Get user with userid
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new InvalidUserException("Invalid User");
        }

        User user = optionalUser.get();

        //        2. Get show with showid
        Optional<Show> optionalShow = showRepository.findById(showId);
        if (optionalShow.isEmpty()){
            throw new InvalidShowException("Invalid Show");
        }

        Show show = optionalShow.get();
        //        3. Get showseats with show seat id
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        //        4. Check if seats are available or not
        for (ShowSeat showSeat : showSeats){
            if (!showSeat.getStatus().equals(SeatStatus.AVAILABLE)){
                throw new ShowSeatNotAvailableException("ShowSeat not Available");
            }
        }

        //        6. if yes mark the seat status as blocked
        List<ShowSeat> finalShowSeats = new ArrayList<>();
        for (ShowSeat showSeat : showSeats){
            showSeat.setStatus(SeatStatus.BLOCKED);
            //        7. Save the updated status to DB
            finalShowSeats.add(showSeatRepository.save(showSeat));
        }

        //        8. Create booking object with pending status
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setUser(user);
        booking.setTimeOfBooking(new Date());
        booking.setShow(show);
        booking.setSeats(finalShowSeats);
        booking.setPayments(new ArrayList<>());
        booking.setPrice(priceCalculatorService.calculatePrice(show,finalShowSeats));

        return booking;

    }
}
