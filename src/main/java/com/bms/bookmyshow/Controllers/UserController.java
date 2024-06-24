package com.bms.bookmyshow.Controllers;

import com.bms.bookmyshow.DTOs.SignUpRequestDTO;
import com.bms.bookmyshow.DTOs.SignUpResponseDTO;
import com.bms.bookmyshow.Models.ResponseStatus;
import com.bms.bookmyshow.Models.User;
import com.bms.bookmyshow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO){
        User user;
        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
        try {
            user = userService.signUp(signUpRequestDTO.getEmailId(),signUpRequestDTO.getPassword());
            signUpResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            signUpResponseDTO.setUserId(user.getId());
        } catch (Exception e){
            signUpResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
            signUpResponseDTO.setUserId(-1L);
        }
        return signUpResponseDTO;
    }
}
