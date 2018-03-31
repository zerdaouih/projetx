package org.projetx.front.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.projetx.back.service.LoginService;
import org.projetx.back.service.MyServicePOC;
import org.projetx.model.Role;
import org.projetx.model.User;
import org.projetx.repo.RoleRepository;
import org.projetx.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	@Autowired
	private RoleRepository roleRepository;

	/**
	 * this method handle GET requests mapped to "/"
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String home(Model model) {
//		logger.info(myService.message());
//		model.addAttribute("msg", myService.message());
//		model.addAttribute("message", "3awd lkerrek");
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
			Set<Role> roles = new HashSet<Role>();
			
			Role adminRole = roleRepository.findByRole("ADMIN");
//			System.out.println(" adminRole " + adminRole);
//			roleRepository.save(adminRole);
//			Role adminCreatedRole = roleRepository.findByRole("ADMIN");
//			System.out.println(" admin role" + adminCreatedRole);
			roles.add(adminRole );
			user.setRoles(roles );
			user.setActive(1);
			userRepository.save(user);
			Iterable<User> userList = userRepository.findAll();
			logger.info("## userList ##");
			logger.info(userList);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			User user = userService.findUserByEmail(auth.getName());
			System.out.println("auth >>>" );
			System.out.println("#authorities "+ auth.getAuthorities() );
			System.out.println("#name "+ auth.getName() );
			System.out.println("#credentials "+ auth.getCredentials().toString() );
			System.out.println("#details "+ auth.getDetails().toString() );
			System.out.println("#principal "+ auth.getPrincipal().toString() );
			
			model.addAttribute("user", user);
			System.out.println("user BEFORE >>>>" +model.asMap().get("user"));
			return "welcom";
		} else {
			model.addAttribute("error", true);
			return "index";
		}
	}
}
