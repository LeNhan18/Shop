package com.project.shopapp.Service.IMP;

import com.project.shopapp.DTOS.UserDTO;
import com.project.shopapp.MODELS.User;

public interface IMPUserService {
    User createUser(UserDTO userDTO);

    User Login(String username, String password);
}
