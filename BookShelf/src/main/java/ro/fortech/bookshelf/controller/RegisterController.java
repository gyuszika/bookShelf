package ro.fortech.bookshelf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login/register")
public class RegisterController {

	@RequestMapping("")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("book/register");
		
		return modelAndView;
	}
}

