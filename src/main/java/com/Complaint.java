package com;

import java.sql.*;

public class Complaint {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/complaints", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String getComplaints() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Custom Name</th><th>Phone number</th>"
					+ "<th>Account number</th><th>Message</th<th>Update</th><th>Remove</th></tr>";
			String query = "select * from complaint";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String complaintID = Integer.toString(rs.getInt("complaintID"));
				String name = rs.getString("name");

				String phoneNumber = rs.getString("phoneNumber");
				String accountNumber = Integer.toString(rs.getInt("accountNumber"));
				String message = rs.getString("message");
				// Add into the html table
				output += "<tr><td><input id='hidComplaintIDUpdate' name='hidComplaintIDUpdate' type='hidden' value='" + complaintID
						+ "'>" + name + "</td>";
				output += "<td>" + phoneNumber + "</td>";
				output += "<td>" + accountNumber + "</td>";
				output += "<td>" + message + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-complaintid='" + complaintID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger'data-complaintid='"
						+ complaintID + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the complaints.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String addComplaint(String name, String phoneNumber, String accountNumber, String message) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into complaint(`name`,`phoneNumber`,`accountNumber`,`message`)"

					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, phoneNumber);
			preparedStmt.setInt(3, Integer.parseInt(accountNumber));
			preparedStmt.setString(4, message);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newComplaints = getComplaints();
			output = "{\"status\":\"success\", \"data\": \"" + newComplaints + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the complaint.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateComplaint(String complaintID, String name, String phoneNumber, String accountNumber, String message) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE complaint SET name=?,phoneNumber=?,accountNumber=?,message=? WHERE complaintID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, phoneNumber);
			preparedStmt.setInt(3, Integer.parseInt(accountNumber));
			preparedStmt.setString(4, message);
			preparedStmt.setInt(5, Integer.parseInt(complaintID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newComplaints = getComplaints();
			output = "{\"status\":\"success\", \"data\": \"" + newComplaints + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the complaint.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteComplaint(String complaintID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from complaint where complaintID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(complaintID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newComplaints = getComplaints();
			output = "{\"status\":\"success\", \"data\": \"" + newComplaints + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the complaint.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}