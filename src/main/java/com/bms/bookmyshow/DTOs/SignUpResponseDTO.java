package com.bms.bookmyshow.DTOs;

import com.bms.bookmyshow.Models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {
    private ResponseStatus responseStatus;
    private Long userId;
}
