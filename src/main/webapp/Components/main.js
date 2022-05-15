$(document).ready(function () {
  if ($("#alertSuccess").text().trim() == "") {
    $("#alertSuccess").hide();
  }
  $("#alertError").hide();
});

// CLIENT-MODEL================================================================
function validateComplaintForm() {
  // CODE
  if ($("#customerName").val().trim() == "") {
    return "Insert Your Name.";
  }
  // NAME
  if ($("#phoneNumber").val().trim() == "") {
    return "Insert Your Phone Number.";
  }
  // PRICE-------------------------------
  if ($("#accountNumber").val().trim() == "") {
    return "Insert Your Account Number.";
  }
  // is numerical value
  var accNum = $("#accountNumber").val().trim();
  if (!$.isNumeric(accNum)) {
    return "Insert a numerical value for Account Number.";
  }
  // DESCRIPTION------------------------
  if ($("#message").val().trim() == "") {
    return "Insert Complaint Message.";
  }
  return true;
}
