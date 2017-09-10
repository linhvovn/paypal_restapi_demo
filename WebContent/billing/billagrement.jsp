<%@page import="com.paypal.api.payments.Currency"%>
<%@page import="com.paypal.demo.example.base.Const"%>
<%@ page import=" java.util.List" %>
<%@ page import="com.paypal.api.payments.Plan" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Billing Agremment</title>
</head>
<body>
<h2>Billing List</h2>
<label>Select plan</label>
<select>
<%
  List<Plan> plans = (List<Plan>)request.getAttribute("plans");
  
  for (int i=0; i<plans.size();++i){
	  Plan plan = plans.get(i);
	  Currency currency = plan.getPaymentDefinitions().get(0).getAmount();
	  %>
	   <option value='<%=plan.getId()%>'><%=plan.getName()%>(<%=currency.getCurrency()%> <%=currency.getValue()%>) </option>
	  <%
  }

%>
</select>
</body>
</html>