package edu.nci.eazyserve.constants;

public class Constants {
	public static String HASH_ALGORITHM = "SHA-256";
	
	public enum RoleType {
		USER, SERVICE_PROVIDER, ADMIN
	}

	public static String graphApiURL = "https://graph.facebook.com/v2.2/";

	public static String friendQueryURL = "/friends?access_token=";

	public static String accessToken = "EAACEdEose0cBAGNJDOblQ25p992bJsGnOMYZCg9k4Yov4ZBRhIcwbqehfOnZA9Iw5A15A5gvZAOHBJrIFPZBNeqk1oeSRqPZCqlWoMQv66WyjlfgUlV9viELIZBusQUCv55nkVLmJFegjupLcVcZABwJoVg4hSt4EcpXwAjstCdCE14hfZA7aNc9r8wpcKZBZAEfssZD";

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
