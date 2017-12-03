package edu.nci.eazyserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nci.eazyserve.bean.RatingReview;
import edu.nci.eazyserve.service.ReviewService;

@RestController
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	@RequestMapping(method = RequestMethod.POST, value = "/{userId}/postReview/{serviceProviderId}")
	@CrossOrigin(origins = "http://localhost:8080")
	public RatingReview postReviewForServiceProvider(@RequestBody RatingReview review) {
		return reviewService.postReviewForServiceProvider(review);
	}

	@RequestMapping(method = RequestMethod.GET, value = "getReviews/{serviceProviderId}")
	@CrossOrigin(origins = "http://localhost:8080")
	public List<RatingReview> getReviewsForServiceProvider(@PathVariable String serviceProviderId) {
		return reviewService.getReviewsForServiceProvider(serviceProviderId);
	}

}
