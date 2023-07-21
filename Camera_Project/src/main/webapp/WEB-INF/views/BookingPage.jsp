<html>
<head>
    <title>Booking_Page</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<style>
	.formbg{
    background-color:#c5e3f6;

}

.name_row{
    display: flex;
    align-items: center;
}

.container_form{
    max-width: 50%;
    margin: auto;
    border-radius: 25px;
}

.overlay_img2{
    position: absolute;
    top: 360px;
    left: 40px;
}

h3{
    color: #002651;
}

.form-row{
    padding-left: 1.5rem;
}
	
	</style>
</head>
<body>
 <div class="container_form pt-5 px-5 pb-4 formbg">
        <div>
            <form>
                <h3 class="mb-n2"><b>Basic Details</b></h3>
                <hr>
                <div class="form-row">
                  <div class="form-group col-md-11">
                    <label for="patient_firstname">First Name</label>
                    <input type="text" class="form-control" id="" placeholder="Enter your first name" readonly>
                  </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-11">
                      <label for="patient_mobile">Contact Details</label>
                      <input type="text" class="form-control" id="" placeholder="Mobile Number" readonly>
                    </div>
                  </div>

                <div class="form-row">
                  <div class="form-group col-md-11">
                    <label for="aadhar">Aadhar</label>
                    <input type="text" class="form-control" id="aadhar" placeholder="Enter Aadhar Number" readonly>
                  </div>
                </div>

                <!-- <div class="form-group">
                    <label for="patient_dob">Select your birth date :</label>
                    <br>

                    <input type="date" id="selected_dob"
                    name="patient_dob" value="2023-02-27"
                    min="1900-01-01" max="2023-02-27" onsclick="ageCalc()">

                </div> -->
                <div class="form-row">
                  <div class="form-group col-md-11">
                    <label for="patient_dob">Date</label>
                    <input type="text" class="form-control" id="patient_dob" placeholder="Enter Date of Birth" readonly> 
                  </div>
                </div>

                <div class="form-row">
                  <div class="form-group col-md-11">
                    <label for="ticketNo">Ticket Number</label>
                    <input type="text" class="form-control" id="ticketNo" placeholder="Enter Date of Birth" readonly>
                  </div>
                </div>

                <h3 class="mb-n2" style="margin-top: 1rem;"><b>Booked Department</b></h3>
                <hr>

                <div class="form-row">
                  <div class="form-group col-md-3">
                    <button type="button" class="btn btn-secondary" style="width: 90%;">Neurology</button>
                  </div>
                  <div class="form-group col-md-3">
                    <button type="button" class="btn btn-secondary" style="width: 90%;">Cardiology</button>
                  </div>
                  <div class="form-group col-md-3">
                    <button type="button" class="btn btn-primary" style="width: 90%;">ENT</button>
                  </div>
                  <div class="form-group col-md-3">
                    <button type="button" class="btn btn-secondary" style="width: 90%;">General</button>
                  </div>
                </div>
                <button type="submit" class="btn btn-info mt-3 mb-2">Submit</button>
              </form>
        </div>
        
      </div>

    



</body>
</html>