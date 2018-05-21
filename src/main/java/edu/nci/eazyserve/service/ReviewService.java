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
		
		if(review.getRating() == null || review.getRating() == 0 || review.getRating() < 0) {
			
			if(review.getReview().contains("excellent") || review.getReview().contains("awesome") || review.getReview().contains("superb")) {
				review.setRating(5);
			}
			else if (review.getReview().contains("very good") || review.getReview().contains("great")) {
				review.setRating(4);
			}
			else if (review.getReview().contains("satisfactory") || review.getReview().contains("okay") || review.getReview().contains("good") || review.getReview().contains("average")) {
				review.setRating(3);
			}
			else if (review.getReview().contains("bad") || review.getReview().contains("not good")) {
				review.setRating(2);
			}
			else if (review.getReview().contains("very bad") || review.getReview().contains("unsatisfactory") || review.getReview().contains("poor")) {
				review.setRating(1);
			}
		}
		
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
