package javaproject.BusinessApplication.web.controllers;

import javaproject.BusinessApplication.exeptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler
    public ModelAndView getEntityNotFoundException(EntityNotFoundException ex) {
        return getExceptionMessage(ex);
    }

    @ExceptionHandler
    public ModelAndView getEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        return getExceptionMessage(ex);
    }

    @ExceptionHandler
    public ModelAndView getCustomTwitterException(CustomTwitterException ex) {
        return getExceptionMessage(ex);
    }

    @ExceptionHandler
    public ModelAndView getEntityIsNotMerchantException(EntityIsNotMerchantException ex) {
        return getExceptionMessage(ex);
    }

    @ExceptionHandler
    public ModelAndView getNotEnoughProductsExceptions(NotEnoughProductsExceptions ex) {
        return getExceptionMessage(ex);
    }

    @ExceptionHandler
    public ModelAndView getDateException(DateException ex) {
        return getExceptionMessage(ex);
    }

    @ExceptionHandler
    public ModelAndView getNegativePriceException(NegativePriceException ex) {
        return getExceptionMessage(ex);
    }

    @ExceptionHandler
    public ModelAndView getNegativeQuantityException(NegativeQuantityException ex) {
        return getExceptionMessage(ex);
    }

    private ModelAndView getExceptionMessage(RuntimeException ex){
        return new ModelAndView("error", "errors", List.of(ex.getMessage()));
    }

}
