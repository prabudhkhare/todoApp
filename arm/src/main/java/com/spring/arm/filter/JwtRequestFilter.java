package com.spring.arm.filter;

import com.spring.arm.model.security.ArmUserDetails;
import com.spring.arm.service.ArmUserDetailsService;
import com.spring.arm.util.JwtCookieUtil;
import com.spring.arm.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Service
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);
    @Value("${app.allowed.static.folders}")
    private String allowedFolders;
    private final JwtUtil jwtUtil;
    private final ArmUserDetailsService armUserDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtil,ArmUserDetailsService armUserDetailsService){
        this.jwtUtil=jwtUtil;
        this.armUserDetailsService=armUserDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        if(path.contains("/logout")){
            return true;
        }
        String[] paths = Stream.of(allowedFolders.split(","))
                .map(p->"/"+p+"/")
                .toArray(String[]::new);
        boolean flag = false;
        for(String p: paths){
            if (path.contains(p)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        LOGGER.info(request.getRequestURI());
        String jwt =getJwtFromCookie(request);
        if(jwt == null && request.getParameter("token")!=null){
            jwt= URLDecoder.decode((String) request.getParameter("token"), StandardCharsets.UTF_8);
        }
        String id = null;
        try {
            if (jwt != null) {
                id = jwtUtil.extractUserId(jwt);
            }
            if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtUtil.validateToken(jwt, id)) {
                    ArmUserDetails userDetails;
                    try {
                        userDetails = (ArmUserDetails) armUserDetailsService.loadUserByUsername(id);
                    } catch (Exception e) {
                        throw new UsernameNotFoundException("Username not found in the database");
                    }
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }catch (Exception e){
            LOGGER.info(e.getMessage());
        }
        chain.doFilter(request, response);
    }

    private String getJwtFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String jwt = null;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (JwtCookieUtil.JWT_HEADER_STRING.equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }
        return jwt;
    }
}