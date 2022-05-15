$(document).ready(function () {
  if ($("#alertSuccess").text().trim() == "") {
    $("#alertSuccess").hide();
  }
  $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function (event) {
  // Clear alerts---------------------
  $("#alertSuccess").text("");
  $("#alertSuccess").hide();
  $("#alertError").text("");
  $("#alertError").hide();
  // Form validation-------------------
  var status = validateComplaintForm();
  if (status != true) {
    $("#alertError").text(status);
    $("#alertError").show();
    return;
  }
  // If valid------------------------
  var type = $("#hidComplaintIDSave").val() == "" ? "POST" : "PUT";
  $.ajax({
    url: "ComplaintsAPI",
    type: type,
    data: $("#formComplaint").serialize(),
    dataType: "text",
    complete: function (response, status) {
      onComplaintSaveComplete(response.responseText, status);
    },
  });
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function (event) {
  $("#hidComplaintIDSave").val($(this).data("complaintid"));
  $("#customerName").val($(this).closest("tr").find("td:eq(0)").text());
  $("#phoneNumber").val($(this).closest("tr").find("td:eq(1)").text());
  $("#accountNumber").val($(this).closest("tr").find("td:eq(2)").text());
  $("#message").val($(this).closest("tr").find("td:eq(3)").text());
});

$(document).on("click", ".btnRemove", function (event) {
  $.ajax({
    url: "ComplaintsAPI",
    type: "DELETE",
    data: "complaintID=" + $(this).data("complaintid"),
    dataType: "text",
    complete: function (response, status) {
      onComplaintDeleteComplete(response.responseText, status);
    },
  });
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

function onComplaintSaveComplete(response, status) {
  if (status == "success") {
    var resultSet = JSON.parse(response);
    if (resultSet.status.trim() == "success") {
      $("#alertSuccess").text("Successfully saved.");
      $("#alertSuccess").show();
      $("#divComplaintsGrid").html(resultSet.data);
    } else if (resultSet.status.trim() == "error") {
      $("#alertError").text(resultSet.data);
      $("#alertError").show();
    }
  } else if (status == "error") {
    $("#alertError").text("Error while saving.");
    $("#alertError").show();
  } else {
    $("#alertError").text("Unknown error while saving..");
    $("#alertError").show();
  }

  $("#hidComplaintIDSave").val("");
  $("#formComplaint")[0].reset();
}

function onComplaintDeleteComplete(response, status) {
  if (status == "success") {
    var resultSet = JSON.parse(response);
    if (resultSet.status.trim() == "success") {
      $("#alertSuccess").text("Successfully deleted.");
      $("#alertSuccess").show();
      $("#divComplaintsGrid").html(resultSet.data);
    } else if (resultSet.status.trim() == "error") {
      $("#alertError").text(resultSet.data);
      $("#alertError").show();
    }
  } else if (status == "error") {
    $("#alertError").text("Error while deleting.");
    $("#alertError").show();
  } else {
    $("#alertError").text("Unknown error while deleting..");
    $("#alertError").show();
  }
}
  