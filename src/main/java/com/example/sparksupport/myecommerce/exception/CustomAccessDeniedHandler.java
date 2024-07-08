package com.example.sparksupport.myecommerce.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String requestedUrl = request.getRequestURI();

        logger.error("Access Denied: {}", accessDeniedException.getMessage());

        String errorMessage = "Access to " + requestedUrl + " is forbidden. " +
                "Only users with admin privileges are allowed to access this resource.";

        logger.error("Response Error Message: {}", errorMessage);

        response.sendError(HttpServletResponse.SC_FORBIDDEN, errorMessage);
    }
}
