package com.project.shopapp.Service;

import com.project.shopapp.DTOS.UserDTO;
import com.project.shopapp.Exception.DataNotFoundException;
import com.project.shopapp.MODELS.Role;
import com.project.shopapp.MODELS.User;
import com.project.shopapp.Respository.RoleRespository;
import com.project.shopapp.Respository.UserRespository;
import com.project.shopapp.Service.IMP.IMPUserService;
import com.project.shopapp.Utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //Xác định lớp này la lớp dịch vuj trong spring
@RequiredArgsConstructor // Auto create các Contructor với các final field
public class UserService implements IMPUserService { //Lớp trien khai interface IMPUserService
    private final RoleRespository roleRespository; //Kho lưu truu role duoc tiem qua constructor
    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;//
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    @SneakyThrows // ẩn việc xử ly ngoại lệ auto throw neeus có
    @Override
    public User createUser(UserDTO userDTO) {
        // register user
        String phoneNumber = userDTO.getPhoneNumber();
        if(userRespository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        User newUser = User.builder()
                .fullname(userDTO.getFullname())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .dateOfBirth(new java.sql.Date(userDTO.getDateOfBirth().getTime()))
                .facebookAccount(userDTO.getFacebookAccountId())
                .googleAccount(userDTO.getGoogleAccountId())
                .build();
        Role role = roleRespository.findById(userDTO.getRoleId())
                .orElseThrow(()->new DataNotFoundException("Role not found"));
        newUser.setRole(role);
        if(userDTO.getFacebookAccountId() ==0 && userDTO.getGoogleAccountId() ==0){
            String password = userDTO.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            newUser.setPassword(encodedPassword);
        }
        return userRespository.save(newUser);
    }

    @Override
    public String Login(String phoneNumber, String password) throws Exception{
        Optional<User> optionalUser = userRespository.findByPhoneNumber(phoneNumber);
        if(optionalUser.isEmpty()){
            throw new DataNotFoundException("Invalid phoneNumber/ password");
        }
        User existingUser = optionalUser.get();
        if( existingUser.getGoogleAccount()==0 && existingUser.getFacebookAccount() ==0 ){
            if(!passwordEncoder.matches(password,existingUser.getPassword())){
                throw new Exception("Wrong phone number or password");
            }
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(phoneNumber,password);
        //authencation with java spring security
        authenticationManager.authenticate(authenticationToken);
        return jwtUtils.generateToken(existingUser); //Muon tra ve JWT ?
    }
}
