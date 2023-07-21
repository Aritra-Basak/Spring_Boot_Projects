package com.example.Camera_Project.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.jbig2.Bitmap;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;



public class TesseractTest {
	




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
	    
	    //Reducing the Size--
	    public static String reduceImageSize(String base64Image, int targetWidth, int targetHeight) {
	        try {
	            // Decode Base64 to byte array
	            byte[] decodedBytes = Base64.decodeBase64(base64Image);

	            // Create a ByteArrayInputStream from the byte array
	            ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);

	            // Read the image from the ByteArrayInputStream
	            BufferedImage inputImage = ImageIO.read(inputStream);

	            // Resize the image to the desired dimensions
	            BufferedImage resizedImage = resizeImage(inputImage, targetWidth, targetHeight);

	            // Encode the resized BufferedImage to Base64
	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	            ImageIO.write(resizedImage, "png", outputStream);
	            byte[] resizedBytes = outputStream.toByteArray();
	            return Base64.encodeBase64String(resizedBytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    private static BufferedImage resizeImage(BufferedImage inputImage, int targetWidth, int targetHeight) {
	        Image tmp = inputImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
	        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d = resizedImage.createGraphics();
	        g2d.drawImage(tmp, 0, 0, null);
	        g2d.dispose();
	        return resizedImage;
	    }
	    
	  
	public static void main(String[] args) throws TesseractException, IOException {
		//String imagePath="C:\\Projects\\MicrosoftTeams-image.png";
		//String imagePath="C:\\Projects\\testingAddhar.jpeg";
		//String imagePath="C:\\Projects\\MicrosoftTeams-image (1).png";
		String imagePath="C:\\Projects\\MicrosoftTeams-image (2).jpg";
		File image = new File(imagePath);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata\\");
        tesseract.setLanguage("eng");
//        tesseract.setPageSegMode(7);
//        tesseract.setOcrEngineMode(3);
        String result = tesseract.doOCR(image);
        System.out.println(result);
        Path pathToImage = Paths.get(imagePath); 
        byte[] imageBytes = Files.readAllBytes(pathToImage);
        
        // 2. Encode image bytes[] to Base64 encoded String
       String base64EncodedImageBytes = Base64.encodeBase64String(imageBytes);
      
      //System.out.println("GrayScale:"+convertBase64ToGrayscale(base64EncodedImageBytes));
      System.out.println("After all reduction: "+reduceImageSize(convertBase64ToGrayscale(base64EncodedImageBytes),600,800));

	}

}
