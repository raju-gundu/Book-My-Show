package com.bms.bookmyshow.DTOs;

import com.bms.bookmyshow.Models.Booking;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDTO {
    private Long userId;
    private Long showId;
    private List<Long> showSeatId;


}
