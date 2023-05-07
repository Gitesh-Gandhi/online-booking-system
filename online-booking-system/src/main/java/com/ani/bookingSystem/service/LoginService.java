package com.ani.bookingSystem.service;

import com.ani.bookingSystem.dto.ForgotPasswordDto;
import com.ani.bookingSystem.dto.LoginResponseDto;
import com.ani.bookingSystem.dto.UserCreateDto;
import com.ani.bookingSystem.dto.LoginDto;

public interface LoginService {
    
    Integer createUser(UserCreateDto user);

    String loginUser (LoginDto dto) ;

    String forgotPassword(ForgotPasswordDto dto);

    LoginResponseDto loginUserForResponse(LoginDto dto);
}
