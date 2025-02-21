package com.project.shopapp.Respository;

import com.project.shopapp.MODELS.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User,Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);
    //SELECT * FROM users WHERE phone_number= ?
}
