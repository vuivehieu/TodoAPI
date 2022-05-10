package com.example.todoapi.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TokenRefreshException {
    private String requestRefreshToken;
    private String error ;
    public TokenRefreshException(String requestRefreshToken, String s) {
        this.requestRefreshToken =requestRefreshToken;
        this.error = s;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleTokenRefreshException(TokenRefreshException ex) {
        return "error";
    }
}
