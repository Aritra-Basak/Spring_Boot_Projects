/**
 * 
 */
package com.example.Camera_Project.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.example.Camera_Project.model.AadhaarCardModel;
import com.example.Camera_Project.model.AadhaarData;
import com.example.Camera_Project.model.ServiceResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @author Aritra
 *
 */
@Component
public class OCR_Decoding {
	@Autowired
	OpenAiWithSpringBootService oa;
	
public ServiceResponse OCR(String imageBase64,HttpServletRequest request) throws TesseractException, IOException
{
	String fileData = imageBase64;
	//String fileData = convertBase64ToGrayscale(imageBase64));
	//Converting the base64 in byte array.
    byte[] byteArr = Base64Utils.decodeFromString(fileData);
    InputStream inputStream = new ByteArrayInputStream(byteArr);
     BufferedImage bufferImage = ImageIO.read(inputStream);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata\\");
        tesseract.setLanguage("eng+ben");
      String contentTxt = tesseract.doOCR(bufferImage);
   /*String contentTxt="W HRA TEHR\r\n" + 
      		"oo Government of India '\r\n" + 
      		"2 e T ]\r\n" + 
      		"& RAFIKUL HASAN b/\r\n" + 
      		"g G4 / DOB: 03/05/1987 :\r\n" + 
      		"; | ¥/ MALE\r\n" + 
      		"| _m._.'ilsé\r\n" + 
      		"B 7943 7820 4868\r\n" + 
      		"I T4, A g\r\n" + 
      		"3\r\n" + 
      		"\"\r\n" + 
      		"Jul 14, 2023, 15:35";*/
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
     LocalDateTime now = LocalDateTime.now();  
     System.out.println("output: "+dtf.format(now)+"\n" + contentTxt);
     return DecodeTheText(contentTxt,request);
}

public ServiceResponse DecodeTheText(String text,HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException
{
	ServiceResponse sr = new ServiceResponse();
	String name= null;
	String dob= null;
	String gender= null;
	String aadharNumber = null;
     String namePattern = "\\b[A-Z]+\\b\\s\\b[A-Z]+\\b";
     String namePattern2="(?m)^(?:[A-Z][a-zA-Z]* [A-Z][a-zA-Z]*|[A-Z]+)$";
     String dobPattern = "\\d{2}/\\d{2}/\\d{4}";
     String aadharPattern = "\\d{4} \\d{4} \\d{4}";
     String genderPattern = "Male|Female"; 


     Pattern nameRegex = Pattern.compile(namePattern);
     Pattern nameRegex2 = Pattern.compile(namePattern2);
     Pattern dobRegex = Pattern.compile(dobPattern);
     Pattern aadharRegex = Pattern.compile(aadharPattern);
     Pattern genderRegex = Pattern.compile(genderPattern, Pattern.CASE_INSENSITIVE);


     Matcher nameMatcher = nameRegex.matcher(text);
     Matcher nameMatcher2 = nameRegex2.matcher(text);
     Matcher dobMatcher = dobRegex.matcher(text);
     Matcher aadharMatcher = aadharRegex.matcher(text);
     Matcher genderMatcher = genderRegex.matcher(text);
     
     String output="";
 
     if (nameMatcher2.find()) {
        name = nameMatcher2.group().trim();
         
         if(name.isEmpty())
         {
        	    if (nameMatcher.find())
        	    {
        	          name = nameMatcher.group().trim();
        	    }    
         }
        	    System.out.println("Name: " + name);
        	  //  output=output+"\nName:"+name; 
          
     }
           

     if (dobMatcher.find()) {
          dob = dobMatcher.group();
         System.out.println("DOB: " + dob);
         //output=output+"\nDOB:"+dob;
     }

     if (aadharMatcher.find()) {
         aadharNumber = aadharMatcher.group();
         System.out.println("Aadhar Number: " + aadharNumber);
        // output=output+"\nAadhar:"+aadharNumber;
     }

     if (genderMatcher.find()) {
         gender = genderMatcher.group();
         System.out.println("Gender: " + gender);
         //output=output+"\nGender:"+gender;
     }
     //System.out.println("output:"+output);
     //if(dob!=null && aadharNumber!=null)
     if(dob!=null && aadharNumber!=null && gender!=null)
     {
	   text = text+"\nGet Name,Gender,Date of Birth ,and Aadhar Number from above text. Aadhar Number number should 12 digit, Date of birth format dd/mm/yyyy give output in json and json key should {\"Name\":\"\",\"Gender\":\",\"DOB\":\"\",\"AadharNumber\":\"\",json should always single object not array . remove space from json keys. if dob not available exclude that person";
	   ServiceResponse response = oa.submitOpenAiRequest(text,request);
	   System.out.println(response.getResObject().toString());
	   output =response.getResponseMessage();
	   System.out.println("API Output:"+output);
	   output = output.substring(output.indexOf("{"));
	   System.out.println("API Output after replace:"+output);
	   AadhaarData data = new ObjectMapper().readValue(output.getBytes(), AadhaarData.class);
	   AadhaarCardModel aadhaarModel = new AadhaarCardModel(data.getName(), data.getGender(), data.getDob(), data.getAadhaarNumber());
	   
		/*output.substring(output.indexOf("Birth:")+7,output.indexOf("Aadhar"));
		output.substring(output.indexOf("Number:")+8,output.indexOf("Gender"));
		output.substring(output.indexOf("Gender:")+8,output.length());
		sr.setName(output.substring(output.indexOf(":")+2,output.indexOf("Date")));*/
		sr.setResObject(aadhaarModel);
		sr.setStatus("Success");
	   
     }
     else
     {
    	 sr.setStatus("Failed");
    	 sr.setResponseMessage("Captured image quality is not good.");
     }
     return sr;
 }

public static String convertBase64ToGrayscale(String base64Image) {
    try {
        // Decode Base64 to byte array
        byte[] decodedBytes = Base64.decodeBase64(base64Image);

        // Create a ByteArrayInputStream from the byte array
        ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);

        // Read the image from the ByteArrayInputStream
        BufferedImage inputImage = ImageIO.read(inputStream);

        // Convert the image to grayscale
        BufferedImage grayscaleImage = convertToGrayscale(inputImage);

        // Encode the grayscale BufferedImage to Base64
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(grayscaleImage, "png", outputStream);
        byte[] grayscaleBytes = outputStream.toByteArray();
        return Base64.encodeBase64String(grayscaleBytes);
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}

private static BufferedImage convertToGrayscale(BufferedImage inputImage) {
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            int rgb = inputImage.getRGB(x, y);
            Color color = new Color(rgb);
            int gray = (int) (color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);
            int grayRGB = new Color(gray, gray, gray).getRGB();
            grayscaleImage.setRGB(x, y, grayRGB);
        }
    }

    return grayscaleImage;
}

}


