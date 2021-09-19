package com.spring.arm.handler;

import com.spring.arm.jpa.entity.User;
import com.spring.arm.model.security.Provider;
import com.spring.arm.service.LoginService;
import com.spring.arm.util.JwtCookieUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger LOGGER= LoggerFactory.getLogger(OAuth2AuthenticationSuccessHandler.class);
    private final LoginService loginService;
    private final JwtCookieUtil jwtCookieUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        try {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            String provider = oauthToken.getAuthorizedClientRegistrationId();
            OAuth2User oauthUser = (OAuth2User) oauthToken.getPrincipal();
            Optional<User> user = Optional.empty();
            if (Provider.FACEBOOK.name().equalsIgnoreCase(provider)) {
                user = loginService.getUserById(oauthUser.getAttribute("id"));
            } else if (Provider.GOOGLE.name().equalsIgnoreCase(provider)) {
                user = loginService.getUserById(oauthUser.getAttribute("email"));
            }
            User user1=null;
            if (user.isPresent()) {
                 user1= user.get();
                user1.setLastLogin(Date.from(Instant.now()));
                loginService.updateUserDetails(user1);
            } else {
                 user1 = new User();
                if (Provider.FACEBOOK.name().equalsIgnoreCase(provider)) {
                    user1.setId(oauthUser.getAttribute("id"));
                } else if (Provider.GOOGLE.name().equalsIgnoreCase(provider)) {
                    user1.setId(oauthUser.getAttribute("email"));
                }
                user1.setUsername(oauthUser.getAttribute("name"));
                user1.setEmailId(oauthUser.getAttribute("email"));
                user1.setProvider(Provider.valueOf(provider.toUpperCase()));
                loginService.saveNewUser(user1);
            }
            httpServletRequest.logout();
            httpServletResponse.setHeader("Set-Cookie",jwtCookieUtil.getLoginCookie(user1.getId()));
            httpServletResponse.sendRedirect("/arm/index?token="+ URLEncoder.encode(jwtCookieUtil.getCookie(user1.getId()), StandardCharsets.UTF_8));
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            httpServletResponse.sendRedirect("/arm/login/sign-in");
        }
    }
}
