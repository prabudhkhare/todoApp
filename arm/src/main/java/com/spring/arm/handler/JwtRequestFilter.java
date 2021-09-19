package com.spring.arm.handler;

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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);
    private final JwtUtil jwtUtil;
    private final ArmUserDetailsService armUserDetailsService;
    @Value("${server.servlet.context-path}")
    private String root;


    public JwtRequestFilter(JwtUtil jwtUtil,ArmUserDetailsService armUserDetailsService){
        this.jwtUtil=jwtUtil;
        this.armUserDetailsService=armUserDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        List<String> paths = Stream.of("/css", "/js", "/bootstrap")
                .map(s -> this.root + s)
                .collect(Collectors.toList());
        boolean flag = false;
        for(String p: paths){
            if (path.startsWith(p)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String jwt=null;
        Cookie[] cookies = request.getCookies();
        if(request.getParameter("token")!=null){
            jwt= URLDecoder.decode((String) request.getParameter("token"), StandardCharsets.UTF_8);
        }else if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (JwtCookieUtil.JWT_HEADER_STRING.equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }
        String email = null;
        LOGGER.info(request.getRequestURI());
        try {
            if (jwt != null) {
                email = jwtUtil.extractUsername(jwt);
            }
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtUtil.validateToken(jwt, email)) {
                    ArmUserDetails userDetails;
                    try {
                        userDetails = (ArmUserDetails) armUserDetailsService.loadUserByUsername(email);
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
            response.sendRedirect("/arm/index");
        }
        chain.doFilter(request, response);
    }

}