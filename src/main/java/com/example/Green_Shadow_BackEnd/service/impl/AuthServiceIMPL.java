package com.example.Green_Shadow_BackEnd.service.impl;

import com.example.Green_Shadow_BackEnd.dao.UserDao;
import com.example.Green_Shadow_BackEnd.dto.impl.UserDto;
import com.example.Green_Shadow_BackEnd.entity.impl.UserEntity;
import com.example.Green_Shadow_BackEnd.secure.JWTAuthResponse;
import com.example.Green_Shadow_BackEnd.secure.SignIn;
import com.example.Green_Shadow_BackEnd.service.AuthService;
import com.example.Green_Shadow_BackEnd.service.JWTService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {
    private final UserDao userDao;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var user = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken( user);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDto userDTO) {
      //Save user
        UserEntity savedUser = userDao.save(mapping.toUserEntity(userDTO));
      //Generate the token and return it
        var generatedToken = jwtService.generateToken(savedUser);
       return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
//        //extract user name
//        var userName = jwtService.extractUserName(accessToken);
//        //check the user availability in the DB
//        var findUser = userDao.findByEmail(userName)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        var refreshToken = jwtService.refreshToken((UserDetails) findUser);
//        return JWTAuthResponse.builder().token(refreshToken).build();

        return null;
    }
}
