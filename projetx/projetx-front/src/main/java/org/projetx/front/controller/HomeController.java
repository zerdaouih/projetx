package org.projetx.front.controller;

import org.projetx.back.service.LoginService;
import org.projetx.back.service.MyServicePOC;
import org.projetx.model.User;
import org.projetx.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = "user", types = { User.class })
public class HomeController {

	@Autowired
	private MyServicePOC myService;
	@Autowired
	private LoginService loginService;
	// @Autowired
	// DataSource dataSource;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String home(Model model) {
		System.out.println(myService.message());
		model.addAttribute("msg", myService.message());
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public String login(Model model, @RequestParam String j_username, @RequestParam String j_password) {
		if (loginService.login(j_username, j_password)) {
			System.out.println("### login OK ###");
			User user = new User(j_username, j_password);
			// User user = userRepository.findByUserName(j_username);
			Iterable<User> userList = userRepository.findAll();
			System.out.println("## userList ##");
			System.out.println(userList);
			model.addAttribute("user", user);
			return "welcom";
		} else {
			model.addAttribute("error", true);
			return "index";
		}
	}
}
