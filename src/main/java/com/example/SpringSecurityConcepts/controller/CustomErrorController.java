package com.example.SpringSecurityConcepts.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        String errorMessage = "Some error occurred";
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            errorMessage = statusCode + " " + HttpStatus.valueOf(statusCode).getReasonPhrase();
        }

        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
    }

}
