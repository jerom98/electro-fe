<%@page import="com.Complaint"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Complaints Management</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h2>Complaints</h2>
				<form id="formComplaint" name="formComplaint">
					Your Name:<input id="customerName" name="customerName" type="text"
						class="form-control form-control-sm"><br> Phone
					Number: <input id="phoneNumber" name="phoneNumber" type="number"
						class="form-control form-control-sm"><br> Account
					Number: <input id="accountNumber" name="accountNumber"
						type="number" class="form-control form-control-sm"><br>
					Message : <input id="message" name="message" type="text"
						class="form-control form-control-sm"><br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divComplaintsGrid">
					<%
					Complaint complaintObj = new Complaint();
					out.print(complaintObj.getComplaints());
					%>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<script src="Components/main.js"></script>
</body>

</html>