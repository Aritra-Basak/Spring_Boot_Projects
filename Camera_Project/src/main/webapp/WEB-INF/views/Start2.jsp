<html>
<head>
    <title>Camera_Starting</title>
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
	<!-- Script and Link for Modal Implementation -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>

body
{
    display: flex;
    justify-content: center;
    align-items: center; 
   /*  background: rgb(0,138,255);
	background: linear-gradient(90deg, rgba(0,138,255,0.24422268907563027) 27%, rgba(5,189,219,0.4543067226890757) 58%); */
    background-image: linear-gradient(160deg, #8035f0 20%, #3bd9d9 100%); /*linear-gradient(direction, color-stop1, color-stop2); Stop is the limit till how much the colour will be affected*/
    font-family: 'Courier New', Courier, monospace;
    height: 100vh;
    background-repeat: no-repeat;
}
.mycard
{
    width: 100%;
    height:400px;
    max-width: 300px;
    border-radius: 8px;
    background: rgba(255, 255, 255, 0.05);
    backdrop-filter: blur(2px);
    border: 1px solid rgba(255,255,255,0.2);
    border-right-color:rgba(255,255,255,0.1);
    border-bottom-color: rgba(255, 255, 255, 0.1);
    box-shadow: 0 20px 30px rgba(0, 0, 0, 0.05);
    padding: 16px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}
.mycard,.mycard *
{
    box-sizing: border-box;
    transition: 400ms;
}

button {
  position: relative;
  padding: 10px 20px;
  border-radius: 7px;
  border: 1px solid rgb(61, 106, 255);
  font-size: 14px;
  text-transform: uppercase;
  font-weight: 600;
  letter-spacing: 2px;
  background: transparent;
  color: #fff;
  overflow: hidden;
  box-shadow: 0 0 0 0 transparent;
  -webkit-transition: all 0.2s ease-in;
  -moz-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
}

button:hover {
  background: rgb(61, 106, 255);
  box-shadow: 0 0 30px 5px rgba(0, 142, 236, 0.815);
  -webkit-transition: all 0.2s ease-out;
  -moz-transition: all 0.2s ease-out;
  transition: all 0.2s ease-out;
}

button:hover::before {
  -webkit-animation: sh02 0.5s 0s linear;
  -moz-animation: sh02 0.5s 0s linear;
  animation: sh02 0.5s 0s linear;
}

button::before {
  content: '';
  display: block;
  width: 0px;
  height: 86%;
  position: absolute;
  top: 7%;
  left: 0%;
  opacity: 0;
  background: #fff;
  box-shadow: 0 0 50px 30px #fff;
  -webkit-transform: skewX(-20deg);
  -moz-transform: skewX(-20deg);
  -ms-transform: skewX(-20deg);
  -o-transform: skewX(-20deg);
  transform: skewX(-20deg);
}

@keyframes sh02 {
  from {
    opacity: 0;
    left: 0%;
  }

  50% {
    opacity: 1;
  }

  to {
    opacity: 0;
    left: 100%;
  }
}

button:active {
  box-shadow: 0 0 0 0 transparent;
  -webkit-transition: box-shadow 0.2s ease-in;
  -moz-transition: box-shadow 0.2s ease-in;
  transition: box-shadow 0.2s ease-in;
}

    </style>
</head>

<body>





   <div class="mycard">
    <div class="mycard-img text-center">
        <h3 class="mycard-title fw-bold fs-2">OPD Aaddhaar Based Registration Counter</h3>
    </div>
    <div class="mycard-content" style="color:#FEFF86; font-size:13px">
        "Aadhaar a 12 digit individual identification number issued by the Unique Identification Authority of India on behalf of the Government of India.The number serves as a proof of identity and address, anywhere in India. Using this Aadhaar Card to modernize the Hospital OPD Booking System."
<!-- <button class="contactButton position-absolute bottom-50 end-50" onclick="Transfer()"> Capture Picture
  <div class="iconButton">
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path fill="none" d="M0 0h24v24H0z"></path><path fill="currentColor" d="M16.172 11l-5.364-5.364 1.414-1.414L20 12l-7.778 7.778-1.414-1.414L16.172 13H4v-2z"></path></svg>
  </div>
</button> -->
<div class="text-center my-4" data-bs-toggle="modal" data-bs-target="#myModal">
	<button>
    Start
</button>
</div>
   
   
    </div>
   </div>
   
      <!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header ">
        <h4 class="modal-title text-warning fw-bold headingmodal">Booking Confirmation</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body fw-bold fs-4 myModalBody">
       Currently do you have your Aadhaar Card with you ?
       
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      	<button type="button" id="mybutton" class="btn btn-success my-2 successbtn button1" data-bs-dismiss="modal" onclick="Transfer()" style="width:150px"><strong>Yes</strong></button>
        <button type="button" class="btn btn-danger my-2 successbtn button1" data-bs-dismiss="modal" style="width:150px"><strong>Close</strong></button>
        <button type="button"  class="btn btn-success my-2 successbtn button2" data-bs-dismiss="modal" onclick="chooseMethod1()" style="width:150px"><strong>QR Method</strong></button>
        <button type="button" class="btn btn-warning my-2 successbtn button2" data-bs-dismiss="modal" style="width:150px" onclick="chooseMethod2()"><strong>Photo Scan</strong></button>
       
      </div>

    </div>
  </div>
</div>

    <script>
    $(function(){
    	$(".button2").css("display","none");
    	
    });
    function chooseMethod1()
    {
    	var ctx = "${pageContext.request.contextPath}";
   	 
        window.location.href =ctx+ "/scanner";

    }
    function chooseMethod2()
    {
    	var ctx = "${pageContext.request.contextPath}";
   	 
        window.location.href =ctx+ "/hello";

    }
 function Transfer()
 {
	 $(".headingmodal").html("Choose Method");
	 $(".button1").css("display","none");
	 $(".button2").css("display","block");
	 $('#myModal').modal('show');
	 $(".myModalBody").html("Choose a method to proceed with your OPD Booking.")

 }
    
</script>
   
</body>
</html>