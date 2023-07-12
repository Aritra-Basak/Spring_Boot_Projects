package com.aritra.PDFDecoder;
import java.util.*;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.google.zxing.Result;
/**
 * PDF Reader and QR Code decoder from pdf
 * PDF Reader - can read all the text or content from a pdf.
 * QR Code decoder - can read a QR code from a pdf and can also scan and decode what's inside the QR code
 * Just run this java file as a java application. Don't have to run the Application file.
 * Author - Aritra
 * **/
public class PDF_and_QR_Reader {
	public static void main(String [] args) throws IOException
	{
		//PDf content reading starts
//		 Loading an existing document
	      File file = new File("<Your PDF File location or complete path>");
	      PDDocument document = PDDocument.load(file);
	      //Instantiate PDFTextStripper class
	      PDFTextStripper pdfStripper = new PDFTextStripper();
	      //Retrieving text from PDF document
	      String text = pdfStripper.getText(document);
	      System.out.println("All the content from the given PDF:-\n"+text);
	      
//QR Decoding Starts
	      File file2 = new File("<Your PDF File location or complete path which have the QR Code>");
	      PDDocument document2 = PDDocument.load(file);
	        // (1) Parse PDF document
	        document2 = PDDocument.load(file2);

	        // (2) Get all the QR results from the document
	       
	        List<Result> results = PDFUtils.getQRResultsFromDocument(document2);
	        
	        // (3) Process QR results
	        for (Result result : results) {      
	          System.out.println("PDF QR DECODED: "+result.getText()); // That's all.        
	        }

	        // Oh, god...
	        if (document != null) {
	          try {
	            document.close();
	          } catch (IOException e) {
	          }
	        }
	}

}
