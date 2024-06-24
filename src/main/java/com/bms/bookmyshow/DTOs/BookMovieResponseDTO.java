package com.bms.bookmyshow.DTOs;

import com.bms.bookmyshow.Models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDTO {
    private int amount;
    private Long bookingId;
    private ResponseStatus responseStatus;
}
