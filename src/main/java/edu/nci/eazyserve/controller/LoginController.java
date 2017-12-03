package edu.nci.eazyserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nci.eazyserve.bean.Credentials;
import edu.nci.eazyserve.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	@CrossOrigin(origins = "http://localhost:8080")
	public Object loginActor(@RequestBody Credentials credentials) {
		return loginService.loginActor(credentials);
	}
}
