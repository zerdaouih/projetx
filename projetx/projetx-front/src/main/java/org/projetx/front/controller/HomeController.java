package org.projetx.front.controller;

import org.apache.log4j.Logger;
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

	/**
	 * Lgger
	 */
	Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private MyServicePOC myService;
	@Autowired
	private LoginService loginService;
	// @Autowired
	// DataSource dataSource;
	@Autowired
	private UserRepository userRepository;

	/**
	 * this method handle GET requests mapped to "/"
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String home(Model model) {
		logger.info(myService.message());
		model.addAttribute("msg", myService.message());
		model.addAttribute("message", "3awd lkerrek");
		return "index";
	}

	/**
	 *  * this method handle GET requests mapped to "/login"
	 * @param model
	 * @param j_username
	 * @param j_password
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public String login(Model model, @RequestParam String j_username, @RequestParam String j_password) {
		if (loginService.login(j_username, j_password)) {
			logger.info("### login OK ###");
//			User user = new User(j_username, j_password);
			User user = userRepository.findByUserName(j_username);
			Iterable<User> userList = userRepository.findAll();
			logger.info("## userList ##");
			logger.info(userList);
			model.addAttribute("user", user);
			return "welcom";
		} else {
			model.addAttribute("error", true);
			return "index";
		}
	}
}
