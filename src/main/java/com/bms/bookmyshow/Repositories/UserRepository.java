package com.bms.bookmyshow.Repositories;

import com.bms.bookmyshow.Models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(Long userId); //Finding the user by user id

    Optional<User> findByEmail(String emailId);
    User saveUser(User user);
}
