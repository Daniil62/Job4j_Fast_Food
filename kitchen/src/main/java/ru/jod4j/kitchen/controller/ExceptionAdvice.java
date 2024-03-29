package ru.jod4j.kitchen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionAdvice {

    private final ObjectMapper objectMapper;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(KitchenController.class.getSimpleName());

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    public void dataIntegrityExceptionHandler(Exception e,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws IOException {
        handleError(e, request, response, HttpStatus.CONFLICT);
    }

    public void handleError(Exception e, HttpServletRequest request,
                            HttpServletResponse response, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("time", LocalDateTime.now());
                put("request type", request.getMethod());
                put("request URI", request.getRequestURI());
                put("message", e.getMessage());
                put("error type", e.getClass());
            }
        }));
        LOGGER.error(e.getLocalizedMessage());
    }
}