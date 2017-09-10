package com.paypal.demo.example.base;

public class URLs {
	
	public static String ONETIME_RESULT = "/OneTimePaymentResult";
	
	//Redirect URL on cancellation of agreement request
	public static String BILLING_PLAN_CANCEL = "http://localhost:8080/TestPayPal/CancelBillingAgreement";
	//Redirect URL on creation of agreement request
	public static String BILLING_PLAN_RETURN = "http://localhost:8080/TestPayPal/ProceedBillingAgreement";
	//Notify URL on agreement creation
	public static String BILLING_PLAN_NOTIFY = "http://localhost:8080/TestPayPal/CreatedBillingAgreement";

}
