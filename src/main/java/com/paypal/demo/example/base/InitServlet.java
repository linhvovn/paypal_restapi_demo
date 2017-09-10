package com.paypal.demo.example.base;

import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class InitBillingPlan
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        Init.InitWebProfile();
        
//        Init.InitBillingPlan();
    }

}
