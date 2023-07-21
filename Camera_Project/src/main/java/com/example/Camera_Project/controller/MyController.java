package com.example.Camera_Project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Camera_Project.model.AadhaarCard;
import com.example.Camera_Project.model.QRStringContainer;
import com.example.Camera_Project.service.QRDecoder;

@Controller
public class MyController {
	@Autowired
	QRDecoder qrd;
	
	@GetMapping("/hello")
	public String hello() {
	    
	    return "Start";
	}
	@PostMapping("/saveDetails")
	public String saveMe(QRStringContainer qr,HttpServletRequest request, RedirectAttributes attr) throws Exception
	{
		System.out.println(qr.getQrString());
		AadhaarCard ard = qrd.secureQrCode(qr.getQrString());
		String last4digit = "**** **** " + ard.getLastFourDigit();
		ard.setLastFourDigit(last4digit);
		System.out.println("last digits" + ard.getLastFourDigit());
		attr.addFlashAttribute("qrval", ard);
		
		return "redirect:/scanner";
	}
	@GetMapping("/start")
	public String startBooking()
	{
		return "Start2";
	}
	@GetMapping("/scanner")
	public String startScanning()
	{
		return "StartVersion2";
	}
}
