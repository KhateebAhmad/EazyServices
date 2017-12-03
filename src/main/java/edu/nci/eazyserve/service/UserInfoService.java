package edu.nci.eazyserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nci.eazyserve.bean.User;
import edu.nci.eazyserve.repository.UserRepository;

@Service
public class UserInfoService {
	@Autowired
	private UserRepository userRepository;
	
	public User getUserInfo(String userName){
		return userRepository.findByName(userName);
	}
}
