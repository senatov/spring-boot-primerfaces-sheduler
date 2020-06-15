package de.senatov.reservatio.controller;



import de.senatov.reservatio.db.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Controller
public class UIController {

	@GetMapping("/reservation")
	public String uigreeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

		model.addAttribute("name", name);
		return "greeting Reservation!";
	}

	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public String uisubmit(@ModelAttribute("company") final User user, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			return "error";
		}
		model.addAttribute("name", user.getUsername());
		model.addAttribute("id", user.getId());
		//companyMap.put(company.getId(), company);
		return "companyView";
	}

	@GetMapping("/ui/reservation")
	public String sgreeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

		model.addAttribute("name", name);
		return "greeting Reservation!";
	}

	@RequestMapping(value = "/ui/reservation", method = RequestMethod.POST)
	public String ssubmit(@ModelAttribute("company") final User user, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			return "error";
		}
		model.addAttribute("name", user.getUsername());
		model.addAttribute("id", user.getId());
		//companyMap.put(company.getId(), company);
		return "companyView";
	}

}