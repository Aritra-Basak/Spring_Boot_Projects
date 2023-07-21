package com.example.Camera_Project.controller;




import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Camera_Project.model.AadhaarModelFromOutside;
import com.example.Camera_Project.model.Mymodel;
import com.example.Camera_Project.model.ServiceResponse;
import com.example.Camera_Project.service.OCR_Decoding;
import com.example.Camera_Project.service.OCR_Decoding_Mobile;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@RestController
@RequestMapping(value="/documents")
public class DocumentsCheck {
		  
	@Autowired
	OCR_Decoding ocr;
	
	@Autowired
	OCR_Decoding_Mobile ocrMob;
	
	
	 @PostMapping("/upload")
	    public ResponseEntity<ServiceResponse> captureDocument(@RequestBody Mymodel my, HttpServletRequest request) throws IOException, TesseractException {
		 String imageBase64=my.getImageBase64().replace("data:image/png;base64,", "");
		 System.out.println("From controller : "+imageBase64);
		 ServiceResponse sr = new ServiceResponse();
		 if(imageBase64==null)
		 	{
			 	sr.setResponseMessage("Image didn't reached backend");
			 	sr.setStatus("Failed");
		 	}
		 	else
		 	{
		 		System.out.println("Successfull call from /upload: "+imageBase64);
		 		sr =ocr.OCR(imageBase64,request);
		        
		 	}
		 return ResponseEntity.ok().body(sr);
	  }
	 
	 @PostMapping("/mobileUpload")
	    public ResponseEntity<ServiceResponse> captureDocumentFromOutside(@RequestBody AadhaarModelFromOutside aout, HttpServletRequest request) throws IOException, TesseractException {
		 String imageBase64=aout.getBase64().replace("data:image/png;base64,", "");
		 ServiceResponse sr = new ServiceResponse();
		 if(imageBase64==null)
		 	{
			 	sr.setResponseMessage("Image didn't reached backend");
			 	sr.setStatus("Failed");
		 	}
		 	else
		 	{
		 		System.out.println("Successfull call from /mobileUpload: "+imageBase64);
		 		sr =ocrMob.OCR(imageBase64,request);
		        
		 	}
		 return ResponseEntity.ok().body(sr);
	  }
    }


