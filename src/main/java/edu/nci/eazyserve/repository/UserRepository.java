package edu.nci.eazyserve.repository;

import org.springframework.data.repository.CrudRepository;

import edu.nci.eazyserve.bean.User;

public interface UserRepository extends CrudRepository<User, String> {

	public User findByFbId(Long fbId);
	
	public User findByName(String name);
}
