package edu.nci.eazyserve.bean;

import java.util.Comparator;
import java.util.List;

public class CustomServiceProvider {

	private ServiceProvider serviceProvider;

	private List<RatingReview> reviews;

	private List<RatingReview> reviewsByFriends;

	public static Comparator<CustomServiceProvider> COMPARE_BY_FRIEND_REVIEWS = new Comparator<CustomServiceProvider>() {
		public int compare(CustomServiceProvider p1, CustomServiceProvider p2) {
			return ((Integer) p1.reviewsByFriends.size()).compareTo((Integer) p2.reviewsByFriends.size());
		}
	};

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public List<RatingReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<RatingReview> reviews) {
		this.reviews = reviews;
	}

	public List<RatingReview> getReviewsByFriends() {
		return reviewsByFriends;
	}

	public void setReviewsByFriends(List<RatingReview> reviewsByFriends) {
		this.reviewsByFriends = reviewsByFriends;
	}
}
