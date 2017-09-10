package com.paypal.demo.example.billingplan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.Plan;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.demo.example.base.Const;
import com.paypal.demo.example.base.Init;

/**
 * Servlet implementation class BillingAgreement
 */
public class BillingAgreement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillingAgreement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Plan> _plans = Init.InitBillingPlan();
		List<Plan> plans = new ArrayList<>();
		for (Plan plan : _plans){
			 try {
				 plans.add(plan.get(Const.PAYPAL_CONTEXT, plan.getId()));
			} catch (PayPalRESTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("plans", plans);
		request.getRequestDispatcher("/billing/billagrement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
