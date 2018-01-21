package org.projetx.front.controller;

import org.projetx.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class AccountController {

	@RequestMapping(path = "/account")
	public String home(@ModelAttribute User user) {
		System.out.println(user);
		return "account";
	}

}
