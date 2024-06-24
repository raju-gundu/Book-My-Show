package com.bms.bookmyshow.Services;

import com.bms.bookmyshow.Exceptions.InvalidUserException;
import com.bms.bookmyshow.Models.User;
import com.bms.bookmyshow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User signUp(String emailId, String password){
        Optional<User> optionalUser = userRepository.findByEmail(emailId);
        if (optionalUser.isPresent()){
            logIn(emailId,password);
        }
        User user = new User();
        user.setEmail(emailId);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.saveUser(user);
    }
    public boolean logIn(String emailId,String password){
        Optional<User> optionalUser = userRepository.findByEmail(emailId);
        String dbPassword = optionalUser.get().getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder.matches(password,dbPassword);
    }
}
