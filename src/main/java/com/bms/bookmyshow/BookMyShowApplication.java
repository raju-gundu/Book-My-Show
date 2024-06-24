package com.bms.bookmyshow;

import com.bms.bookmyshow.Controllers.UserController;
import com.bms.bookmyshow.DTOs.SignUpRequestDTO;
import com.bms.bookmyshow.DTOs.SignUpResponseDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {
    private UserController userController;
    public static void main(String[] args) {

        SpringApplication.run(BookMyShowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDTO requestDTO = new SignUpRequestDTO();
        requestDTO.setEmailId("rajugundu849@gamil.com");
        requestDTO.setPassword("adc");
        SignUpResponseDTO responseDTO = userController.signUp(requestDTO);


    }
}
