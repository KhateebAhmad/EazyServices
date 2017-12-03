package edu.tcd.tapserve.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.nci.eazyserve.bean.Appointment;
import edu.nci.eazyserve.bean.Service;
import edu.nci.eazyserve.bean.ServiceProvider;
import edu.nci.eazyserve.bean.User;
import edu.nci.eazyserve.constants.Constants.AppointmentStatus;
import edu.nci.eazyserve.repository.AppointmentRepository;
import edu.nci.eazyserve.repository.ServiceProviderRepository;
import edu.nci.eazyserve.repository.UserRepository;
import edu.nci.eazyserve.service.AppointmentService;

public class AppointmentServiceTest {

	@Mock
	AppointmentRepository appointmentRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	ServiceProviderRepository serviceproviderRepository;

	@InjectMocks
	AppointmentService appointmentService = new AppointmentService();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testBookNullAppointment() {
		assertEquals(null, appointmentService.bookAppointment(null));
	}

	@Test
	public void testBookAppointment() {
		User user = new User();
		user.setId("user1");

		ServiceProvider sp = new ServiceProvider();
		sp.setId("sp1");

		Service service = new Service();
		service.setId("serv1");

		Appointment appointment = new Appointment();
		appointment.setName("abc");
		appointment.setUser(user);
		appointment.setServiceProvider(sp);
		appointment.setService(service);

		Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);

		assertEquals(appointment.getName(), appointmentService.bookAppointment(appointment).getName());
		assertEquals(appointment.getService(), appointmentService.bookAppointment(appointment).getService());
		assertEquals(appointment.getUser(), appointmentService.bookAppointment(appointment).getUser());
		assertEquals(appointment.getServiceProvider(),
				appointmentService.bookAppointment(appointment).getServiceProvider());
		assertEquals(AppointmentStatus.OPEN.getVal(), appointmentService.bookAppointment(appointment).getStatus());
	}

	@Test
	public void testGetAppointment() {
		User user = new User();
		user.setId("user1");

		ServiceProvider sp = new ServiceProvider();
		sp.setId("sp1");

		Service service = new Service();
		service.setId("serv1");

		Appointment appointment = new Appointment();
		appointment.setId("app1");
		appointment.setName("abc");
		appointment.setUser(user);
		appointment.setServiceProvider(sp);
		appointment.setService(service);

		Mockito.when(appointmentRepository.findOne(Mockito.anyString())).thenReturn(appointment);

		assertEquals(appointment, appointmentService.getAppointmentDetails("app1"));

	}

	@Test
	public void testGetAppointmentHistory() {
		User user = new User();
		user.setId("user1");

		ServiceProvider sp = new ServiceProvider();
		sp.setId("sp1");

		Service service = new Service();
		service.setId("serv1");

		Appointment appointment1 = new Appointment();
		appointment1.setId("app1");
		appointment1.setName("abc");
		appointment1.setUser(user);
		appointment1.setServiceProvider(sp);
		appointment1.setService(service);

		Appointment appointment2 = new Appointment();
		appointment2.setId("app2");
		appointment2.setName("xyz");
		appointment2.setUser(user);
		appointment2.setServiceProvider(sp);
		appointment2.setService(service);

		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(appointment1);
		appointments.add(appointment2);

		Mockito.when(userRepository.findOne(Mockito.anyString())).thenReturn(user);

		Mockito.when(appointmentRepository.findByUser(Mockito.any(User.class))).thenReturn(appointments);

		assertEquals(appointment1, appointmentService.getAppointmentHistoryForUser("user1").get(0));
		assertEquals(appointment2, appointmentService.getAppointmentHistoryForUser("user1").get(1));

	}

	@Test
	public void testGetOpenAppointmentOfServiceProvider() {
		ServiceProvider sp = new ServiceProvider();
		sp.setId("sp1");

		Appointment appointment1 = new Appointment();
		appointment1.setId("app1");
		appointment1.setName("abc1");
		appointment1.setServiceProvider(sp);
		appointment1.setStatus(0);

		Appointment appointment2 = new Appointment();
		appointment2.setId("app2");
		appointment2.setName("abc2");
		appointment2.setServiceProvider(sp);
		appointment2.setStatus(1);

		Appointment appointment3 = new Appointment();
		appointment3.setId("app3");
		appointment3.setName("abc3");
		appointment3.setServiceProvider(sp);
		appointment3.setStatus(2);

		Appointment appointment4 = new Appointment();
		appointment4.setId("app1");
		appointment4.setName("abc1");
		appointment4.setServiceProvider(sp);
		appointment4.setStatus(0);

		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(appointment1);
		appointments.add(appointment2);
		appointments.add(appointment3);
		appointments.add(appointment4);

		Mockito.when(serviceproviderRepository.findOne(Mockito.any(String.class))).thenReturn(sp);

		Mockito.when(appointmentRepository.findByServiceProvider(Mockito.any(ServiceProvider.class)))
				.thenReturn(appointments);

		assertEquals(2, appointmentService.getOpenAppointmentsOfServiceProvider("sp1").size());
		assertEquals(appointment1, appointmentService.getOpenAppointmentsOfServiceProvider("sp1").get(0));
		assertEquals(appointment4, appointmentService.getOpenAppointmentsOfServiceProvider("sp1").get(1));
	}

	@Test
	public void testGetAppointmentOfServiceProvider() {
		ServiceProvider sp = new ServiceProvider();
		sp.setId("sp1");

		Appointment appointment1 = new Appointment();
		appointment1.setId("app1");
		appointment1.setName("abc1");
		appointment1.setServiceProvider(sp);
		appointment1.setStatus(0);

		Appointment appointment2 = new Appointment();
		appointment2.setId("app2");
		appointment2.setName("abc2");
		appointment2.setServiceProvider(sp);
		appointment2.setStatus(1);

		Appointment appointment3 = new Appointment();
		appointment3.setId("app3");
		appointment3.setName("abc3");
		appointment3.setServiceProvider(sp);
		appointment3.setStatus(2);

		Appointment appointment4 = new Appointment();
		appointment4.setId("app1");
		appointment4.setName("abc1");
		appointment4.setServiceProvider(sp);
		appointment4.setStatus(0);

		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(appointment1);
		appointments.add(appointment2);
		appointments.add(appointment3);
		appointments.add(appointment4);

		Mockito.when(serviceproviderRepository.findOne(Mockito.any(String.class))).thenReturn(sp);

		Mockito.when(appointmentRepository.findByServiceProvider(Mockito.any(ServiceProvider.class)))
				.thenReturn(appointments);

		assertEquals(4, appointmentService.getAppointmentsOfServiceProvider("sp1").size());
		assertEquals(appointment1, appointmentService.getAppointmentsOfServiceProvider("sp1").get(0));
		assertEquals(appointment2, appointmentService.getAppointmentsOfServiceProvider("sp1").get(1));
		assertEquals(appointment3, appointmentService.getAppointmentsOfServiceProvider("sp1").get(2));
		assertEquals(appointment4, appointmentService.getAppointmentsOfServiceProvider("sp1").get(3));
	}
}
