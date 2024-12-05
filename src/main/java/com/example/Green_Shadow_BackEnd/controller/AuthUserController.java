package com.example.Green_Shadow_BackEnd.controller;


import com.example.Green_Shadow_BackEnd.dto.impl.UserDto;
import com.example.Green_Shadow_BackEnd.entity.impl.Role;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.secure.JWTAuthResponse;
import com.example.Green_Shadow_BackEnd.service.AuthService;
import com.example.Green_Shadow_BackEnd.service.UserService;
import com.example.Green_Shadow_BackEnd.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.Green_Shadow_BackEnd.secure.SignIn;
@RequestMapping("api/v1/auth/")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthUserController {
    private final UserService userService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping(value = "signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("role") Role role
    ) {
        try {
            UserDto buildUserDTO = new UserDto();
            buildUserDTO.setEmail(email);
            buildUserDTO.setRole(role);
            buildUserDTO.setPassword(passwordEncoder.encode(password));
            authService.signUp(buildUserDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "signin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authService.signIn(signIn));
    }


//    @PostMapping("refresh")
//    public ResponseEntity<JWTAuthResponse> refreshToken(@RequestParam ("existingToken") String existingToken) {
//        return ResponseEntity.ok(authService.refreshToken(existingToken));
//    }


}
