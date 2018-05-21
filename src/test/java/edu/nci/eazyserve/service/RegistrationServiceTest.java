package edu.nci.eazyserve.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.nci.eazyserve.bean.Administrator;
import edu.nci.eazyserve.bean.Credentials;
import edu.nci.eazyserve.bean.Role;
import edu.nci.eazyserve.bean.Service;
import edu.nci.eazyserve.bean.ServiceProvider;
import edu.nci.eazyserve.bean.ServiceToProviderMapper;
import edu.nci.eazyserve.bean.User;
import edu.nci.eazyserve.repository.AdministratorRepository;
import edu.nci.eazyserve.repository.CredentialsRepository;
import edu.nci.eazyserve.repository.RoleRepository;
import edu.nci.eazyserve.repository.ServiceProviderRepository;
import edu.nci.eazyserve.repository.ServiceRepository;
import edu.nci.eazyserve.repository.ServiceToProviderMapperRepository;
import edu.nci.eazyserve.repository.UserRepository;
import edu.nci.eazyserve.service.RegistrationService;

public class RegistrationServiceTest {

	@Mock
	private CredentialsRepository mockedCredentialsRepository;

	@Mock
	private UserRepository mockedUserRepository;

	@Mock
	private ServiceProviderRepository mockedServiceProviderRepository;

	@Mock
	private RoleRepository mockedRoleRepository;

	@Mock
	private ServiceToProviderMapperRepository mockedMapperRepository;

	@Mock
	private AdministratorRepository mockedAdministratorRepository;

	@Mock
	private ServiceRepository mockedServiceRepository;

	@InjectMocks
	RegistrationService registrationService = new RegistrationService();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddNullCredentials() {
		assertEquals(null, registrationService.addCredentials(null));
	}

	@Test
	public void testAddCredentialsWithNullUsername() {
		Credentials credentials = new Credentials();
		credentials.setUsername(null);
		assertEquals(null, registrationService.addCredentials(credentials));
	}

	@Test
	public void testAddCredentialsWithNullPassword() {
		Credentials credentials = new Credentials();
		credentials.setPassword(null);
		assertEquals(null, registrationService.addCredentials(credentials));
	}

	@Test
	public void testAddCredentials() {
		Credentials credentials = new Credentials();
		credentials.setActorId("abc");
		credentials.setId("abc_id");
		credentials.setPassword("abc_pass");
		credentials.setUsername("abc.username");
		Mockito.when(mockedCredentialsRepository.save(credentials)).thenReturn(credentials);
		assertEquals(36, registrationService.addCredentials(credentials).length());
	}

	@Test
	public void testAddUser() {
		User user = new User();
		Role role = new Role();
		role.setName("USER");
		Mockito.when(mockedUserRepository.save(user)).thenReturn(user);
		Mockito.when(mockedRoleRepository.findByName(Mockito.any(String.class))).thenReturn(role);
		assertEquals("USER", registrationService.addUser(user).getRole().getName());
	}

	@Test
	public void testAddServiceProvider() {
		ServiceProvider sp = new ServiceProvider();
		Role role = new Role();
		role.setName("SERVICE_PROVIDER");
		Mockito.when(mockedServiceProviderRepository.save(sp)).thenReturn(sp);
		Mockito.when(mockedRoleRepository.findByName(Mockito.any(String.class))).thenReturn(role);
		Mockito.when(mockedMapperRepository.save(Mockito.any(ServiceToProviderMapper.class)))
				.thenReturn(new ServiceToProviderMapper());
		assertEquals("SERVICE_PROVIDER", registrationService.addServiceProvider(sp).getRole().getName());
	}

	@Test
	public void testAddAdministrator() {
		Administrator administrator = new Administrator();
		Role role = new Role();
		role.setName("ADMIN");
		Mockito.when(mockedAdministratorRepository.save(administrator)).thenReturn(administrator);
		Mockito.when(mockedRoleRepository.findByName(Mockito.any(String.class))).thenReturn(role);
		assertEquals("ADMIN", registrationService.addAdministrator(administrator).getRole().getName());
	}

	@Test
	public void testGetServices() {
		Service service1 = new Service();
		service1.setId("1");
		service1.setName("service1");
		service1.setDescription("desc1");
		Service service2 = new Service();
		service2.setId("2");
		service2.setName("service2");
		service2.setDescription("desc2");
		List<Service> services = new ArrayList<Service>();
		services.add(service1);
		services.add(service2);
		Iterable<Service> serviceIterable = services;
		Mockito.when(mockedServiceRepository.findAll()).thenReturn(serviceIterable);
		assertEquals(2, registrationService.getServices().size());
		assertEquals("1", ((Service) registrationService.getServices().get(0)).getId());
		assertEquals("service1", ((Service) registrationService.getServices().get(0)).getName());
		assertEquals("desc1", ((Service) registrationService.getServices().get(0)).getDescription());
		assertEquals("2", ((Service) registrationService.getServices().get(1)).getId());
		assertEquals("service2", ((Service) registrationService.getServices().get(1)).getName());
		assertEquals("desc2", ((Service) registrationService.getServices().get(1)).getDescription());
	}

}
