package com.spring.arm.handler;

import com.spring.arm.util.JwtCookieUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@AllArgsConstructor
public class ArmLogoutHandler implements LogoutHandler {
    private final JwtCookieUtil jwtCookieUtil;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {
        response.setHeader("Set-Cookie",jwtCookieUtil.getLogoutCookie());
    }
}