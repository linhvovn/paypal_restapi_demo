package com.paypal.demo.example.onetime;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.ChargeModels;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentDetail;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalModel;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.demo.example.base.Const;
import com.paypal.demo.mockup.func.PerfomUpdate;

/**
 * Servlet implementation class OneTimePaymentResult
 */
public class OneTimePaymentResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneTimePaymentResult() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.get payment information from request
		String paymentID = request.getParameter("paymentID");
		
		//2.retrieve payment - transaction info from PayPal
		try {
			Payment payment = Payment.get(Const.PAYPAL_CONTEXT, paymentID);
			for (Transaction transaction: payment.getTransactions()){
				String custom[] = transaction.getCustom().split(",");
				String studentEmail = custom[0];
				String studentName = custom[1];
				
				PerfomUpdate.NyuukinShori(studentEmail, studentName, Const.PAYMENT_TYPE_ONE_TIME, paymentID, 
						transaction.getDescription(), Double.parseDouble(transaction.getAmount().getTotal()), Const.TRANSACTION_SUCCESS, payment.getCreateTime());
			}
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/onetime/success.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
