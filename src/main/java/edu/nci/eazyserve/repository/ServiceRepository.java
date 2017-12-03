package edu.nci.eazyserve.repository;

import org.springframework.data.repository.CrudRepository;

import edu.nci.eazyserve.bean.Service;

public interface ServiceRepository extends CrudRepository<Service, String> {

}
