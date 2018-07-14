package de.senatov.drill.jsf_springboot.controller;


import de.senatov.drill.jsf_springboot.annotations.Loggable;
import org.slf4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Controller
@ControllerAdvice
public class CustomErrorWebExceptionHandler {

    @Loggable
    private Logger log;


    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {

        // If exception has a ResponseStatus annotation then use its response code
        ResponseStatus responseStatusAnnotation = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);

        return buildModelAndViewErrorPage(request, response, ex,
                Optional.ofNullable(responseStatusAnnotation).map(ResponseStatus::value).orElse(INTERNAL_SERVER_ERROR));
    }


    @RequestMapping("*")
    public ModelAndView fallbackHandler(HttpServletRequest request, HttpServletResponse response) {

        return buildModelAndViewErrorPage(request, response, null, NOT_FOUND);
    }


    private ModelAndView buildModelAndViewErrorPage(HttpServletRequest request, HttpServletResponse response, Exception ex,
            HttpStatus httpStatus) {

        response.setStatus(httpStatus.value());

        ModelAndView mav = new ModelAndView("error");
        if (ex != null) {
            mav.addObject("title", ex);
        }

        mav.addObject("content", request.getRequestURL());
        return mav;
    }

}
