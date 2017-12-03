package edu.nci.eazyserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nci.eazyserve.bean.CustomServiceProvider;
import edu.nci.eazyserve.service.SearchService;

@RestController
public class SearchController {

	@Autowired
	SearchService searchService;

	@RequestMapping(method = RequestMethod.GET, value = "/findServiceProviders/{userId}/{regionId}/{serviceId}")
	@CrossOrigin(origins = "http://localhost:8080")
	public List<CustomServiceProvider> getSearchResult(@PathVariable String userId, @PathVariable String regionId,
			@PathVariable String serviceId) {

		return searchService.getSearchResult(userId, regionId, serviceId);
	}
}
