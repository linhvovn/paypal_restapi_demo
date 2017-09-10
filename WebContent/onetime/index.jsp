<%@ page import="com.paypal.demo.example.base.Const" %>
<%@ page import="com.paypal.demo.example.base.URLs" %>
<html>
<head>
 	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <script src="https://www.paypalobjects.com/api/checkout.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
<h2>One time payment</h2>

 <form>
    <label>Amount</label>
 	<input id="amount" type="number" value="1"><br/>
 	<label>Currency</label>
 	<input id="currency" type="text" value="USD"><br/>
 	
 	<label>Description</label>
 	<input id="des" type="text" value="Pay for tuition fee"><br/>
 	
	<!--  	this information should get from session -->
	<p>--Below information should get from session in real application</p>
 	<label>Student Email</label>
 	<input id="email" type="text" value="student@mail.com"><br/>
 	<label>Student Name</label>
 	<input id="name" type="text" value="Student One"><br/>
 	
 </form>
 <div id="paypal-button-container"></div>
 
    <script>
        paypal.Button.render({
            env: 'sandbox', // sandbox | production
            // PayPal Client IDs - replace with your own
            // Create a PayPal app: https://developer.paypal.com/developer/applications/create
            client: {
                sandbox:    '<%=Const.clientID_test %>',
                production: '<%=Const.clientID_prod %>'
            },

            // Show the buyer a 'Pay Now' button in the checkout flow
            commit: true,

            // payment() is called when the button is clicked
            payment: function(data, actions) {

                // Make a call to the REST api to create the payment
                return actions.payment.create({
                    payment: {
                        transactions: [
                            {
                                amount: { total: $("#amount").val(), currency: $("#currency").val() },
                                description: $("#des").val(),
                                custom: $("#email").val()+","+$("#name").val()
                            }
                        ],
                        experience_profile_id: "<%=Const.NO_SHIPPING_PROFILE_ID%>"
                    }
                });
            },

            // onAuthorize() is called when the buyer approves the payment
            onAuthorize: function(data, actions) {
                // Make a call to the REST api to execute the payment
                console.log(data);
                return actions.payment.execute().then(function() {
                	window.location.href="<%=request.getContextPath()+URLs.ONETIME_RESULT%>"+"?paymentID="+data['paymentID']
                });
            }

        }, '#paypal-button-container');

    </script>
</body>
</html>
