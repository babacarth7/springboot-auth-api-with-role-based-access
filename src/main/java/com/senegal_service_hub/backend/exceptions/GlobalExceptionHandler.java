package com.senegal_service_hub.backend.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        logger.error("Exception handled: ", exception);

        return switch (exception) {
            case BadCredentialsException badCredentialsException -> handleBadCredentialsException(
                    badCredentialsException);
            case AccountStatusException accountStatusException -> handleAccountStatusException(
                    accountStatusException);
            case AccessDeniedException accessDeniedException -> handleAccessDeniedException(
                    accessDeniedException);
            case SignatureException signatureException -> handleSignatureException(
                    signatureException);
            case ExpiredJwtException expiredJwtException -> handleExpiredJwtException(
                    expiredJwtException);
            default -> handleGenericException(exception);
        };
    }

    private ProblemDetail handleBadCredentialsException(
            BadCredentialsException exception) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                exception.getMessage());
        errorDetail.setProperty("description",
                "The email or the password is incorrect");
        return errorDetail;
    }

    private ProblemDetail handleAccountStatusException(
            AccountStatusException exception) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(403),
                exception.getMessage());
        errorDetail.setProperty("description", "The account is locked");
        return errorDetail;
    }

    private ProblemDetail handleAccessDeniedException(
            AccessDeniedException exception) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(403),
                exception.getMessage());
        errorDetail.setProperty("description",
                "You are not authorized to access this resource");
        return errorDetail;
    }

    private ProblemDetail handleSignatureException(
            SignatureException exception) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(403),
                exception.getMessage());
        errorDetail.setProperty("description",
                "The JWT signature is invalid");
        return errorDetail;
    }

    private ProblemDetail handleExpiredJwtException(
            ExpiredJwtException exception) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(403),
                exception.getMessage());
        errorDetail.setProperty("description",
                "The JWT token is expired");
        return errorDetail;
    }

    private ProblemDetail handleGenericException(Exception exception) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(500),
                exception.getMessage());
        errorDetail.setProperty("description", "Internal server error");
        return errorDetail;
    }
}
