package de.senatov.reservatio.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ReservationController {

	@GetMapping("/reservation")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

		model.addAttribute("name", name);
		return "greeting Reservation!";
	}

}