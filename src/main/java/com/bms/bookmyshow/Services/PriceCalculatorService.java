package com.bms.bookmyshow.Services;

import com.bms.bookmyshow.Models.Show;
import com.bms.bookmyshow.Models.ShowSeat;
import com.bms.bookmyshow.Models.ShowSeatType;
import com.bms.bookmyshow.Repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(Show show, List<ShowSeat> showSeats){
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
        int amount = 0;
        for (ShowSeat showSeat : showSeats){
            for (ShowSeatType showSeatType : showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;

    }
}
