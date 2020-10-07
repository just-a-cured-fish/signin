package com.yxh.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
 
public class test {
	
	private static final String QR_CODE_IMAGE_PATH = "/fresh_images/";
	
	private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		String path="C:/java_tools/apache-tomcat-9.0.30/webapps/";
		UUID uuid=UUID.randomUUID();
		String fileName=uuid.toString()+".png";
		
	
	
		Path path2 = FileSystems.getDefault().getPath(path+filePath+fileName);
		
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path2);
		
	}
	
	public static void main(String[] args) {
        try {
            generateQRCodeImage("f12019ffe0704de988ff0224c1fdb061", 350, 350, QR_CODE_IMAGE_PATH);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
		
	}
	
 
}