package edu.nci.eazyserve.repository;

import org.springframework.data.repository.CrudRepository;

import edu.nci.eazyserve.bean.Administrator;

public interface AdministratorRepository extends CrudRepository<Administrator, String> {

}
