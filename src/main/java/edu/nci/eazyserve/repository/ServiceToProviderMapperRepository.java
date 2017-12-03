package edu.nci.eazyserve.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.nci.eazyserve.bean.Service;
import edu.nci.eazyserve.bean.ServiceToProviderMapper;

public interface ServiceToProviderMapperRepository extends CrudRepository<ServiceToProviderMapper, String> {

	public List<ServiceToProviderMapper> findByService(Service service);
}
