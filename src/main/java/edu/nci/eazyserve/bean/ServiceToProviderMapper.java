package edu.nci.eazyserve.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_TO_PROVIDER_MAPPER")
public class ServiceToProviderMapper {

	@Id
	@Column(name = "ID")
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SERVICE_PROVIDER_ID")
	private ServiceProvider serviceProvider;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SERVICE_ID")
	private Service service;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
