package edu.nci.eazyserve.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.nci.eazyserve.bean.RatingReview;
import edu.nci.eazyserve.bean.ServiceProvider;

public interface RatingReviewRepository extends CrudRepository<RatingReview, String> {

	public List<RatingReview> findByServiceProvider(ServiceProvider serviceProvider);
}
