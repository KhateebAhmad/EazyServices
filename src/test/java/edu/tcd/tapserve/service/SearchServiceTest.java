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

import edu.nci.eazyserve.bean.CustomServiceProvider;
import edu.nci.eazyserve.bean.RatingReview;
import edu.nci.eazyserve.bean.Service;
import edu.nci.eazyserve.bean.ServiceProvider;
import edu.nci.eazyserve.bean.ServiceToProviderMapper;
import edu.nci.eazyserve.bean.User;
import edu.nci.eazyserve.repository.RatingReviewRepository;
import edu.nci.eazyserve.repository.ServiceRepository;
import edu.nci.eazyserve.repository.ServiceToProviderMapperRepository;
import edu.nci.eazyserve.service.FriendService;
import edu.nci.eazyserve.service.SearchService;

public class SearchServiceTest {

	@InjectMocks
	SearchService searchService = new SearchService();

	@Mock
	private ServiceToProviderMapperRepository mockedMapperRepository;

	@Mock
	private ServiceRepository mockedServiceRepository;

	@Mock
	private RatingReviewRepository mockedRatingReviewRepository;

	@Mock
	private FriendService mockedfriendService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSearchServiceWithourReviews1() {
		User user = new User();
		user.setId("u1");

		Service service = new Service();
		service.setId("1");
		service.setName("serv1");

		ServiceProvider serviceProvider1 = new ServiceProvider();
		ServiceProvider serviceProvider2 = new ServiceProvider();
		serviceProvider1.setId("sp1");
		serviceProvider1.setRegionIdentifier("r1");
		serviceProvider2.setId("sp2");
		serviceProvider2.setRegionIdentifier("r1");

		ServiceToProviderMapper mapper1 = new ServiceToProviderMapper();
		ServiceToProviderMapper mapper2 = new ServiceToProviderMapper();
		mapper1.setId("m1");
		mapper2.setId("m2");
		mapper1.setService(service);
		mapper2.setService(service);
		mapper1.setServiceProvider(serviceProvider1);
		mapper2.setServiceProvider(serviceProvider2);

		List<ServiceToProviderMapper> mapperObjects = new ArrayList<ServiceToProviderMapper>();
		mapperObjects.add(mapper1);
		mapperObjects.add(mapper2);

		List<RatingReview> emptyReviews = new ArrayList<RatingReview>();

		Mockito.when(mockedServiceRepository.findOne(Mockito.any(String.class))).thenReturn(service);
		Mockito.when(mockedMapperRepository.findByService(Mockito.any(Service.class))).thenReturn(mapperObjects);
		Mockito.when(mockedRatingReviewRepository.findByServiceProvider(Mockito.any(ServiceProvider.class)))
				.thenReturn(emptyReviews).thenReturn(emptyReviews);
		Mockito.when(mockedfriendService.getFriendsReviews(Mockito.any(ArrayList.class), Mockito.any(String.class)))
				.thenReturn(emptyReviews).thenReturn(emptyReviews);

		List<CustomServiceProvider> customObjs = searchService.getSearchResult("u1", "r1", "1");

		assertEquals(2, customObjs.size());
		assertEquals(0, customObjs.get(0).getReviews().size());
		assertEquals(0, customObjs.get(0).getReviewsByFriends().size());
		assertEquals(0, customObjs.get(1).getReviews().size());
		assertEquals(0, customObjs.get(1).getReviewsByFriends().size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSearchServiceWithourReviews2() {
		User user = new User();
		user.setId("u1");

		Service service = new Service();
		service.setId("1");
		service.setName("serv1");

		ServiceProvider serviceProvider1 = new ServiceProvider();
		ServiceProvider serviceProvider2 = new ServiceProvider();
		serviceProvider1.setId("sp1");
		serviceProvider1.setRegionIdentifier("r2");
		serviceProvider2.setId("sp2");
		serviceProvider2.setRegionIdentifier("r3");

		ServiceToProviderMapper mapper1 = new ServiceToProviderMapper();
		ServiceToProviderMapper mapper2 = new ServiceToProviderMapper();
		mapper1.setId("m1");
		mapper2.setId("m2");
		mapper1.setService(service);
		mapper2.setService(service);
		mapper1.setServiceProvider(serviceProvider1);
		mapper2.setServiceProvider(serviceProvider2);

		List<ServiceToProviderMapper> mapperObjects = new ArrayList<ServiceToProviderMapper>();
		mapperObjects.add(mapper1);
		mapperObjects.add(mapper2);

		List<RatingReview> emptyReviews = new ArrayList<RatingReview>();

		Mockito.when(mockedServiceRepository.findOne(Mockito.any(String.class))).thenReturn(service);
		Mockito.when(mockedMapperRepository.findByService(Mockito.any(Service.class))).thenReturn(mapperObjects);
		Mockito.when(mockedRatingReviewRepository.findByServiceProvider(Mockito.any(ServiceProvider.class)))
				.thenReturn(emptyReviews).thenReturn(emptyReviews);
		Mockito.when(mockedfriendService.getFriendsReviews(Mockito.any(ArrayList.class), Mockito.any(String.class)))
				.thenReturn(emptyReviews).thenReturn(emptyReviews);

		List<CustomServiceProvider> customObjs = searchService.getSearchResult("u1", "r1", "1");

		assertEquals(0, customObjs.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSearchService() {
		User user = new User();
		user.setId("u1");

		Service service = new Service();
		service.setId("1");
		service.setName("serv1");

		ServiceProvider serviceProvider1 = new ServiceProvider();
		ServiceProvider serviceProvider2 = new ServiceProvider();
		serviceProvider1.setId("sp1");
		serviceProvider1.setRegionIdentifier("r1");
		serviceProvider2.setId("sp2");
		serviceProvider2.setRegionIdentifier("r1");

		ServiceToProviderMapper mapper1 = new ServiceToProviderMapper();
		ServiceToProviderMapper mapper2 = new ServiceToProviderMapper();
		mapper1.setId("m1");
		mapper2.setId("m2");
		mapper1.setService(service);
		mapper2.setService(service);
		mapper1.setServiceProvider(serviceProvider1);
		mapper2.setServiceProvider(serviceProvider2);

		List<ServiceToProviderMapper> mapperObjects = new ArrayList<ServiceToProviderMapper>();
		mapperObjects.add(mapper1);
		mapperObjects.add(mapper2);

		RatingReview review1 = new RatingReview();
		RatingReview review2 = new RatingReview();
		review1.setId("rw1");
		review2.setId("rw2");
		review1.setServiceProvider(serviceProvider2);
		review2.setServiceProvider(serviceProvider2);

		List<RatingReview> reviews = new ArrayList<RatingReview>();
		reviews.add(review1);
		reviews.add(review2);

		List<RatingReview> emptyReviews = new ArrayList<RatingReview>();

		Mockito.when(mockedServiceRepository.findOne(Mockito.any(String.class))).thenReturn(service);
		Mockito.when(mockedMapperRepository.findByService(Mockito.any(Service.class))).thenReturn(mapperObjects);
		Mockito.when(mockedRatingReviewRepository.findByServiceProvider(Mockito.any(ServiceProvider.class)))
				.thenReturn(emptyReviews).thenReturn(reviews);
		Mockito.when(mockedfriendService.getFriendsReviews(Mockito.any(ArrayList.class), Mockito.any(String.class)))
				.thenReturn(emptyReviews).thenReturn(reviews);

		List<CustomServiceProvider> customObjs = searchService.getSearchResult("u1", "r1", "1");
		assertEquals(2, customObjs.size());
		assertEquals(2, customObjs.get(0).getReviews().size());
		assertEquals(2, customObjs.get(0).getReviewsByFriends().size());
		assertEquals("rw1", customObjs.get(0).getReviews().get(0).getId());
		assertEquals(0, customObjs.get(1).getReviews().size());
		assertEquals(0, customObjs.get(1).getReviewsByFriends().size());

	}

}
