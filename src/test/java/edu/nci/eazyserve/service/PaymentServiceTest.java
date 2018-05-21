package edu.nci.eazyserve.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.nci.eazyserve.bean.Appointment;
import edu.nci.eazyserve.bean.Payment;
import edu.nci.eazyserve.constants.Constants.PaymentStatus;
import edu.nci.eazyserve.repository.AppointmentRepository;
import edu.nci.eazyserve.repository.PaymentRepository;
import edu.nci.eazyserve.service.PaymentService;

public class PaymentServiceTest {

	@InjectMocks
	PaymentService paymentService = new PaymentService();

	@Mock
	AppointmentRepository mockedAppointmentRepository;

	@Mock
	PaymentRepository paymentRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPayForService() {
		Appointment appointment = new Appointment();
		appointment.setId("app1");

		Payment payment = new Payment();
		payment.setId("p1");
		payment.setAppointment(appointment);

		Mockito.when(mockedAppointmentRepository.findOne(Mockito.anyString())).thenReturn(appointment);

		Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(payment);

		assertEquals(appointment, paymentService.payForService("app1", payment).getAppointment());

		assertEquals(PaymentStatus.DONE.getVal(), paymentService.payForService("app1", payment).getStatus());
	}

}
