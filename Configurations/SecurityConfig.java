package com.project.shopapp.Configurations;

import com.project.shopapp.MODELS.User;
import com.project.shopapp.Respository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
// user detail object
    private final UserRespository userRespository;
    @Bean
    public UserDetailsService userDetailsService() {
        return phoneNumber->{
            User exitsPhoneNumber =userRespository.findByPhoneNumber(phoneNumber)
                    .orElseThrow(()-> new UsernameNotFoundException("Cannot find user with phone number = "+phoneNumber));
            return exitsPhoneNumber;

        }

    }
}
