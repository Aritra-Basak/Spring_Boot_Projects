<html>
<head>
    <title>Camera</title>
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
       * {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
body {
  height: 100vh;
  width: 100vw;
  background: #E3F4F4;
  overflow: scroll;
}
.webcam{
		 	/*  display: grid;
			  grid-template-columns: auto auto auto; */
			  
			  padding: 10px;
           	margin-top:2%;
           	background: #E3F4F4;
            
            }
			video {
           width: 400px;
		    height: 296px;
		    margin-top: 2%;
		    border-radius: 10px;

           
       	 }
       	 .grid-item
       	 {
       	  background-color: rgba(255, 255, 255, 0.8);
		  border: 0px solid rgba(0, 0, 0, 0.8);
		  border-radius:10px;
		 width:460px;
		 height: 575px;
		  font-size: 30px;
		  text-align: center;
		  grid-auto-columns: 1fr;
   		  grid-auto-flow: column;
   		  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
		  }
        canvas {
            display: none;
        }
        .webcam-start-stop {
		  position: fixed;
		  left: 0;
		  bottom: 0;
		  right: 0;
		  padding: 5px 0;
		  background: #000;
		  display: flex;
		  align-items: center;
		  justify-content: space-around;
}
.webcam-start-stop > a {
  text-decoration: unset;
  color: #000;
  background: #fff;
  padding: 10px 20px;
}
#res
{
    display: flex;
    justify-content: center;
    align-items: center;
    
}
#res img
{
			width: 400px;
		    height: 296px;
		    margin-top: 2%;
		    border-radius: 10px;
}
.button {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 9px 12px;
  gap: 8px;
  height: 40px;
  width: 172px;
  border: none;
  background: #FF2849;
  border-radius: 20px;
  cursor: pointer;
}

.lable {
  line-height: 22px;
  font-size: 17px;
  color: #fff;
  font-family: sans-serif;
  letter-spacing: 1px;
}

.button:hover {
  background: #e52441;
}

.button:hover .svg-icon {
  animation: flickering 2s linear infinite;
}

@keyframes flickering {
  0% {
    opacity: 1;
  }

  50% {
    opacity: 1;
  }

  52% {
    opacity: 1;
  }

  54% {
    opacity: 0;
  }

  56% {
    opacity: 1;
  }

  90% {
    opacity: 1;
  }

  92% {
    opacity: 0;
  }

  94% {
    opacity: 1;
  }

  96% {
    opacity: 0;
  }

  98% {
    opacity: 1;
  }

  99% {
    opacity: 0;
  }

  100% {
    opacity: 1;
  }
}

/* Dot Spiner */
.dot-spinner {
  --uib-size: 2.8rem;
  --uib-speed: .9s;
  --uib-color: #183153;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  height: var(--uib-size);
  width: var(--uib-size);
}

.dot-spinner__dot {
  position: absolute;
  top: 0;
  left: 0;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  height: 100%;
  width: 100%;
}

.dot-spinner__dot::before {
  content: '';
  height: 20%;
  width: 20%;
  border-radius: 50%;
  background-color: var(--uib-color);
  transform: scale(0);
  opacity: 0.5;
  animation: pulse0112 calc(var(--uib-speed) * 1.111) ease-in-out infinite;
  box-shadow: 0 0 20px rgba(18, 31, 53, 0.3);
}

.dot-spinner__dot:nth-child(2) {
  transform: rotate(45deg);
}

.dot-spinner__dot:nth-child(2)::before {
  animation-delay: calc(var(--uib-speed) * -0.875);
}

.dot-spinner__dot:nth-child(3) {
  transform: rotate(90deg);
}

.dot-spinner__dot:nth-child(3)::before {
  animation-delay: calc(var(--uib-speed) * -0.75);
}

.dot-spinner__dot:nth-child(4) {
  transform: rotate(135deg);
}

.dot-spinner__dot:nth-child(4)::before {
  animation-delay: calc(var(--uib-speed) * -0.625);
}

.dot-spinner__dot:nth-child(5) {
  transform: rotate(180deg);
}

.dot-spinner__dot:nth-child(5)::before {
  animation-delay: calc(var(--uib-speed) * -0.5);
}

.dot-spinner__dot:nth-child(6) {
  transform: rotate(225deg);
}

.dot-spinner__dot:nth-child(6)::before {
  animation-delay: calc(var(--uib-speed) * -0.375);
}

.dot-spinner__dot:nth-child(7) {
  transform: rotate(270deg);
}

.dot-spinner__dot:nth-child(7)::before {
  animation-delay: calc(var(--uib-speed) * -0.25);
}

.dot-spinner__dot:nth-child(8) {
  transform: rotate(315deg);
}

.dot-spinner__dot:nth-child(8)::before {
  animation-delay: calc(var(--uib-speed) * -0.125);
}

@keyframes pulse0112 {
  0%,
  100% {
    transform: scale(0);
    opacity: 0.5;
  }

  50% {
    transform: scale(1);
    opacity: 1;
  }
}

#loader
{
position:relative;
top:35%;
left:49%;
}


#refreshbtn {
  color: white;
  background-color: #222;
  font-weight: 500;
  border-radius: 0.5rem;
  font-size: 1rem;
  line-height: 2rem;
  padding-left: 2rem;
  padding-right: 2rem;
  padding-top: 0.7rem;
  padding-bottom: 0.7rem;
  text-align: center;
  margin-right: 0.5rem;
  display: inline-flex;
  align-items: center;
  border: none;
}

#refreshbtn:hover {
  background-color: #333;
}

#refreshbtn svg {
  display: inline;
  width: 1.3rem;
  height: 1.3rem;
  margin-right: 0.75rem;
  color: white;
}

#refreshbtn:focus svg {
  animation: spin_357 0.5s linear;
}

@keyframes spin_357 {
  from {
    transform: rotate(0deg);
  }

  to {
    transform: rotate(360deg);
  }
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


/* Tooltip */
.mytooltip {
display: inline;
position: relative;
z-index: 999
}
.mytooltip .tooltip-item {
background: rgba(0, 0, 0, 0.1);
cursor: pointer;
display: inline-block;
font-weight: 500;
padding: 0 10px
}
.mytooltip .tooltip-content {
position: absolute;
z-index: 9999;
width: 360px;
left: 50%;
margin: 0 0 20px -180px;
bottom: 100%;
text-align: left;
font-size: 14px;
line-height: 30px;
-webkit-box-shadow: -5px -5px 15px rgba(48, 54, 61, 0.2);
box-shadow: -5px -5px 15px rgba(48, 54, 61, 0.2);
background: #2b2b2b;
opacity: 0;
cursor: default;
pointer-events: none
}
.mytooltip .tooltip-content::after {
content: '';
top: 100%;
left: 50%;
border: solid transparent;
height: 0;
width: 0;
position: absolute;
pointer-events: none;
border-color: #2a3035 transparent transparent;
border-width: 10px;
margin-left: -10px
}
.mytooltip .tooltip-content img {
position: relative;
height: 140px;
display: block;
float: left;
margin-right: 1em
}
.mytooltip .tooltip-item::after {
content: '';
position: absolute;
width: 360px;
height: 20px;
bottom: 100%;
left: 50%;
pointer-events: none;
-webkit-transform: translateX(-50%);
transform: translateX(-50%)
}
.mytooltip:hover .tooltip-item::after {
pointer-events: auto
}
.mytooltip:hover .tooltip-content {
pointer-events: auto;
opacity: 1;
-webkit-transform: translate3d(0, 0, 0) rotate3d(0, 0, 0, 0deg);
transform: translate3d(0, 0, 0) rotate3d(0, 0, 0, 0deg)
}
.mytooltip:hover .tooltip-content2 {
opacity: 1;
font-size: 18px
}
.mytooltip .tooltip-text {
font-size: 14px;
line-height: 24px;
display: block;
padding: 1.31em 1.21em 1.21em 0;
color: #fff
}

    </style>
</head>

<body>
<div class="text-center">
	
</div>

<div class="d-flex ">

<!-- <button class="button" onclick="startAll()" id="mainButton">
  <svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" height="24" fill="none" class="svg-icon"><g stroke-width="2" stroke-linecap="round" stroke="#fff" fill-rule="evenodd" clip-rule="evenodd"><path d="m4 9c0-1.10457.89543-2 2-2h2l.44721-.89443c.33879-.67757 1.03131-1.10557 1.78889-1.10557h3.5278c.7576 0 1.4501.428 1.7889 1.10557l.4472.89443h2c1.1046 0 2 .89543 2 2v8c0 1.1046-.8954 2-2 2h-12c-1.10457 0-2-.8954-2-2z"></path><path d="m15 13c0 1.6569-1.3431 3-3 3s-3-1.3431-3-3 1.3431-3 3-3 3 1.3431 3 3z"></path></g></svg>
  <span class="lable">Take a Photo</span>
</button>
 -->

</div>


<!-- <button id="captureButton" onclick="stopCamera()" class="btn btn-success my-4">Stop</button> -->
   <div class="webcam d-flex justify-content-evenly">
        <div class="grid-item">
        	<h1 id="mainheader">Camera</h1>
        	<div id ="res"></div>
            <video id="videoElement"></video>
            <div id="instructions" class="fw-bold" style="font-size:12px;margin-top:140px">
				<h4>Instructions</h4>
	            <ul>
	            	<li>Hold the Aadhaar Card infront of the camera till the timer stops.</li>
	            	<li>টাইমার বন্ধ না হওয়া পর্যন্ত ক্যামেরার সামনে আধার কার্ডটি ধরে রাখুন। </li>
	            </ul>
	            <span class="mytooltip tooltip-effect-1">
				<span class="tooltip-item text-danger">
			Help
				
				</span>
				<span class="tooltip-content clearfix">
				<img src="/images/example.jpg">
				<span class="tooltip-text text-danger" style="font-size:10px">Hold your Aadhaar Card infront of the camera like this.</span>
				</span>
			</span>
            </div> 
             
            
        </div>
        <div class="grid-item">
        	<div>
			    <h1 id="countHeader">Countdown Timer</h1>
			    <div style="background-color: #C1ECE4;margin-top:4%">
			        <p id="timerDisplay" class="fw-bold" style="font-size: 9rem;">00:07</p>
			    </div>
        	</div>
        </div>
       <div class="grid-item">
       		<h1>Details</h1>
      
       <div id="loader">
			       <div class="dot-spinner">
					    <div class="dot-spinner__dot"></div>
					    <div class="dot-spinner__dot"></div>
					    <div class="dot-spinner__dot"></div>
					    <div class="dot-spinner__dot"></div>
					    <div class="dot-spinner__dot"></div>
					    <div class="dot-spinner__dot"></div>
					    <div class="dot-spinner__dot"></div>
					    <div class="dot-spinner__dot"></div>
					</div>
       </div>
       <form:form id="docForm" action="${pageContext.request.contextPath}/" method="POST">
       					<div class="d-flex flex-column" style="margin:7%" id="detailsForm">
								<div class="mb-3" >
										  <label class="form-label" style="font-size: 20px;margin-left: -85%;">Name</label>
										  <input type="text" class="form-control" id="patientName" name="patientName" readonly>
								</div>
								<div class="mb-3" >
										  <label  class="form-label" style="font-size: 20px;margin-left: -225px;">Aadhaar Number<i class="fa fa-asterisk" style="color:red">*</i></label>
										  <input type="number" class="form-control" id="aadhaarNumber" name="aadhaarNumber" readonly>
								</div>
								<div class="mb-3" >
										  <label class="form-label" style="font-size: 20px;margin-left: -88%;">DOB<i class="fa fa-asterisk" style="color:red">*</i></label>
										  <input type="text" class="form-control" id="dOb" name="dOb" readonly>
								</div>
								
								<div class="mb-3" >
										  <label class="form-label" style="font-size: 20px;margin-left: -81%;">Gender<i class="fa fa-asterisk" style="color:red">*</i></label>
										  <input type="text" class="form-control" id="gender" name="gender" readonly >
								</div>
								<div class="mb-3" >
										  <label class="form-label" style="font-size: 20px;margin-left: -251px;">Phone Number</label>
										  <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" maxlength="10" autocomplete="off">
								</div>
								<div id="departmentButton" style="background-colour:">
								<h3 class="form-label" style="font-size: 20px;">Select OPD Department<i class="fa fa-asterisk" style="color:red">*</i></h3>
								<div >
											<button type="button" class="btn btn-primary" onclick="BookAppt(1)" style="width:80px">General</button>
											<button type="button" class="btn btn-success" onclick="BookAppt(2)" style="width:80px">Neurology</button>
											<button type="button" class="btn btn-warning" onclick="BookAppt(3)" style="width:80px">Cardiology</button>
											<button type="button" class="btn btn-info" onclick="BookAppt(4)" style="width:80px">ENT</button>
								</div>
								</div>
							
						</div>
	</form:form>
    </div>
   </div>
   <!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header ">
        <h4 class="modal-title text-warning">Booking Confirmation</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body fw-bold fs-3">
       
       
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      	<button type="button" id="mybutton" class="btn btn-success my-2 successbtn" data-bs-dismiss="modal" onclick="takeMeBack()" style="width:150px"><strong>Ok</strong></button>
        <button type="button" class="btn btn-danger my-2 successbtn" data-bs-dismiss="modal" style="width:150px"><strong>Close</strong></button>
        <button type="button" id="mybutton" class="btn btn-info my-2 failurebtn" data-bs-dismiss="modal" onclick="reloadmypage()" style="width:150px" ><strong>Try Again</strong></button>
        <button type="button" id="mybutton" class="btn btn-danger my-2 failurebtn" data-bs-dismiss="modal" onclick="takeMeBack()" style="width:150px" ><strong>Back to Home</strong></button>
      </div>

    </div>
  </div>
</div>


    <script>

    	let xclarity=0;
    	let mediaStream;
    	let isFunctionRunning = true;
    	let intervalId;
    	var canvas;
    	var context;
    	var video;
    	var videoBox=document.getElementById("videoElement");
    $(function(){
    	
    	startCamera();
    	$("#loader").css("display","none");
    	$("#refreshbtn").css("display","none");
    	$("#mainButton").css("display","block");
    	$('#departmentButton').css("display","none");
    	startAll();
    	  $('#phoneNumber').keydown(function(e) { 
              if (e.shiftKey)
                  e.preventDefault();
              var nKeyCode = e.keyCode;
              //Ignore Backspace and Tab keys
              if (nKeyCode === 8 || nKeyCode === 9)
                 return;
             var regex="[0-9]+";
             if (!(e.key.match(regex)))
             {
              alert("Enter Phone Number Properly, Only numbers are allowed.");
              return false;         	        
             }
              $('#'+this.id).removeClass('show');

          });
    	
    });
		function startCamera() 
		{
		// Access the webcam and capture snapshots
		navigator.mediaDevices.getUserMedia({ video: true })
		.then(function (stream) {
		    // Create a video element to display the video stream
		        video = document.getElementById('videoElement');
		        video.srcObject = stream;
		        mediaStream = stream;
		        video.play();
		
		        // Create a canvas element to draw the captured snapshots
		        canvas = document.createElement('canvas');
		         context = canvas.getContext('2d');
		        context.drawImage(video, 0, 0, canvas.width, canvas.height);
		
		
		    // Define the clarity threshold for capturing the photo (adjust as needed)
		     // var clarityThreshold = 15;
		    // Capture a snapshot automatically when the image is clear
	
		       
		})
		.catch(function (error) {
		    console.error('Error accessing the webcam', error);
		});
		}
		function startAll()
		{
			setTimeout(captureSnapshot,7000);
	  		var countdownDuration = 7;

	  		// Function to update the timer and display the countdown
	  		function updateTimer() {
	  		    const timerDisplay = document.getElementById('timerDisplay');
	  		   // const currentTime = parseInt(timerDisplay.innerText);

	  		    // Decrease the timer by 1 second
	  		   countdownDuration = countdownDuration - 1;

	  		    // Update the display with leading zeros if needed
	  		    const formattedTime = String(countdownDuration).padStart(2, '0');

	  		    // Display the countdown in the HTML element
	  		   $('#timerDisplay').html("00:"+formattedTime);

	  		    // Check if the timer has reached zero
	  		    if (countdownDuration <= 0) {
	  		        clearInterval(interval);
	  		        timerDisplay.innerText = 'Time\'s up!';
	  		      	stopCamera();
	  		    }
	  		}

	  		// Update the timer every second (1000 milliseconds)
	  		const interval = setInterval(updateTimer, 1000);
	  		$("#loader").css("display","block");
	  		$("#docForm").css("display","none");
		    
		}
	      function captureSnapshot() 
	      {
	            canvas.width = video.videoWidth;
	            canvas.height = video.videoHeight;
	         	// Draw the image on the canvas
	            context.drawImage(video, 0, 0, canvas.width, canvas.height);
	            var imageDataUrl = canvas.toDataURL('image/png');
	        	 // Get the image data from the canvas
	            var imageData = context.getImageData(0, 0, canvas.width, canvas.height).data;
	            var imageClarity = calculateImageClarity(imageData);
	            console.log(imageClarity);
	             var capturedImage = new Image();
	                capturedImage.src = canvas.toDataURL('image/png');
	                	
	            	 // Send the captured snapshot to the server
			               var ctx = "${pageContext.request.contextPath}/documents/upload";
			                $.ajax({
			                    url: ctx,
			                    method: 'POST',
			                    data: JSON.stringify({ imageBase64: imageDataUrl }),
			                    contentType: 'application/json',
			                    success: function(response) {
			                        // If the response received from the backend server is successful, proceed.
			                        $("#res").html(capturedImage);
			                        xclarity = imageClarity;
			                        console.log("Xclarity is:---" + xclarity);
			                        console.log("success msg : " + JSON.stringify(response));
			                        console.log(capturedImage);
			                        stopCamera();
			                        $("#loader").css("display", "none");
			                        $("#docForm").css("display","block");
			                        $("#refreshbtn").css("display","block");
			                        $("#mainButton").css("display","none");
			                        
			                        if(response.status==="Success")
			                        	{
					                        $('#patientName').val(response.resObject.name);
					                        $('#aadhaarNumber').val(response.resObject.aadhaarNumber);
					                        $('#dOb').val(response.resObject.dob);
					                        $('#gender').val(response.resObject.gender);
					                        $('#mainheader').html("Preview");
					                        $('#departmentButton').css("display","block");
					                        videoBox.remove();
					                        $('.failurebtn').css("display","none");
					                        var innerHtml='Success! <br> <span style="font-size:2rem">Now you can proceed with the next step.</span>'
					                        $('#timerDisplay').html(innerHtml);
					                        $("#countHeader").html("Message.");
			                        	}
			                        else
			                        	{
				                        	$('.modal-body').html(response.responseMessage);
				                        	$('.failurebtn').css("display","block");
				                        	$('.successbtn').css("display","none");
				                        	$('.modal-title').html("Confirmation");
				                        	$('#myModal').modal('show');
			                        	}
			                        
			                        
			                    },
			                    error: function(jqXHR, textStatus, errorThrown) {
			                    	$('.modal-body').html('Error fetching the document. Please re-start');
			                    	$('.failurebtn').css("display","block");
		                        	$('.successbtn').css("display","none");
		                        	$('.modal-title').html("Confirmation");
		                        	$('#myModal').modal('show');
			                    }
			                });
	                  
	                
	        
			                // Function to calculate the image clarity
					        function calculateImageClarity(imageData) {
					            // Convert the image data to grayscale
					            var grayscaleData = [];
					            for (var i = 0; i < imageData.length; i += 4) {
					                var avg = (imageData[i] + imageData[i + 1] + imageData[i + 2]) / 3;
					                grayscaleData.push(avg);
					            }
					
					            // Calculate the standard deviation of the grayscale values
					            var sum = grayscaleData.reduce(function (acc, val) { return acc + val; }, 0);
					            var mean = sum / grayscaleData.length;
					            var variance = grayscaleData.reduce(function (acc, val) { return acc + Math.pow(val - mean, 2); }, 0) / grayscaleData.length;
					            var standardDeviation = Math.sqrt(variance);
					
					            return standardDeviation;
					        }
	        } 
	            
	            //Function to stop the camera
	       	function stopCamera() {
		  		  if (mediaStream) {
		  		      var tracks = mediaStream.getTracks();
		  		      tracks.forEach(function (track) {
		  		          track.stop();
		  		      });
		  		      mediaStream = null;
		  		      var video = document.getElementById('videoElement');
		  		      video.srcObject = null;
		  		      isFunctionRunning = false;
		  		      clearInterval(intervalId);
		                console.log("Function call stopped");
		  		  }
		  		}
	       	//Reloading the page
	       	function reloadmypage()
	       	{
	       		location.reload()
	       	}
	       	//Redirecting Back to the previous or Landing Page
	       	function takeMeBack()
	       	{
	       		var ctx = "${pageContext.request.contextPath}";
	       	 
	       	        window.location.href =ctx+ "/start";
	       	}
	       	//Function to Book Appointment
	       	function BookAppt( x)
	       	{
	       		var ticketNo;
	       		var dept =null
	       		if(x===1)
	       			{
	       			dept="General";
	       			}
	       		if(x===2)
	       			{
	       			dept="Neurology";
	       			}
	       		if(x===3)
	       			{
	       			dept="Cardiology";
	       			}
	       		if(x===4)
	       			{
	       			dept="ENT"
	       			}
	       		const min = 1;
	       		const max = 100;
	       		ticketNo=Math.floor(Math.random() * (max - min + 1)) + min;
	       		if($("#phoneNumber").val()==="")
	       			{
	       				$('.modal-body').html("Your booking has been done for the OPD department: "+dept+"<br>Ticket number: Reg-No."+ ticketNo+".<br>"+"Name: "+$("#patientName").val()+"<br>Date of Birth: "+$("#dOb").val()+"<br>Gender: "+$("#gender").val());
	       			}
	       		if($("#phoneNumber").val()!=="")
	       			{
	       				$('.modal-body').html("Your booking has been done for the OPD department: "+dept+"<br>Ticket number: Reg-No."+ ticketNo+".<br>"+"Name: "+$("#patientName").val()+"<br>Date of Birth: "+$("#dOb").val()+"<br>Gender: "+$("#gender").val()+"<br>Phone No.: "+$("#phoneNumber").val());
	       			}
	       		if($("#dOb").val()==="" || $("#gender").val()==="" || $("#aadhaarNumber").val()==="")
	       			{
		       			$('.modal-body').html("Sorry! Your booking cannot be proceeded.");
		       			$(".failurebtn").css("display","block");
		       			$('.successbtn').css("display","none");
		       			$('.modal-title').html("Confirmation");
	       			}
	       		
	       		$('#myModal').modal('show');
	       	}		
</script>
   
</body>
</html>