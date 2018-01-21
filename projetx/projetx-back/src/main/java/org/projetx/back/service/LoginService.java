package org.projetx.back.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean login(String userName, String password) {
		boolean login = "hamada".equals(userName) && "hamada".equals(password);
		return login;
	}

}
