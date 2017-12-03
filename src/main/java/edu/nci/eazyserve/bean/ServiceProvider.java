package edu.nci.eazyserve.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SERVICE_PROVIDER")
public class ServiceProvider {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ORGANIZATION_NAME")
	private String organizationName;

	@Column(name = "PHOTO")
	private byte[] photo;

	@Column(name = "CONTACT_NUMBER")
	private Long contactNumber;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "REGION_ID")
	private String regionIdentifier;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	private Role role;

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "SERVICE_TO_PROVIDER_MAPPER", joinColumns = @JoinColumn(name = "SERVICE_PROVIDER_ID") , inverseJoinColumns = @JoinColumn(name = "SERVICE_ID") )
//	@JsonManagedReference
	@Transient
	private List<Service> services = new ArrayList<Service>();

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

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegionIdentifier() {
		return regionIdentifier;
	}

	public void setRegionIdentifier(String regionIdentifier) {
		this.regionIdentifier = regionIdentifier;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

}
