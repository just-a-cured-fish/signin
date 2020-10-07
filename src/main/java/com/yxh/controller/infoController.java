package com.yxh.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.yxh.pojo.class1;

@RestController
public class infoController {
	@RequestMapping(value="/json1",produces = "text/html;charset=UTF-8")
	public String json1(class1 class1,HttpServletRequest request) {
		String path=request.getRequestURL().toString();
		String str=request.getSession().getServletContext().getRealPath("");
		
		 str = str.substring(0, str.indexOf("webapps")+8);
		 str=str+"fresh_images/";
		
	     try {
	            str=generateQRCodeImage(class1.getCid(), 350, 350, str);
	        } catch (WriterException e) {
	            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
	        } catch (IOException e) {
	            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
	        }
		 
	 
	     return path.substring(0,path.indexOf("signinmy"))+ str.substring(str.indexOf("fresh_images"));
	}
	private static String generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	
		UUID uuid=UUID.randomUUID();
		String fileName=uuid.toString()+".png";
		
	
	
		Path path2 = FileSystems.getDefault().getPath(filePath+fileName);
		
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path2);
		return filePath+fileName;
	}
	

}
