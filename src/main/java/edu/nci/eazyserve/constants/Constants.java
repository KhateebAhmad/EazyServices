package edu.nci.eazyserve.constants;

public class Constants {
	public static String HASH_ALGORITHM = "SHA-256";
	
	public enum RoleType {
		USER, SERVICE_PROVIDER, ADMIN
	}

	public static String graphApiURL = "https://graph.facebook.com/v3.0/";

	public static String friendQueryURL = "/friends?access_token=";

	public static String accessToken = "EAACEdEose0cBAEOWCO3lBiMPAVzFzTJXLhBCVRd0CzNFwxyckRLPYc5ZBMyI9eJX0iQdfm4H3IPazbY3qvxPXsK2ars5ZAKnyZB59o7D4cZC5IZBwX3gPpbxlZCbNZAqJTO9kqnbecpLUtyOnhzFgSj3eOpwICZC1DifZCwT17LT1CPTSW5x3vGXDqujSoDa0hIT3hr4NniTB7AZDZD";

	public enum AppointmentStatus {
		OPEN(0), CLOSED(1), CANCELLED(2);
		private int statusVal;

		AppointmentStatus(int statusVal) {
			this.statusVal = statusVal;
		}

		public int getVal() {
			return statusVal;
		}
	}
	
	public enum PaymentStatus {
		PENDING(0), DONE(1);
		private int statusVal;

		PaymentStatus(int statusVal) {
			this.statusVal = statusVal;
		}

		public int getVal() {
			return statusVal;
		}
	}
}
