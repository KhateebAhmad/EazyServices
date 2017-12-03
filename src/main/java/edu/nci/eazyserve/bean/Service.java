package edu.nci.eazyserve.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SERVICE")
public class Service {
	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

//	@ManyToMany(mappedBy = "services")
//	@JsonBackReference
	@Transient
	List<ServiceProvider> serviceproviders = new ArrayList<ServiceProvider>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ServiceProvider> getServiceproviders() {
		return serviceproviders;
	}

	public void setServiceproviders(List<ServiceProvider> serviceproviders) {
		this.serviceproviders = serviceproviders;
	}
}
