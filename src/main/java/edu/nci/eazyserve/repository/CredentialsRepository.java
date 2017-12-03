package edu.nci.eazyserve.repository;

import org.springframework.data.repository.CrudRepository;

import edu.nci.eazyserve.bean.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, String> {

	public Credentials findByUsername(String username);
}
