package com.example.Green_Shadow_BackEnd.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String profilePicToBase64(byte [] profilePic){
       return Base64.getEncoder().encodeToString(profilePic);
    }

}
