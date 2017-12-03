package edu.nci.eazyserve.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.nci.eazyserve.bean.Appointment;
import edu.nci.eazyserve.bean.ServiceProvider;
import edu.nci.eazyserve.bean.User;

public interface AppointmentRepository extends CrudRepository<Appointment, String> {

	public List<Appointment> findByUser(User user);
	
	public List<Appointment> findByServiceProvider(ServiceProvider serviceProvider);
}
