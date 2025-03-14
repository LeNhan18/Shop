package com.project.shopapp.Service.IMP;

import com.project.shopapp.DTOS.UserDTO;
import com.project.shopapp.Exception.DataNotFoundException;
import com.project.shopapp.MODELS.User;

public interface IMPUserService {
    User createUser(UserDTO userDTO);

    String Login(String username, String password) throws  Exception;
}
