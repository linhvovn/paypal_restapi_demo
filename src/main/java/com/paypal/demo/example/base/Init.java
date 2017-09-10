package com.paypal.demo.example.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paypal.api.payments.Currency;
import com.paypal.api.payments.InputFields;
import com.paypal.api.payments.MerchantPreferences;
import com.paypal.api.payments.PaymentDefinition;
import com.paypal.api.payments.Plan;
import com.paypal.api.payments.PlanList;
import com.paypal.api.payments.WebProfile;
import com.paypal.base.rest.PayPalRESTException;

public class Init {
	
	/**
	 * Create Web Profile to remove shipping address
	 * More info here: https://developer.paypal.com/docs/api/payment-experience/
	 */
	public static void InitWebProfile(){
		WebProfile profile = new WebProfile();
		
		try {
			//load existing profile
			List<WebProfile> existingProfile = profile.getList(Const.PAYPAL_CONTEXT);
			
			//if found, get first profile
			if (existingProfile != null && !existingProfile.isEmpty()){
				Const.NO_SHIPPING_PROFILE_ID = existingProfile.get(0).getId();
			}else{
				//create new profile
				profile.setName("Thai Language Class Profile 1");
				InputFields fields = new InputFields();
				fields.setNoShipping(1);
				fields.setAddressOverride(1);
				profile.setInputFields(fields);
				profile.create(Const.PAYPAL_CONTEXT);
				Const.NO_SHIPPING_PROFILE_ID = profile.getId();
			}
		} catch (PayPalRESTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Create Sample Billing Plan or load avaliable plans from server
	 */
	public static List<Plan> InitBillingPlan(){
		List<Plan> plans = loadExistingPlan();
		if (plans == null || plans.isEmpty()){
			createNewSamplePlans();
		}
		return plans;
	}
	
	
	/**
	 * Try to load created plans from server
	 * @return
	 */
	static List<Plan> loadExistingPlan() {
		// try to load current plans
		PlanList list = new PlanList();
		Map<String, String> container = new HashMap<String, String>();

		// https://developer.paypal.com/docs/api/payments.billing-plans/#plan_list
		// container.put("status", "CREATED");

		try {
			list = Plan.list(Const.PAYPAL_CONTEXT, container);
		} catch (PayPalRESTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return list != null ? list.getPlans() : null;
	}
	
	/**
	 * Create new sample plans
	 * @return
	 */
	static List<Plan> createNewSamplePlans(){
		List<Plan> plans = new ArrayList<>();
		
			//2. No plan found, try to create sample plans
			for (int i=0;i< Const.NO_OF_SAMPLE_PLAN; ++i){
				// Build Plan object
				Plan plan = new Plan();
				plan.setName("Language Month Club Plan"+i);
				plan.setDescription("Template creation."+i);
				plan.setType("fixed");

				// payment_definitions
				PaymentDefinition paymentDefinition = new PaymentDefinition();
				paymentDefinition.setName("Regular Payments"+i);
				paymentDefinition.setType("REGULAR");
				paymentDefinition.setFrequency("DAY");
				paymentDefinition.setFrequencyInterval("1");
				paymentDefinition.setCycles("12");

				// currency
				Currency currency = new Currency();
				currency.setCurrency("USD");
				currency.setValue(String.valueOf(10*(i+1)));
				paymentDefinition.setAmount(currency);

				// payment_definition
				List<PaymentDefinition> paymentDefinitionList = new ArrayList<PaymentDefinition>();
				paymentDefinitionList.add(paymentDefinition);
				plan.setPaymentDefinitions(paymentDefinitionList);

				// merchant_preferences
				MerchantPreferences merchantPreferences = new MerchantPreferences();
				merchantPreferences.setSetupFee(currency);
				merchantPreferences.setCancelUrl(URLs.BILLING_PLAN_CANCEL);
				merchantPreferences.setReturnUrl(URLs.BILLING_PLAN_RETURN);
//				merchantPreferences.setNotifyUrl(URLs.BILLING_PLAN_NOTIFY);
				merchantPreferences.setMaxFailAttempts("0");
				merchantPreferences.setAutoBillAmount("YES");
				merchantPreferences.setInitialFailAmountAction("CONTINUE");
				plan.setMerchantPreferences(merchantPreferences);

				try {
					plan.create(Const.PAYPAL_CONTEXT);
					plans.add(plan);
				} catch (PayPalRESTException e) {
					e.printStackTrace();
				}
			}
			return plans;
	}

}
