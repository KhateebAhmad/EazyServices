package edu.nci.eazyserve.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nci.eazyserve.bean.RatingReview;
import edu.nci.eazyserve.bean.ServiceProvider;
import edu.nci.eazyserve.repository.RatingReviewRepository;
import edu.nci.eazyserve.repository.ServiceProviderRepository;

@Service
public class ReviewService {

	@Autowired
	private RatingReviewRepository ratingReviewRepository;

	@Autowired
	private ServiceProviderRepository serviceProviderRepository;

	public RatingReview postReviewForServiceProvider(RatingReview review) {
		review.setId(UUID.randomUUID().toString());
		ratingReviewRepository.save(review);
		return review;
	}

	public List<RatingReview> getReviewsForServiceProvider(String serviceProviderId) {
		List<RatingReview> reviews = new ArrayList<RatingReview>();
		ServiceProvider serviceProvider = serviceProviderRepository.findOne(serviceProviderId);
		reviews = ratingReviewRepository.findByServiceProvider(serviceProvider);
		return reviews;

	}

}
