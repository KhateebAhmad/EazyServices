package edu.nci.eazyserve.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nci.eazyserve.bean.RatingReview;
import edu.nci.eazyserve.bean.User;
import edu.nci.eazyserve.constants.Constants;
import edu.nci.eazyserve.repository.UserRepository;
import net.sf.json.JSONSerializer;

@Service
public class FriendService {

	@Autowired
	private UserRepository userRepository;

	public List<RatingReview> getFriendsReviews(List<RatingReview> allReviews, String userId) {
		if (allReviews.size() == 0)
			return allReviews;

		List<RatingReview> friendsReviews = new ArrayList<RatingReview>();

		List<User> friends = getFriends(userId);

		for (RatingReview review : allReviews) {
			for (User friend : friends) {
				if (friend.getId().equals(review.getUser().getId())) {
					friendsReviews.add(review);
					break;
				}
			}
		}

		return friendsReviews;
	}

	private List<User> getFriends(String userId) {
		List<User> userFriends = new ArrayList<User>();
		User user = userRepository.findOne(userId);

		JSONObject json = (JSONObject) JSONSerializer.toJSON(getFriendsJSON(user.getFbId().toString()));

		JSONArray friendsObj = (JSONArray) JSONSerializer.toJSON(json.get("data"));

		if (friendsObj.size() == 0)
			return null;

		for (Object friendObj : friendsObj) {
			User friend = userRepository.findByFbId(((JSONObject) friendObj).getLong("id"));
			if (friend == null)
				continue;

			userFriends.add(friend);
		}

		return userFriends;
	}

	private String getFriendsJSON(String fbId) {
		String url = Constants.graphApiURL + fbId + Constants.friendQueryURL + Constants.accessToken;
		URL obj;
		StringBuilder response = new StringBuilder();
		;
		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response.toString();
	}

}
