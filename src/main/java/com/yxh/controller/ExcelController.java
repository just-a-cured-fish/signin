package com.yxh.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxh.mapper.signinMapper;
import com.yxh.mapper.thesignMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.signin;
import com.yxh.pojo.thesign;
import com.yxh.util.Excelimpl;


@Controller
public class ExcelController {
    //这里直接new了
    Excelimpl  excleImpl=new Excelimpl();
    
@RequestMapping(value="download_excel")    

//获取url链接上的参数
public @ResponseBody String dowm(HttpServletResponse response,signin signin1){
     response.setContentType("application/binary;charset=UTF-8");
              try{
                  ServletOutputStream out=response.getOutputStream();
                  ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
          		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
                  thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
                   thesign thesign=new thesign();
                   
                   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                   thesign.setThesignid(signin1.getThesignid());
                  List<thesign> thesign1=thesignMapper.selectbythesignid(thesign);
                 
                  System.out.println(thesign1.get(0));
                  String title=thesign1.get(0).getTitle()+" "+formatter.format(thesign1.get(0).getStartday());
                  try {
                      //设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.pdf)
                      response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(title+".xls", "UTF-8"));
                  } catch (UnsupportedEncodingException e1) {
                      e1.printStackTrace();
                  }
               
                  String[] titles = { "学生名字", "学生地点", "打卡时间", "学生地点状况","学生人脸验证","学生身体状况","学生体温" }; 
                  excleImpl.export(titles, out,signin1);      
                  return "success";
              } catch(Exception e){
                  e.printStackTrace();
                  return "导出信息失败";
              }
          }
}