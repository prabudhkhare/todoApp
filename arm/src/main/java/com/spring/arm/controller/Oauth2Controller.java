package com.spring.arm.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.OAuth2Utils;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;


@Controller
@RequestMapping("/oauth2")
public class Oauth2Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Oauth2Controller.class);
    @Value("${spring.security.oauth2.client.registration.facebook.client-id}")
    private String FACEBOOK_APP_ID;
    @Value("${spring.security.oauth2.client.registration.facebook.client-secret}")
    private String FACEBOOK_APP_SECRET;
    @Value("${spring.security.oauth2.client.registration.facebook.redirect-uri}")
    private String FACEBOOK_REDIRECT_URI;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_APP_ID;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String GOOGLE_APP_SECRET;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String GOOGLE_REDIRECT_URI;

    @GetMapping("/doLoginFacebook")
    public String doLoginFacebook(@RequestParam(value = "code",required = false) final String code,
                          @RequestParam(value = "error",required = false) final String error){
        if(error!=null){
            return "redirect:/login/sign-in";
        }
        try {
            FacebookConnectionFactory factory = new FacebookConnectionFactory(FACEBOOK_APP_ID, FACEBOOK_APP_SECRET);
            OAuth2Operations operations = factory.getOAuthOperations();
            AccessGrant accessToken = operations.exchangeForAccess(code, FACEBOOK_REDIRECT_URI, null);
            Connection<Facebook> connection = factory.createConnection(accessToken);
            Facebook facebook = connection.getApi();
            String[] fields = {"id", "name", "email"};
            User userProfile = facebook.fetchObject("me", User.class, fields);
            LOGGER.info("userIn=" + userProfile.getEmail());
            return "redirect:/index";
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return "redirect:/login/sign-in";
    }

    @GetMapping("/doLoginGoogle")
    public String doLoginGoogle(@RequestParam(value = "code",required = false) final String code,
                                @RequestParam(value = "error",required = false) final String error, OAuth2AuthenticationToken authentication){
//        if(error!=null){
//            return "redirect:/login/sign-in";
//        }
//        try {
//            final GoogleCredentials googleCredentials = serviceAccountCredentials
//                    .createScoped(Collections.singletonList(StorageScopes.DEVSTORAGE_FULL_CONTROL));
//            HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(googleCredentials);
//
//
//        }catch (Exception e){
//            LOGGER.error(e.getMessage());
//        }
        return "redirect:/login/sign-in";
    }
}
