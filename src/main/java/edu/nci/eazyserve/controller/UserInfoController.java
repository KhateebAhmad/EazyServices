package edu.nci.eazyserve.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nci.eazyserve.bean.User;
import edu.nci.eazyserve.service.UserInfoService;

@RestController
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(method = RequestMethod.GET, value = "/userInfo/{userName}")
	@CrossOrigin(origins = "http://localhost:8080/*")
	public UserInfo getUserInfo(@PathVariable String userName) {
		User user = userInfoService.getUserInfo(userName);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setId(user.getId());
		userInfo.setName(user.getName());
		userInfo.setRole(user.getRole().getName());
		
		return userInfo;
	}
	
	public class UserInfo implements Serializable {
		private String name;
		private String role;
		private String id;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	}
}
