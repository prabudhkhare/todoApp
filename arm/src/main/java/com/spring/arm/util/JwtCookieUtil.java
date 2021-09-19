package com.spring.arm.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
@AllArgsConstructor
public class JwtCookieUtil {
    public static final String JWT_HEADER_STRING = "Authorization";
    private final JwtUtil jwtTokenUtil;

    public String getLoginCookie(String username){
        String cookie=JWT_HEADER_STRING+"="+jwtTokenUtil.generateToken(username)+";";
        String cookieExpire =getCookieExpiryString(Long.parseLong(jwtTokenUtil.getEXPIRY()));
        return cookie+cookieExpire+"SameSite=Strict;Path=/;";
    }

    public String getLogoutCookie(){
        String cookie=JWT_HEADER_STRING+"=deleted;";
        String cookieExpire =getCookieExpiryString(0L);
        return cookie+cookieExpire+"SameSite=Strict;Path=/;";
    }

    private String getCookieExpiryString(Long expireAfterMilliSeconds){
        Date expdate= new Date();
        expdate.setTime(expdate.getTime()+expireAfterMilliSeconds);
        DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", java.util.Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return "expires=" + df.format(expdate)+";";
    }
}
