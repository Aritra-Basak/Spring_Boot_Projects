package com.example.Camera_Project.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.example.Camera_Project.model.AadhaarCard;

@Service
public class QRDecoder {
	
	

	protected static final byte SEPARATOR_BYTE = (byte)255;
	 protected static final int REFERENCE_ID_INDEX = 1,
	            NAME_INDEX = 2,
	            DATE_OF_BIRTH_INDEX = 3,
	            GENDER_INDEX = 4,
	            CARE_OF_INDEX = 5,
	            DISTRICT_INDEX = 6,
	            LANDMARK_INDEX = 7,
	            HOUSE_INDEX = 8,
	            LOCATION_INDEX = 9,
	            PIN_CODE_INDEX = 10,
	            POST_OFFICE_INDEX = 11,
	            STATE_INDEX = 12,
	            STREET_INDEX = 13,
	            SUB_DISTRICT_INDEX = 14,
	            VTC_INDEX = 15;
	    protected int emailMobilePresent, imageStartIndex, imageEndIndex;
	    protected ArrayList<String> decodedData;
	    protected String signature,email,mobile;
	    protected AadhaarCard scannedAadharCard;
	    
	    public boolean validateMobile(String hexString, String mobile, String last4Digit) throws NoSuchAlgorithmException {
	    	boolean isValid = false;
	    	int lastDigit = Integer.parseInt(last4Digit.substring(3));
	    	int noOfIteration = 0;
	    	if (lastDigit == 0 || lastDigit == 1) {
	    		noOfIteration = 1;
	    	} else {
	    		noOfIteration = lastDigit;
	    	}
	    	byte[] mobileByte = mobile.getBytes(StandardCharsets.UTF_8);
	    	for (int i = 1; i <= noOfIteration; i++) {
	    		mobileByte = convertSha256(mobileByte);
	    	}
	    	String hexMobile = new String(mobileByte, StandardCharsets.UTF_8);
	    	if (hexMobile.equals(hexString)) {
	    		isValid = true;
	    	}
	    	return isValid;
	    }
	    
	    public AadhaarCard secureQrCode(String scanData) throws Exception{
	        scannedAadharCard = new AadhaarCard();

	        // 1. Convert Base10 to BigInt
	        final BigInteger bigIntScanData = new BigInteger(scanData,10);

	        // 2. Convert BigInt to Byte Array
	        final byte byteScanData[] = bigIntScanData.toByteArray();

	        // 3. Decompress Byte Array
	        final byte[] decompByteScanData = decompressData(byteScanData);

	        // 4. Split the byte array using delimiter
	        List<byte[]> parts = separateData(decompByteScanData);
	        // Throw error if there are no parts
	        if(parts.size() == 0){
	            throw new Exception("Invalid QR Code Data, no parts found after splitting by delimiter");
	        }

	        // 5. decode extracted data to string
	        decodeData(parts);

	        // 6. Extract Signature
	        decodeSignature(decompByteScanData);

	        // 7. Email and Mobile number
	       AadhaarCard emailMobile = decodeMobileEmail(decompByteScanData);

	        // 8. Extract Image
	        String imageBase64 = decodeImage(decompByteScanData);
	       if(imageBase64 != null) {
	    	   scannedAadharCard.setImage(imageBase64);
	       }
	       if (emailMobile != null) {
	    	   scannedAadharCard.setEmail(emailMobile.getEmail());
	    	   scannedAadharCard.setMobile(emailMobile.getMobile());
	       }
	       return scannedAadharCard;
	    }

    
	    /**
	     * Decompress the byte array, compression used is GZIP
	     * @param byteScanData compressed byte array
	     * @return uncompressed byte array
	     * @throws QrCodeException
	     */
	    protected byte[] decompressData(byte[] byteScanData) throws Exception {
	        ByteArrayOutputStream bos = new ByteArrayOutputStream(byteScanData.length);
	        ByteArrayInputStream bin = new ByteArrayInputStream(byteScanData);
	        GZIPInputStream gis = null;

	        try {
	            gis = new GZIPInputStream(bin);
	        } catch (IOException e) {
	            throw new Exception("Error in opening Gzip byte stream while decompressing QRcode",e);
	        }

	        int size = 0;
	        byte[] buf = new byte[1024];
	        while (size >= 0) {
	            try {
	                size = gis.read(buf,0,buf.length);
	                if(size > 0){
	                    bos.write(buf,0,size);
	                }
	            } catch (IOException e) {
	                throw new Exception("Error in writing byte stream while decompressing QRcode",e);
	            }
	        }

	        try {
	            gis.close();
	            bin.close();
	        } catch (IOException e) {
	            throw new Exception("Error in closing byte stream while decompressing QRcode",e);
	        }

	        return bos.toByteArray();
	    }
	    
	    /**
	     * Function to split byte array with delimiter
	     * @param source source byte array
	     * @return list of separated byte arrays
	     */
	    protected List<byte[]> separateData(byte[] source) {
	        List<byte[]> separatedParts = new LinkedList<>();
	        int begin = 0;

	        for (int i = 0; i < source.length; i++) {
	            if(source[i] == SEPARATOR_BYTE){
	                // skip if first or last byte is separator
	                if(i != 0 && i != (source.length -1)){
	                    separatedParts.add(Arrays.copyOfRange(source, begin, i));
	                }
	                begin = i + 1;
	                // check if we have got all the parts of text data
	                if(separatedParts.size() == (VTC_INDEX + 1)){
	                    // this is required to extract image data
	                    imageStartIndex = begin;
	                    break;
	                }
	            }
	        }
	        return separatedParts;
	    }

	    /**
	     * function to decode string values
	     * @param encodedData
	     * @throws QrCodeException
	     */
	    protected AadhaarCard decodeData(List<byte[]> encodedData) throws Exception{
	        Iterator<byte[]> i = encodedData.iterator();
	        decodedData = new ArrayList<String>();
	        while(i.hasNext()){
	            try {
	                decodedData.add(new String(i.next(), "ISO-8859-1"));
	            } catch (UnsupportedEncodingException e) {
	                throw new Exception("Decoding QRcode, ISO-8859-1 not supported",e);
	            }
	        }
	        // set the value of email/mobile present flag
	        emailMobilePresent = Integer.parseInt(decodedData.get(0));

	        // populate decoded data
	        scannedAadharCard.setLastFourDigit(decodedData.get(1).substring(0,4));
	        scannedAadharCard.setName(decodedData.get(2));
	        scannedAadharCard.setDateOfBirth(decodedData.get(3));
	        scannedAadharCard.setGender(decodedData.get(4));
	        scannedAadharCard.setCareOf(decodedData.get(5));
	        scannedAadharCard.setDistrict(decodedData.get(6));
	        scannedAadharCard.setLandmark(decodedData.get(7));
	        scannedAadharCard.setHouse(decodedData.get(8));
	        scannedAadharCard.setLocation(decodedData.get(9));
	        scannedAadharCard.setPinCode(decodedData.get(10));
	        scannedAadharCard.setPostOffice(decodedData.get(11));
	        scannedAadharCard.setState(decodedData.get(12));
	        scannedAadharCard.setStreet(decodedData.get(13));
	        scannedAadharCard.setSubDistrict(decodedData.get(14));
	        scannedAadharCard.setVtc(decodedData.get(15));
	        return scannedAadharCard;
	    }
	    
	    
	    protected void decodeSignature(byte[] decompressedData) throws Exception{
	        // extract 256 bytes from the end of the byte array
	        int startIndex = decompressedData.length - 257,
	                noOfBytes = 256;
	        try {
	            signature = new String (decompressedData,startIndex,noOfBytes,"ISO-8859-1");
	        } catch (UnsupportedEncodingException e) {
	            throw new Exception("Decoding Signature of QRcode, ISO-8859-1 not supported",e);
	        }

	    }
	    
	    /**
	     * ref : https://uidai.gov.in/2-uncategorised/11320-aadhaar-paperless-offline-e-kyc-3.html
	     * Hashing logic for Email ID :
	     * Sha256(Sha256(Email+SharePhrase))*number of times last digit of Aadhaar number
	     * (Ref ID field contains last 4 digits).
	     *
	     * Example :
	     * Email: abc@gm.com
	     * Aadhaar Number:XXXX XXXX 3632
	     * Passcode : Lock@487
	     * Hash : Sha256(Sha256(abc@gm.comLock@487))*2
	     * In case of Aadhaar number ends with Zero we will hashed one time.
	     * **********************************************************************
	     * **********************************************************************
	     * Hashing logic for Mobile Number :
	     * Sha256(Sha256(Mobile+SharePhrase))*number of times last digit of Aadhaar number
	     * (Ref ID field contains last 4 digits).
	     *
	     * Example :
	     * Mobile: 1234567890
	     * Aadhaar Number:XXXX XXXX 3632
	     * Passcode : Lock@487
	     * Hash: Sha256(Sha256(1234567890Lock@487))*2
	     * In case of Aadhaar number ends with Zero we will hashed one time.
	     * @param decompressedData
	     * @throws QrCodeException
	     */

	    protected AadhaarCard decodeMobileEmail(byte[] decompressedData) throws Exception{
	    	AadhaarCard emailMobile = null;
	        int mobileStartIndex = 0,mobileEndIndex = 0,emailStartIndex = 0,emailEndIndex = 0;
	        switch (emailMobilePresent){
	            case 3:
	                // both email mobile present
	                mobileStartIndex = decompressedData.length - 289; // length -1 -256 -32
	                mobileEndIndex = decompressedData.length - 257; // length -1 -256
	                emailStartIndex = decompressedData.length - 322;// length -1 -256 -32 -1 -32
	                emailEndIndex = decompressedData.length - 290;// length -1 -256 -32 -1

	                mobile = bytesToHex(Arrays.copyOfRange(decompressedData,mobileStartIndex,mobileEndIndex+1));
	                email = bytesToHex(Arrays.copyOfRange(decompressedData,emailStartIndex,emailEndIndex+1));
	                // set image end index, it will be used to extract image data
	                imageEndIndex = decompressedData.length - 323;
	                break;

	            case 2:
	                // only mobile
	                email = "";
	                mobileStartIndex = decompressedData.length - 289; // length -1 -256 -32
	                mobileEndIndex = decompressedData.length - 257; // length -1 -256

	                mobile = bytesToHex(Arrays.copyOfRange(decompressedData,mobileStartIndex,mobileEndIndex+1));
	                // set image end index, it will be used to extract image data
	                imageEndIndex = decompressedData.length - 290;
	                break;

	            case 1:
	                // only email
	                mobile = "";
	                emailStartIndex = decompressedData.length - 289; // length -1 -256 -32
	                emailEndIndex = decompressedData.length - 257; // length -1 -256

	                email = bytesToHex(Arrays.copyOfRange(decompressedData,emailStartIndex,emailEndIndex+1));
	                // set image end index, it will be used to extract image data
	                imageEndIndex = decompressedData.length - 290;
	                break;

	            default:
	                // no mobile or email
	                mobile = "";
	                email = "";
	                // set image end index, it will be used to extract image data
	                imageEndIndex = decompressedData.length - 257;
	        }
	        if (!(mobile.isEmpty() && email.isEmpty())) {
	        	emailMobile = new AadhaarCard();
	        	emailMobile.setEmail(email);
	        	emailMobile.setMobile(mobile);
	        }
	        return emailMobile;
	    }
	    
	    /**
	     * Convert byte array to hex string
	     * @param bytes
	     * @return
	     */
	    public static String bytesToHex(byte[] bytes) {
	        final char[] hexArray = "0123456789ABCDEF".toCharArray();

	        char[] hexChars = new char[bytes.length * 2];
	        for ( int j = 0; j < bytes.length; j++ ) {
	            int v = bytes[j] & 0xFF;
	            hexChars[j * 2] = hexArray[v >>> 4];
	            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	        }
	        return new String(hexChars);
	    }
	    
	    protected String decodeImage(byte[] decompressedData) throws Exception{
	        // image start and end indexes are calculated in functions : separateData and decodeMobileEmail
	        byte[] imageBytes = Arrays.copyOfRange(decompressedData,imageStartIndex,imageEndIndex+1);
//	        String decoded = new String(Base64.getDecoder().decode(imageBytes));
	        String decoded = new String(Base64Utils.encodeToString(imageBytes));
	        return decoded;
	    }

	    protected byte[] convertSha256(byte[] plainByte) throws NoSuchAlgorithmException {
	    	MessageDigest md = MessageDigest.getInstance("SHA-256");
	    	  byte[] result = md.digest(plainByte);
	    	  return result;
	    }
	

}
