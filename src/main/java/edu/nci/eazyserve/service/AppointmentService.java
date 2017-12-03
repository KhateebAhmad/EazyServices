package edu.nci.eazyserve.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nci.eazyserve.bean.Appointment;
import edu.nci.eazyserve.bean.ServiceProvider;
import edu.nci.eazyserve.constants.Constants.AppointmentStatus;
import edu.nci.eazyserve.repository.AppointmentRepository;
import edu.nci.eazyserve.repository.ServiceProviderRepository;
import edu.nci.eazyserve.repository.UserRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ServiceProviderRepository serviceproviderRepository;

	public Appointment bookAppointment(Appointment appointment) {
		if (appointment == null)
			return null;

		appointment.setId(UUID.randomUUID().toString());
		appointment.setStatus(AppointmentStatus.OPEN.getVal());
		appointment.setBookingDate(new Timestamp(new Date().getTime()));
		appointmentRepository.save(appointment);
		return appointment;
	}

	public Appointment getAppointmentDetails(String appointmentId) {
		return appointmentRepository.findOne(appointmentId);
	}

	public List<Appointment> getAppointmentHistoryForUser(String userId) {
		return appointmentRepository.findByUser(userRepository.findOne(userId));
	}

	public List<Appointment> getAppointmentsOfServiceProvider(String serviceProviderId) {
		ServiceProvider serviceProvider = serviceproviderRepository.findOne(serviceProviderId);
		return appointmentRepository.findByServiceProvider(serviceProvider);
	}

	public List<Appointment> getOpenAppointmentsOfServiceProvider(String serviceProviderId) {
		List<Appointment> openAppointments = new ArrayList<Appointment>();
		ServiceProvider serviceProvider = serviceproviderRepository.findOne(serviceProviderId);

		List<Appointment> serviceProviderAppointments = appointmentRepository.findByServiceProvider(serviceProvider);

		for (Appointment appointment : serviceProviderAppointments) {
			if (appointment.getStatus() == AppointmentStatus.OPEN.getVal()) {
				openAppointments.add(appointment);
			}
		}
		return openAppointments;

	}
}
