package com.project.shopapp.Configurations;

import com.project.shopapp.MODELS.User;
import com.project.shopapp.Respository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
// user detail object
    private final UserRespository userRespository;
    @Bean
    public UserDetailsService userDetailsService() {
        return phoneNumber->
                     userRespository.findByPhoneNumber(phoneNumber)
                    .orElseThrow(()->
                            new UsernameNotFoundException("Cannot find user with phone number = "+phoneNumber));
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configture)
            throws Exception {
        return configture.getAuthenticationManager();
    }
}
