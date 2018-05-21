package edu.nci.eazyserve.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.nci.eazyserve.bean.Administrator;
import edu.nci.eazyserve.bean.Credentials;
import edu.nci.eazyserve.bean.Role;
import edu.nci.eazyserve.bean.ServiceProvider;
import edu.nci.eazyserve.bean.User;
import edu.nci.eazyserve.constants.Constants.RoleType;
import edu.nci.eazyserve.repository.AdministratorRepository;
import edu.nci.eazyserve.repository.CredentialsRepository;
import edu.nci.eazyserve.repository.ServiceProviderRepository;
import edu.nci.eazyserve.repository.UserRepository;
import edu.nci.eazyserve.service.LoginService;
import edu.nci.eazyserve.utils.PasswordEncryptionUtil;

public class LoginServiceTest {

	@InjectMocks
	LoginService loginService = new LoginService();

	@Mock
	CredentialsRepository mockedCredentialsRepository;

	@Mock
	UserRepository mockedUserRepository;

	@Mock
	ServiceProviderRepository mockedServiceProviderRepository;

	@Mock
	AdministratorRepository mockedAdministratorRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testNullCredentials() {
		assertEquals(null, loginService.loginActor(null));
	}

	@Test
	public void testNullUsername() {
		Credentials credentials = new Credentials();
		credentials.setPassword("p");
		credentials.setUsername(null);
		assertEquals(null, loginService.loginActor(credentials));
	}

	@Test
	public void testNullPassword() {
		Credentials credentials = new Credentials();
		credentials.setUsername("u");
		credentials.setPassword(null);
		assertEquals(null, loginService.loginActor(credentials));
	}

	@Test
	public void testDbCredentialsNotFound() {
		Credentials credentials = new Credentials();
		credentials.setPassword("p");
		credentials.setUsername("u");
		Mockito.when(mockedCredentialsRepository.findByUsername(Mockito.anyString())).thenReturn(null);

		assertEquals(null, loginService.loginActor(credentials));
	}

	@Test
	public void testWrongPassword() {
		Credentials credentials1 = new Credentials();
		credentials1.setPassword("p");
		credentials1.setUsername("u");
		credentials1.setId("1");
		credentials1.setActorId("1");

		Credentials credentials2 = new Credentials();
		credentials2.setPassword(PasswordEncryptionUtil.encrypt("q"));
		credentials2.setUsername("u");
		credentials2.setId("1");
		credentials2.setActorId("1");

		Mockito.when(mockedCredentialsRepository.findByUsername(Mockito.anyString())).thenReturn(credentials2);

		assertEquals(null, loginService.loginActor(credentials1));
	}

	@Test
	public void testFoundUser() {
		Credentials credentials1 = new Credentials();
		credentials1.setPassword("p");
		credentials1.setUsername("u");
		credentials1.setId("1");
		credentials1.setActorId("1");

		Credentials credentials2 = new Credentials();
		credentials2.setPassword(PasswordEncryptionUtil.encrypt("p"));
		credentials2.setUsername("u");
		credentials2.setId("1");
		credentials2.setActorId("1");

		Role role = new Role();
		role.setName(RoleType.USER.name());

		User user = new User();
		user.setId("1");
		user.setRole(role);

		Mockito.when(mockedCredentialsRepository.findByUsername(Mockito.anyString())).thenReturn(credentials2);
		Mockito.when(mockedUserRepository.findOne(Mockito.anyString())).thenReturn(user);

		assertEquals("1", ((User) loginService.loginActor(credentials1)).getId());
		assertEquals("USER", ((User) loginService.loginActor(credentials1)).getRole().getName());
	}

	@Test
	public void testFoundServiceProvder() {
		Credentials credentials1 = new Credentials();
		credentials1.setPassword("p");
		credentials1.setUsername("u");
		credentials1.setId("1");
		credentials1.setActorId("1");

		Credentials credentials2 = new Credentials();
		credentials2.setPassword(PasswordEncryptionUtil.encrypt("p"));
		credentials2.setUsername("u");
		credentials2.setId("1");
		credentials2.setActorId("1");

		Role role = new Role();
		role.setName(RoleType.SERVICE_PROVIDER.name());

		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setId("1");
		serviceProvider.setRole(role);

		Mockito.when(mockedCredentialsRepository.findByUsername(Mockito.anyString())).thenReturn(credentials2);
		Mockito.when(mockedUserRepository.findOne(Mockito.anyString())).thenReturn(null);
		Mockito.when(mockedServiceProviderRepository.findOne(Mockito.anyString())).thenReturn(serviceProvider);

		assertEquals("1", ((ServiceProvider) loginService.loginActor(credentials1)).getId());
		assertEquals("SERVICE_PROVIDER", ((ServiceProvider) loginService.loginActor(credentials1)).getRole().getName());
	}

	@Test
	public void testFoundAdministrator() {
		Credentials credentials1 = new Credentials();
		credentials1.setPassword("p");
		credentials1.setUsername("u");
		credentials1.setId("1");
		credentials1.setActorId("1");

		Credentials credentials2 = new Credentials();
		credentials2.setPassword(PasswordEncryptionUtil.encrypt("p"));
		credentials2.setUsername("u");
		credentials2.setId("1");
		credentials2.setActorId("1");

		Role role = new Role();
		role.setName(RoleType.ADMIN.name());

		Administrator administrator = new Administrator();
		administrator.setId("1");
		administrator.setRole(role);

		Mockito.when(mockedCredentialsRepository.findByUsername(Mockito.anyString())).thenReturn(credentials2);
		Mockito.when(mockedUserRepository.findOne(Mockito.anyString())).thenReturn(null);
		Mockito.when(mockedServiceProviderRepository.findOne(Mockito.anyString())).thenReturn(null);
		Mockito.when(mockedAdministratorRepository.findOne(Mockito.anyString())).thenReturn(administrator);

		assertEquals("1", ((Administrator) loginService.loginActor(credentials1)).getId());
		assertEquals("ADMIN", ((Administrator) loginService.loginActor(credentials1)).getRole().getName());
	}

	@Test
	public void testNoActorFound() {
		Credentials credentials1 = new Credentials();
		credentials1.setPassword("p");
		credentials1.setUsername("u");
		credentials1.setId("1");
		credentials1.setActorId("1");

		Credentials credentials2 = new Credentials();
		credentials2.setPassword(PasswordEncryptionUtil.encrypt("p"));
		credentials2.setUsername("u");
		credentials2.setId("1");
		credentials2.setActorId("1");

		Mockito.when(mockedCredentialsRepository.findByUsername(Mockito.anyString())).thenReturn(credentials2);
		Mockito.when(mockedUserRepository.findOne(Mockito.anyString())).thenReturn(null);
		Mockito.when(mockedServiceProviderRepository.findOne(Mockito.anyString())).thenReturn(null);
		Mockito.when(mockedAdministratorRepository.findOne(Mockito.anyString())).thenReturn(null);

		assertEquals(null, loginService.loginActor(credentials1));
	}

}
