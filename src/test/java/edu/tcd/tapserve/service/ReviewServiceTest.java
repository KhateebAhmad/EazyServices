package edu.tcd.tapserve.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.nci.eazyserve.bean.RatingReview;
import edu.nci.eazyserve.bean.ServiceProvider;
import edu.nci.eazyserve.repository.RatingReviewRepository;
import edu.nci.eazyserve.repository.ServiceProviderRepository;
import edu.nci.eazyserve.service.ReviewService;

public class ReviewServiceTest {

	@InjectMocks
	ReviewService reviewService = new ReviewService();

	@Mock
	RatingReviewRepository mockedRatingReviewRepository;

	@Mock
	ServiceProviderRepository mockedServiceProviderRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPostReview() {
		RatingReview review = new RatingReview();
		review.setRating(5);
		review.setReview("review");

		Mockito.when(mockedRatingReviewRepository.save(review)).thenReturn(review);

		assertEquals("review", reviewService.postReviewForServiceProvider(review).getReview());
		assertEquals(5, reviewService.postReviewForServiceProvider(review).getRating());
	}

	@Test
	public void testGetReviewsForServiceProvider() {
		ServiceProvider sp = new ServiceProvider();
		sp.setId("sp1");

		RatingReview review1 = new RatingReview();
		review1.setRating(5);
		review1.setReview("review1");
		review1.setServiceProvider(sp);

		RatingReview review2 = new RatingReview();
		review2.setRating(3);
		review2.setReview("review2");
		review2.setServiceProvider(sp);

		List<RatingReview> reviews = new ArrayList<RatingReview>();
		reviews.add(review1);
		reviews.add(review2);

		Mockito.when(mockedServiceProviderRepository.findOne(Mockito.anyString())).thenReturn(sp);
		Mockito.when(mockedRatingReviewRepository.findByServiceProvider(Mockito.any(ServiceProvider.class)))
				.thenReturn(reviews);

		assertEquals(2, reviewService.getReviewsForServiceProvider("sp1").size());
		assertEquals(review1, reviewService.getReviewsForServiceProvider("sp1").get(0));
		assertEquals(review2, reviewService.getReviewsForServiceProvider("sp1").get(1));
	}
}
