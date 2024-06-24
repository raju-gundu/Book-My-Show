package com.bms.bookmyshow.Controllers;

import com.bms.bookmyshow.DTOs.BookMovieRequestDTO;
import com.bms.bookmyshow.DTOs.BookMovieResponseDTO;
import com.bms.bookmyshow.Models.Booking;
import com.bms.bookmyshow.Models.ResponseStatus;
import com.bms.bookmyshow.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookingController {
    private BookingService bookingService;

    @Autowired
    private BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    private BookMovieResponseDTO bookMovie(BookMovieRequestDTO requestDTO){
        BookMovieResponseDTO responseDTO = new BookMovieResponseDTO();
        try {
            Booking booking = bookingService.bookMovie(
                    requestDTO.getUserId(),
                    requestDTO.getShowId(),
                    requestDTO.getShowSeatId()
            );
            responseDTO.setBookingId(booking.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setAmount(booking.getPrice());
        } catch (Exception e){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDTO;
    }
}
