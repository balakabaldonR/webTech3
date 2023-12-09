package com.es.phoneshop.web.controller.pages;

import com.es.core.entity.phone.PhoneNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PhoneNotFoundException.class)
    public ModelAndView handleException(PhoneNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("errorPages/notFoundPhone");
        modelAndView.addObject("errorMessage", exception.getErrorMessage());
        return modelAndView;
    }
}
