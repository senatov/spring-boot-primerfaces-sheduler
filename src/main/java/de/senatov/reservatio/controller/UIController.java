package de.senatov.reservatio.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class UIController {

    @GetMapping("/")
    public String requestWithError(@PathVariable("firstValue") String firstValue) {

        return "/ui/reservation";
    }



    @GetMapping("/{firstValue}")
    public String requestOneWithError(@PathVariable("firstValue") String firstValue) {

        return "/ui/reservation";
    }



    @GetMapping("/ui/reservation/{page}")
    public String requesWOError(@PathVariable("page") String page) {

        return "/ui/reservation";
    }



    @GetMapping("/ui/{secondValue:.+}")
    public String requestWithRegex(@PathVariable("secondValue") String secondValue) {

        return "404.xhtml";
    }



    @GetMapping("/ui/{secondValue}/")
    public String requestWithSlash(@PathVariable("secondValue") String secondValue) {

        return "/ui/reservation";
    }

}