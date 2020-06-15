package de.senatov.reservatio.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class UIController {

	@GetMapping("/{firstValue}")
	public String requestWithError(
			@PathVariable("firstValue")
					String firstValue) {

		return firstValue;
	}

    @GetMapping("/{firstValue}/{secondValue}")
    public String requestWithError(
            @PathVariable("firstValue")
                    String firstValue,
            @PathVariable("secondValue")
                    String secondValue) {

        return firstValue + " - " + secondValue;
    }



    @GetMapping("/{firstValue}/{secondValue:.+}")
    public String requestWithRegex(
            @PathVariable("firstValue")
                    String firstValue,
            @PathVariable("secondValue")
                    String secondValue) {

        return firstValue + " - " + secondValue;
    }



    @GetMapping("/{firstValue}/{secondValue}/")
    public String requestWithSlash(
            @PathVariable("firstValue")
                    String firstValue,
            @PathVariable("secondValue")
                    String secondValue) {

        return firstValue + " - " + secondValue;
    }

}