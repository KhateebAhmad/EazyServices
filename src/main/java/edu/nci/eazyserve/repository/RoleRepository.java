package edu.nci.eazyserve.repository;

import org.springframework.data.repository.CrudRepository;

import edu.nci.eazyserve.bean.Role;

public interface RoleRepository extends CrudRepository<Role, String> {

	public Role findByName(String name);
}
