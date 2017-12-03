package edu.nci.eazyserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nci.eazyserve.bean.Appointment;
import edu.nci.eazyserve.service.AppointmentService;

@RestController
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@RequestMapping(method = RequestMethod.POST, value = "/{userId}/bookAppointment/{serviceProviderId}/{serviceId}")
	@CrossOrigin(origins = "http://localhost:8080")
	public Appointment bookAppointment(@RequestBody Appointment appointment) {
		return appointmentService.bookAppointment(appointment);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userId}/appointments/{appointmentId}")
	@CrossOrigin(origins = "http://localhost:8080")
	public Appointment getAppointmentDetails(@PathVariable String appointmentId) {
		return appointmentService.getAppointmentDetails(appointmentId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userId}/appointments")
	@CrossOrigin(origins = "http://localhost:8080")
	public List<Appointment> getAppointmentHistoryForUser(@PathVariable String userId) {
		return appointmentService.getAppointmentHistoryForUser(userId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{serviceProviderId}/appointments/open")
	@CrossOrigin(origins = "http://localhost:8080")
	public List<Appointment> getOpenAppointmentsOfServiceProvider(@PathVariable String serviceProviderId) {
		return appointmentService.getOpenAppointmentsOfServiceProvider(serviceProviderId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{serviceproviderId}/spAppointments")
	@CrossOrigin(origins = "http://localhost:8080")
	public List<Appointment> getAppointmentsOfServiceProvider(@PathVariable String serviceProviderId) {
		return appointmentService.getAppointmentsOfServiceProvider(serviceProviderId);
	}
}
