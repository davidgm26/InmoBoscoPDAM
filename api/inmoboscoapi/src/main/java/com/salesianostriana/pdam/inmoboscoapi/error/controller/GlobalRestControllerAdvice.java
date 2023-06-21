package com.salesianostriana.pdam.inmoboscoapi.error.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.pdam.inmoboscoapi.error.impl.ApiErrorImpl;
import com.salesianostriana.pdam.inmoboscoapi.error.impl.ApiValidationSubError;
import com.salesianostriana.pdam.inmoboscoapi.exception.*;
import com.salesianostriana.pdam.inmoboscoapi.security.errorhandling.JwtTokenException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header("WWW-Authenticate", "Bearer")
                .body(ErrorMessage.of(HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getRequestURI()));

    }

    @ExceptionHandler({SameUserNameException.class})
    public ResponseEntity<?> handleSameUsernameException(SameUserNameException exception, WebRequest request) {
        return buildApiError(exception.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PropertyNotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(PropertyNotFoundException exception, WebRequest request) {
        return buildApiError(exception.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorMessage.of(HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getRequestURI()));

    }

    @ExceptionHandler({JwtTokenException.class})
    public ResponseEntity<?> handleTokenException(JwtTokenException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorMessage.of(HttpStatus.FORBIDDEN, ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<?> handleUserNotExistsException(UsernameNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.of(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request.getRequestURI()
                ));
    }
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.of(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler({PropertyNotFoundInFavouriteListException.class})
    public ResponseEntity<?> handlePropertyNotFoundInFavouriteListException(PropertyNotFoundInFavouriteListException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request.getRequestURI()
                ));
    }
    @ExceptionHandler({PhoneNumberInUseException.class})
    public ResponseEntity<?> handlePhoneNumberInUseException(PhoneNumberInUseException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler({UsernameInUseException.class})
    public ResponseEntity<?> handleUsernameInUseException(UsernameInUseException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request.getRequestURI()
                ));
    }
    @ExceptionHandler({EmailInUseException.class})
    public ResponseEntity<?> handleEmailInUseException(EmailInUseException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler({DniInUseException.class})
    public ResponseEntity<?> handleDniInUseException(DniInUseException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler({PropertyAlredyInListException.class})
    public ResponseEntity<?> handlePropertyAlredyInListException(PropertyAlredyInListException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler({UserHavePropertiesException.class})
    public ResponseEntity<?> handleUserHavePropertiesException(UserHavePropertiesException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(
                        HttpStatus.CONFLICT,
                        ex.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorImpl.builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message("Constraint Validation error. Please check the sublist.")
                                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                .subErrors(exception.getConstraintViolations().stream()
                                        .map(v -> {
                                            return ApiValidationSubError.builder()
                                                    .message(v.getMessage())
                                                    .rejectedValue(v.getInvalidValue())
                                                    .object(v.getRootBean().getClass().getSimpleName())
                                                    .field(((PathImpl) v.getPropertyPath()).getLeafNode().asString())
                                                    .build();
                                        })
                                        .collect(Collectors.toList())
                                )
                                .build()
                );
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiErrorWithSubErrors("Validation error. Please check the sublist.", request, status, ex.getAllErrors());
    }

    private final ResponseEntity<Object> buildApiError(String msg, WebRequest request, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(ApiErrorImpl.builder()
                        .status(status)
                        .message(msg)
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiError(ex.getMessage(), request, status);
    }

    private final ResponseEntity<Object> buildApiErrorWithSubErrors(String message, WebRequest request, HttpStatus status, List<ObjectError> subErrors) {
        return ResponseEntity
                .status(status)
                .body(
                        ApiErrorImpl.builder()
                                .status(status)
                                .message(message)
                                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                .subErrors(subErrors.stream()
                                        .map(ApiValidationSubError::fromObjectError)
                                        .collect(Collectors.toList())
                                )
                                .build()
                );

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class ErrorMessage {

        private HttpStatus status;
        private String message, path;

        @Builder.Default
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
        private LocalDateTime dateTime = LocalDateTime.now();

        public static ErrorMessage of(HttpStatus status, String message, String path) {
            return ErrorMessage.builder()
                    .status(status)
                    .message(message)
                    .path(path)
                    .build();
        }

    }

}