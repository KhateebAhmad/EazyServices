package edu.nci.eazyserve.repository;

import org.springframework.data.repository.CrudRepository;

import edu.nci.eazyserve.bean.ServiceProvider;

public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, String> {

	public ServiceProvider findByRoleId(String roleId);
}
