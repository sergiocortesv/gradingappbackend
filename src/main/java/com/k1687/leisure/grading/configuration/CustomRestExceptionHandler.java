package com.k1687.leisure.grading.configuration;

import com.k1687.leisure.grading.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request){
        final List<String> errors = new ArrayList<String>();
        List<String> fieldErrors = ex.getBindingResult().getFieldErrors().stream().map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.toList());
        errors.addAll(fieldErrors);
        List<String> globalErrors = ex.getBindingResult().getGlobalErrors().stream().map(e -> e.getObjectName() + ":" + e.getDefaultMessage())
                .collect(Collectors.toList());
        errors.addAll(globalErrors);

        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(HttpStatus.BAD_REQUEST.name());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage("Validation Error");
        errorResponse.setDetailedMessage(errors.stream().collect(Collectors.joining(",")));
        return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

}
