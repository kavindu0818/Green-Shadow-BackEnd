package com.example.Green_Shadow_BackEnd.service;


import com.example.Green_Shadow_BackEnd.dto.impl.UserDto;
import com.example.Green_Shadow_BackEnd.secure.JWTAuthResponse;
import com.example.Green_Shadow_BackEnd.secure.SignIn;

public interface AuthService {
   JWTAuthResponse signIn(SignIn signIn);
   JWTAuthResponse signUp(UserDto userDTO);
   JWTAuthResponse refreshToken(String accessToken);
}
