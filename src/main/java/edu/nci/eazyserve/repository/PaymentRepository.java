package edu.nci.eazyserve.repository;

import org.springframework.data.repository.CrudRepository;

import edu.nci.eazyserve.bean.Payment;

public interface PaymentRepository extends CrudRepository<Payment, String> {

}
