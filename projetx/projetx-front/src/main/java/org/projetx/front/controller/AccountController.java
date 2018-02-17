package org.projetx.front.controller;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.projetx.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author hamada
 *
 */
@Controller
@SessionAttributes("user")
public class AccountController {

	/**
	 * Lgger
	 */
	Logger logger = Logger.getLogger(AccountController.class);

	/**
	 * this Methos allow to handle requests mapped to /account.
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(path = "/account")
	public String home(@ModelAttribute User user) {
		logger.info("user account : " + user.getUserName() + "is logged at " + LocalDateTime.now());
		return "account";
	}

}
