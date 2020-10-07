package com.yxh.controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yxh.mapper.StudentMapper;
import com.yxh.mapper.classMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.class1;
import com.yxh.pojo.code;

import javax.net.ssl.SSLException;
import javax.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
 
/**
 * 商品信息页面跳转控制类
 * @author 喻鑫昊
 * @createtime 2017年8月20日10:34:55
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
 
	private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    private static String boundaryString = getBoundary();
    protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if(fileMap != null && fileMap.size() > 0){
            Iterator fileIter = fileMap.entrySet().iterator();
            while(fileIter.hasNext()){
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins = null;
        int code = conne.getResponseCode();
        try{
            if(code == 200){
                ins = conne.getInputStream();
            }else{
                ins = conne.getErrorStream();
            }
        }catch (SSLException e){
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while((len = ins.read(buff)) != -1){
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }
    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }
    private static String encode(String value) throws Exception{
        return URLEncoder.encode(value, "UTF-8");
    }
    
    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

 
    /**
     * @createtime 2017年8月20日17:15:41
     * @param request
     * @param file
     * @return 上传成功返回“success”，上传失败返回“error”
     * @throws IOException
     */
	  @ResponseBody
	    @RequestMapping("upload2")
	    public String upload2(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
	        System.out.println("执行upload");
	        int a = 77;
	        request.setCharacterEncoding("UTF-8");
	        String sid=request.getParameter("sid");
	        ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
			//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
			StudentMapper userMapper=context.getBean("StudentMapper",StudentMapper.class);
			
			String str2 = null;
				
			try {
				Student stu = new Student();
				stu.setSid(sid);
				Student stu2=userMapper.selectbyid(stu);
				System.out.println(stu2.getSphoto());
				 
				str2 = "/usr/local/apache-tomcat-8.0.53/webapps/"+stu2.getSphoto().substring(stu2.getSphoto().indexOf("fresh_images"));
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	     	String str;
	     	String path=request.getRequestURL().toString();
	        if(!file.isEmpty()) {
	         
	            String fileName = file.getOriginalFilename();
	           
	            String type = null;
	            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
	          
	            if (type != null) {
	                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
	                    // 项目在容器中实际发布运行的根路径
	                    //String realPath = request.getSession().getServletContext().getRealPath("/").substring(0,path.indexOf("webapps"));
	                    // 自定义的文件名称
	                	 str=request.getSession().getServletContext().getRealPath("");
	            		
	           		 str = str.substring(0, str.indexOf("webapps")+8);
	           		
	           		
	                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
	                    // 设置存放图片文件的路径
	                    str=str+"fresh_images/"+ trueFileName;
	                    //path = realPath + "/fresh_images/" + trueFileName;
	            		
	                   
	                    file.transferTo(new File(str));
	             
	                }else {
	                    
	                    return "error";
	                }
	            }else {
	               
	                return "error";
	            }
	        }else {
	           
	            return "error";
	        }
	        a=String1(str,str2);
	        new File(str).delete();
	        return ""+a;
	    }
	public int String1(String str,String str2) {
		 File file3 = new File(str);
         File file2 = new File(str2);
 		byte[] buff = getBytesFromFile(file3);
 		byte[] buff2 = getBytesFromFile(file2);
 		String url = "https://api-cn.faceplusplus.com/facepp/v3/compare";
         HashMap<String, String> map = new HashMap<>();
         HashMap<String, byte[]> byteMap = new HashMap<>();
         map.put("api_key", "gyXTjYcueC7terkw2-S0t4nMTuzxPkmN");
         map.put("api_secret", "TiAouXYh28lFiATFIkT6foESjAikmWkI");
 		map.put("return_landmark", "1");
         map.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
         byteMap.put("image_file1", buff);
         byteMap.put("image_file2", buff2);
         try{
             byte[] bacd = post(url, map, byteMap);
             String str4 = new String(bacd);
             
             JSONObject rootObject = new JSONObject(str4);
             int a=rootObject.optInt("confidence");
             return a;
             //String sir1= rootObject.getString("confidence");
            // JSONObject sonObject = feedsArray.getJSONObject(0);
   		
//             JSONObject paramzObject = rootObject.getJSONObject("faces1");
//             JSONObject paramzObject2 = paramzObject.getJSONObject("confidence");
             //String str1=new Gson().toJson(sonObject);
       
           
         }catch (Exception e) {
         	e.printStackTrace();
 		}
         return 0;
	}
    @ResponseBody
    @RequestMapping("upload")
    public String upload(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        System.out.println("执行upload");
        request.setCharacterEncoding("UTF-8");
      
     	String str;
     	String path=request.getRequestURL().toString();
        if(!file.isEmpty()) {
         
            String fileName = file.getOriginalFilename();
           
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
          
            if (type != null) {
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    //String realPath = request.getSession().getServletContext().getRealPath("/").substring(0,path.indexOf("webapps"));
                    // 自定义的文件名称
                	 str=request.getSession().getServletContext().getRealPath("");
            		
           		 str = str.substring(0, str.indexOf("webapps")+8);
           		
           		
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    str=str+"fresh_images/"+ trueFileName;
                    //path = realPath + "/fresh_images/" + trueFileName;
                
                    file.transferTo(new File(str));
             
                }else {
                    
                    return "error";
                }
            }else {
               
                return "error";
            }
        }else {
           
            return "error";
        }
        
        return path.substring(0,path.indexOf("signinmy"))+ str.substring(str.indexOf("fresh_images"));
    }
    @ResponseBody
    @RequestMapping(value="/uploadnaoke",produces = "text/html;charset=UTF-8")
    public String upload3(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        System.out.println("执行upload");
        request.setCharacterEncoding("UTF-8");
      
     	String str;
     	String path=request.getRequestURL().toString();
        if(!file.isEmpty()) {
         
            String fileName = file.getOriginalFilename();
           
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
          
            if (type != null) {
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    //String realPath = request.getSession().getServletContext().getRealPath("/").substring(0,path.indexOf("webapps"));
                    // 自定义的文件名称
                	 str=request.getSession().getServletContext().getRealPath("");
            		
           		 str = str.substring(0, str.indexOf("webapps")+8);
           		
           		
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    str=str+"fresh_images/"+ trueFileName;
                    //path = realPath + "/fresh_images/" + trueFileName;
                
                    file.transferTo(new File(str));
             
                }else {
                    
                    return "error";
                }
            }else {
               
                return "error";
            }
        }else {
           
            return "error";
        }
		
        File file3 = new File(str);
		byte[] buff = getBytesFromFile(file3);
		String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "gyXTjYcueC7terkw2-S0t4nMTuzxPkmN");
        map.put("api_secret", "TiAouXYh28lFiATFIkT6foESjAikmWkI");
		map.put("return_landmark", "1");
        map.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
        byteMap.put("image_file", buff);
        try{
            byte[] bacd = post(url, map, byteMap);
            String str4 = new String(bacd);
            System.out.println(str4);
            JSONObject rootObject = new JSONObject(str4);
  		  JSONArray feedsArray = rootObject.getJSONArray("faces");
  		 JSONObject rootObject2=feedsArray.getJSONObject(0);
  	
  		if( new Gson().toJson(rootObject2)!=null) {
  			 return path.substring(0,path.indexOf("signinmy"))+ str.substring(str.indexOf("fresh_images"));
  		}
  
        }catch (Exception e) {
        	return "图片格式有误或者检测不到人脸";
		}
        return "图片格式有误或者检测不到人脸";
    }
}