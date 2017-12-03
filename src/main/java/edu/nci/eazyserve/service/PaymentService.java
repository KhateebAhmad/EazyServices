package edu.nci.eazyserve.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nci.eazyserve.bean.Appointment;
import edu.nci.eazyserve.bean.Payment;
import edu.nci.eazyserve.constants.Constants.AppointmentStatus;
import edu.nci.eazyserve.constants.Constants.PaymentStatus;
import edu.nci.eazyserve.repository.AppointmentRepository;
import edu.nci.eazyserve.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	public Payment payForService(String appointmentId, Payment payment) {
		Appointment appointment = appointmentRepository.findOne(appointmentId);

		payment.setId(UUID.randomUUID().toString());
		appointment.setStatus(AppointmentStatus.CLOSED.getVal());
		payment.setAppointment(appointment);
		payment.setStatus(PaymentStatus.DONE.getVal());

		paymentRepository.save(payment);
		
		return payment;
	}
}
