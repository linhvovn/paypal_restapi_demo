package com.paypal.demo.mockup.func;

public class PerfomUpdate {
	/**
	 * Call when create new payment
	 * @param studentEmail
	 * @param studentName
	 * @param paymentType
	 * @param paymentID
	 * @param paymentProductName
	 * @param paymentAmount
	 * @param paymentStatus
	 * @param paymentDate
	 */
	public static void NyuukinShori(String studentEmail, String studentName, int paymentType, String paymentID, String
			paymentProductName, double paymentAmount, int paymentStatus, String paymentDate){
		
		System.out.println("Call NyuukinShori");
		System.out.println(studentEmail);
		System.out.println(studentName);
		System.out.println(paymentType);
		System.out.println(paymentID);
		System.out.println(paymentProductName);
		System.out.println(paymentAmount);
		System.out.println(paymentStatus);
		System.out.println(paymentDate);
		System.out.println("End Call NyuukinShori");
	}
}
