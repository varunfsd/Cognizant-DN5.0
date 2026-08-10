package com.varun.SpringBootDemoJpa.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler
        implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauth2User =
                (OAuth2User) authentication.getPrincipal();

        // Get GitHub ID safely
        Object githubIdObject =
                oauth2User.getAttribute("id");

        String githubId =
                String.valueOf(githubIdObject);

        String username =
                oauth2User.getAttribute("login");

        String email =
                oauth2User.getAttribute("email");

        System.out.println("GitHub ID: " + githubId);
        System.out.println("GitHub Username: " + username);
        System.out.println("GitHub Email: " + email);

        response.setContentType("application/json");

        response.getWriter().write("""
                {
                    "message": "GitHub login successful",
                    "githubId": "%s",
                    "username": "%s",
                    "email": "%s"
                }
                """.formatted(
                githubId,
                username,
                email
        ));
    }
}