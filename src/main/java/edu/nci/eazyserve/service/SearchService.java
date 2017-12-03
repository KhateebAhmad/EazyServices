package edu.nci.eazyserve.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nci.eazyserve.bean.CustomServiceProvider;
import edu.nci.eazyserve.bean.RatingReview;
import edu.nci.eazyserve.bean.ServiceToProviderMapper;
import edu.nci.eazyserve.repository.RatingReviewRepository;
import edu.nci.eazyserve.repository.ServiceRepository;
import edu.nci.eazyserve.repository.ServiceToProviderMapperRepository;

@Service
public class SearchService {

	@Autowired
	private ServiceToProviderMapperRepository mapperRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private RatingReviewRepository ratingReviewRepository;

	@Autowired
	private FriendService friendService;

	public List<CustomServiceProvider> getSearchResult(String userId, String regionId, String serviceId) {
		List<CustomServiceProvider> customServiceProviders = new ArrayList<CustomServiceProvider>();
		edu.nci.eazyserve.bean.Service service = serviceRepository.findOne(serviceId);
		List<ServiceToProviderMapper> mapperObjects = mapperRepository.findByService(service);

		for (ServiceToProviderMapper mapper : mapperObjects) {
			if (!mapper.getServiceProvider().getRegionIdentifier().equals(regionId))
				continue;

			CustomServiceProvider customObj = new CustomServiceProvider();
			customObj.setServiceProvider(mapper.getServiceProvider());

			List<RatingReview> reviews = ratingReviewRepository.findByServiceProvider(mapper.getServiceProvider());
			customObj.setReviews(reviews);

			customObj.setReviewsByFriends(friendService.getFriendsReviews(reviews, userId));

			customServiceProviders.add(customObj);
		}
		
		Collections.sort(customServiceProviders,
				Collections.reverseOrder(CustomServiceProvider.COMPARE_BY_FRIEND_REVIEWS));
		
		return customServiceProviders;
	}

}
