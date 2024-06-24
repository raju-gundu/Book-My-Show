package com.bms.bookmyshow.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String emailId;
    private String password;
}
