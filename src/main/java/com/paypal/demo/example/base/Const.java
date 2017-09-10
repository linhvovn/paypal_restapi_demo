package com.paypal.demo.example.base;

import com.paypal.api.payments.Plan;
import com.paypal.base.rest.APIContext;

public class Const {
	//sandbox
	public static final String clientID_test = "AVgySO6CgTVLpdiubsfffFYR7m7nqIgLgkU1EGbipB-mus0kKln4VCqhNB1hZnJVrWRkgnXj1dX9u7L1";
	public static final String clientSecret = "EBtE0LuT5UcbcKbl5Yho5y4JOUT_tLy0Dc6-B4Yq3DeqOovhilydHtzUSBY4rVGnPRbDTEEGsKlU8-cX";
	
	public static final APIContext PAYPAL_CONTEXT = new APIContext(clientID_test, clientSecret, "sandbox");
	//production
	public static final String clientID_prod = "AVgySO6CgTVLpdiubsfffFYR7m7nqIgLgkU1EGbipB-mus0kKln4VCqhNB1hZnJVrWRkgnXj1dX9u7L1";
	
	public static int PAYMENT_TYPE_ONE_TIME = 2;
	public static int TRANSACTION_SUCCESS = 1;
	public static int TRANSACTION_FAILED = 2;
	
	//Parameter create on start up
	public static String NO_SHIPPING_PROFILE_ID;
	public static int NO_OF_SAMPLE_PLAN = 3;
	public static Plan[] SAMPLE_PLAN = new Plan[3];
	
}
