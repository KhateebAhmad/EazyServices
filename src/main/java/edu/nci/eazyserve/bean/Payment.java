package edu.nci.eazyserve.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT")
public class Payment {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "AMOUNT")
	private Double amount;

	@Column(name = "MODE")
	private String mode;

	@Column(name = "STATUS")
	private Integer status;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "APPOINTMENT_ID")
	private Appointment appointment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
}
