package edu.nci.eazyserve.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nci.eazyserve.bean.Administrator;
import edu.nci.eazyserve.bean.Credentials;
import edu.nci.eazyserve.bean.Role;
import edu.nci.eazyserve.bean.ServiceProvider;
import edu.nci.eazyserve.bean.ServiceToProviderMapper;
import edu.nci.eazyserve.bean.User;
import edu.nci.eazyserve.constants.Constants.RoleType;
import edu.nci.eazyserve.repository.AdministratorRepository;
import edu.nci.eazyserve.repository.CredentialsRepository;
import edu.nci.eazyserve.repository.RoleRepository;
import edu.nci.eazyserve.repository.ServiceProviderRepository;
import edu.nci.eazyserve.repository.ServiceRepository;
import edu.nci.eazyserve.repository.ServiceToProviderMapperRepository;
import edu.nci.eazyserve.repository.UserRepository;
import edu.nci.eazyserve.utils.PasswordEncryptionUtil;

@Service
public class RegistrationService {
	private final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

	@Autowired
	private CredentialsRepository credentialsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ServiceProviderRepository serviceProviderRepository;

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private ServiceToProviderMapperRepository mapperRepository;

	public String addCredentials(Credentials credentials) {
		if (credentials == null) {
			logger.warn("Input credentails object cannot be null.");
			return null;
		}
		if (credentials.getUsername() == null || credentials.getPassword() == null) {
			logger.warn("Username or passward of credentails cannot be null.");
			return null;
		}

		credentials.setId(UUID.randomUUID().toString());
		String actorId = UUID.randomUUID().toString();
		credentials.setActorId(actorId);
		logger.debug("Set Id and actorID to credential object.");

		String encryptedPassword = PasswordEncryptionUtil.encrypt(credentials.getPassword());
		credentials.setPassword(encryptedPassword);

		credentialsRepository.save(credentials);
		return actorId;
	}

	public User addUser(User user) {
		Role role = roleRepository.findByName(RoleType.USER.name());
		user.setRole(role);
		userRepository.save(user);
		return user;
	}

	public ServiceProvider addServiceProvider(ServiceProvider serviceProvider) {
		Role role = roleRepository.findByName(RoleType.SERVICE_PROVIDER.name());
		serviceProvider.setRole(role);
		serviceProviderRepository.save(serviceProvider);

		ServiceToProviderMapper mapper = new ServiceToProviderMapper();
		for (edu.nci.eazyserve.bean.Service service : serviceProvider.getServices()) {
			mapper.setId(UUID.randomUUID().toString());
			mapper.setService(service);
			mapper.setServiceProvider(serviceProvider);
			mapperRepository.save(mapper);
		}

		return serviceProvider;
	}

	public Administrator addAdministrator(Administrator administrator) {
		Role role = roleRepository.findByName(RoleType.ADMIN.name());
		administrator.setRole(role);
		administratorRepository.save(administrator);
		return administrator;
	}

	public List<edu.nci.eazyserve.bean.Service> getServices() {
		List<edu.nci.eazyserve.bean.Service> services = new ArrayList<edu.nci.eazyserve.bean.Service>();
		Iterable<edu.nci.eazyserve.bean.Service> servicesIterable = serviceRepository.findAll();
		for (edu.nci.eazyserve.bean.Service service : servicesIterable)
			services.add(service);
		return services;
	}

}
