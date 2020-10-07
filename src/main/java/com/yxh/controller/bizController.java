package com.yxh.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yxh.mapper.StudentMapper;
import com.yxh.mapper.classMapper;
import com.yxh.mapper.signinMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.class1;
import com.yxh.pojo.code;
import com.yxh.pojo.signin;

@Controller
public class bizController {
    @RequestMapping(value = "/downloadZip.do")
    public String downloadFiles(signin signin,String tcLwIds, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<File> files = new ArrayList<File>();
        File Allfile = new File(request.getSession().getServletContext().getRealPath("/") + "upload/");
        System.out.println(request.getSession().getServletContext().getRealPath("/") + "upload/");
       
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		classMapper classMapper=context.getBean("classMapper",classMapper.class);
		StudentMapper StudentMapper=context.getBean("StudentMapper",StudentMapper.class);
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


		List<signin> signin1=signinMapper.selectchoose(signin);
		 String fileName="没有图片.zip";
		for(signin signinall:signin1) {
			if(signinall.getPic().contains("https")) {
				
				System.out.println(signinall.getPic());
				System.out.println(signinall.getPic().substring(signinall.getPic().indexOf("fresh_images")));
				File file=new File("/usr/local/apache-tomcat-8.0.53/webapps/"+signinall.getPic().substring(signinall.getPic().indexOf("fresh_images")));
			    files.add(file);
			}
		}
      
        //        if (Allfile.exists()) {
//            File[] fileArr = Allfile.listFiles();
//            for (File file2 : fileArr) {
//            	
//                files.add(file2);
//            }
//        }
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 try {
		 if(signin1.get(0)!=null) {
		Student stu=new Student();
		 class1 class1=new class1();
		 stu.setSid(signin1.get(0).getStudentid());
		 Student Student = StudentMapper.selectbyid(stu);
		 class1.setCid(Student.getSclass());
		 List<class1> class2 = classMapper.selectbyid(class1);
	      fileName = class2.get(0).getCname()+formatter.format(signin1.get(0).getTime())+ ".zip";
		 }
		 }
		 catch(Exception e) {
			 
		 }
        // 在服务器端创建打包下载的临时文件
		  String outFilePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
 
        File fileZip = new File(outFilePath + fileName);
        // 文件输出流
        FileOutputStream outStream = new FileOutputStream(fileZip);
        // 压缩流
        ZipOutputStream toClient = new ZipOutputStream(outStream);
    //  toClient.setEncoding("gbk");
        zipFile(files, toClient);
        toClient.close();
        outStream.close();
        this.downloadFile(fileZip, response, true);
        return null;
    }
    public static void zipFile(List<File> files, ZipOutputStream outputStream) throws IOException, ServletException {
        try {
            int size = files.size();
            // 压缩列表中的文件
            for (int i = 0; i < size; i++) {
                File file = (File) files.get(i);
                zipFile(file, outputStream);
            }
        } catch (IOException e) {
            throw e;
        }
    }
    public static void zipFile(File inputFile, ZipOutputStream outputstream) throws IOException, ServletException {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream inStream = new FileInputStream(inputFile);
                    BufferedInputStream bInStream = new BufferedInputStream(inStream);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    outputstream.putNextEntry(entry);
 
                    final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
                    long streamTotal = 0; // 接受流的容量
                    int streamNum = 0; // 流需要分开的数量
                    int leaveByte = 0; // 文件剩下的字符数
                    byte[] inOutbyte; // byte数组接受文件的数据
 
                    streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
                    streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
                    leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量
 
                    if (streamNum > 0) {
                        for (int j = 0; j < streamNum; ++j) {
                            inOutbyte = new byte[MAX_BYTE];
                            // 读入流,保存在byte数组
                            bInStream.read(inOutbyte, 0, MAX_BYTE);
                            outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
                        }
                    }
                    // 写出剩下的流数据
                    inOutbyte = new byte[leaveByte];
                    bInStream.read(inOutbyte, 0, leaveByte);
                    outputstream.write(inOutbyte);
                    outputstream.closeEntry(); // Closes the current ZIP entry
                    // and positions the stream for
                    // writing the next entry
                    bInStream.close(); // 关闭
                    inStream.close();
                }
            } else {
                throw new ServletException("文件不存在！");
            }
        } catch (IOException e) {
            throw e;
        }
    }
public void downloadFile(File file,HttpServletResponse response,boolean isDelete) {
    try {
        // 以流的形式下载文件。
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"));
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        if(isDelete)
        {
            file.delete();        //是否将生成的服务器端文件删除
        }
     } 
     catch (IOException ex) {
        ex.printStackTrace();
    }
} 
}
