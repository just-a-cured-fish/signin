package com.yxh.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yxh.mapper.signinMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.code;
import com.yxh.pojo.signin;



public class Excelimpl {

public void export(String[] titles, ServletOutputStream out,signin signin1) throws Exception{
    try{
                     // 第一步，创建一个workbook，对应一个Excel文件
                     HSSFWorkbook workbook = new HSSFWorkbook();
                     
                     // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
                     HSSFSheet hssfSheet = workbook.createSheet("sheet1");
                     
                     // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
                     
                     HSSFRow row = hssfSheet.createRow(0);
                    // 第四步，创建单元格，并设置值表头 设置表头居中
                     HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
                     hssfCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                   
                     //居中样式
                     hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                     hssfSheet.setColumnWidth(0, 25 * 256);
                     hssfSheet.setColumnWidth(1, 25 * 256);
                     hssfSheet.setColumnWidth(2, 25 * 256);
                     hssfSheet.setColumnWidth(3, 25 * 256);
                     hssfSheet.setColumnWidth(4, 25 * 256);
                     hssfSheet.setColumnWidth(5, 25 * 256);
                     hssfSheet.setColumnWidth(6, 25 * 256);
                     hssfSheet.setColumnWidth(7, 25 * 256);
                     hssfSheet.setColumnWidth(8, 25 * 256);
                     hssfSheet.setColumnWidth(9, 25 * 256);
                     HSSFCell hssfCell = null;
                     for (int i = 0; i < titles.length; i++) {
                         hssfCell = row.createCell(i);//列索引从0开始
                         hssfCell.setCellValue(titles[i]);//列名1
                         hssfCell.setCellStyle(hssfCellStyle);//列居中显示                
                     }
                 
                     ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
             		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
             		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
             	
             		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             		 
                      //这里我把list当做数据库啦
                      List<signin>  list=signinMapper.selectchoose(signin1);;
                      List<Student> stu=signinMapper.selectstunot(signin1);
                      
                      int j=0;
                      for (int i = 0; i < stu.size(); i++) {
                          row = hssfSheet.createRow(i+1);
                          Student student=stu.get(i);
                          HSSFRichTextString richString = new HSSFRichTextString(student.getSname()+"未打卡"); //textValue是要设置大小的单元格数据
                          HSSFFont font = workbook.createFont();
                          font.setColor(HSSFColor.RED.index);//设置excel数据字体颜色
                          font.setFontHeightInPoints((short) 11);//设置excel数据字体大小
                          richString.applyFont(font);
                          j++;
                          row.createCell(0).setCellValue(richString);
                      }
                    
                     
                         for (int i = 0; i < list.size(); i++) {
                        	 System.out.println(list.get(i));
                             row = hssfSheet.createRow(j+1);  
                             j++;
                             signin signin = list.get(i);
                             
                             // 第六步，创建单元格，并设置值
                             String  name = null;
                             if(signin.getSname()!= null){
                            	 name = signin.getSname();
                             }
                            row.createCell(0).setCellValue(name);
                             // 第六步，创建单元格，并设置值
                             String  address = null;
                             if(signin.getAddress()!= null){
                            	 address = signin.getAddress();
                             }
                            row.createCell(1).setCellValue(address);
                            String  time = null;
                            if(signin.getTime()!= null){
                            	 time = formatter.format(signin.getTime());
                            	
                            }
                            if(signin.getIstrue()!=null) {
                            String istrue=null;
                            if(signin.getIstrue()!=null&&signin.getIstrue()==1) {
                            	istrue="打卡与要求打卡地点相同";
                            	row.createCell(3).setCellValue(istrue);
                            }
                            else  if(signin.getIstrue()!=null&&signin.getIstrue()==0){
                            	HSSFRichTextString richString = new HSSFRichTextString("打卡与要求打卡地点不同"); //textValue是要设置大小的单元格数据
                                HSSFFont font = workbook.createFont();
                                font.setColor(HSSFColor.RED.index);//设置excel数据字体颜色
                                font.setFontHeightInPoints((short) 11);//设置excel数据字体大小
                                richString.applyFont(font);
                            	
                            	row.createCell(3).setCellValue(richString);
                            }else {
                            	istrue="无";
                            	row.createCell(3).setCellValue(istrue);
                            }
                            
                            }
                            if(signin.getIstrue2()!=null) {
                            String istrue2=null;
                            if( signin.getIstrue2()==1) {
                            	istrue2="人脸验证通过";
                            	row.createCell(4).setCellValue(istrue2);
                            }
                            else if(signin.getIstrue2()==0){
                            	HSSFRichTextString richString = new HSSFRichTextString("人脸验证未通过"); //textValue是要设置大小的单元格数据
                                HSSFFont font = workbook.createFont();
                                font.setColor(HSSFColor.RED.index);//设置excel数据字体颜色
                                font.setFontHeightInPoints((short) 11);//设置excel数据字体大小
                                richString.applyFont(font);
                            	
                            	row.createCell(4).setCellValue(richString);
                            }else{
                            	istrue2="无";
                            	row.createCell(4).setCellValue(istrue2);
                            }
                            
                            }
                           row.createCell(2).setCellValue(time);
                           String  about = null;
                           if(signin.getAbout()!= null){
                        	   about = signin.getAbout();
                           }
                          row.createCell(5).setCellValue(about);
                          String  temp = null;
                          if(signin.getTemp()!= null){
                        	  
                        	  temp = signin.getTemp();
                       	
                       	  try {
                       	 
                       		   if(Float.parseFloat(temp)>37.4) {
                              		HSSFRichTextString richString = new HSSFRichTextString(temp); //textValue是要设置大小的单元格数据
                                   HSSFFont font = workbook.createFont();
                                   font.setColor(HSSFColor.RED.index);//设置excel数据字体颜色
                                   font.setFontHeightInPoints((short) 11);//设置excel数据字体大小
                                   richString.applyFont(font);
                               	
                                   row.createCell(6).setCellValue(richString);
                              		  
                              	   } else {
                              		 row.createCell(6).setCellValue(temp);
                              	   }
                       	   }catch(Exception e){
                       
                     	   }finally {
                      		   
                      	   }
                   
                          
                          }
                          String  dan = null;
                          if(signin.getDan()!= null){
                       	   about = signin.getDan();
                          }
                         row.createCell(7).setCellValue(dan);
//                             String name = "";
//                             if(person.getName() != null){
//                                 name = person.getName();
//                             }
    //                        row.createCell(1).setCellValue(name);
//                             String password = "";
//                             if(person.getPassword() != null){
//                                 password = person.getPassword();
//                             }
//                             row.createCell(2).setCellValue(password);
//                             String age=null;
//                             if(person.getAge() !=null){
//                                 age = person.getAge();
//                             }
                           //  row.createCell(3).setCellValue(age);
                         }
    
                     // 第七步，将文件输出到客户端浏览器
                     try {
                         workbook.write(out);
                         out.flush();
                        out.close();
         
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }catch(Exception e){
                     e.printStackTrace();
                    throw new Exception("导出信息失败！");
                    
                    }
                 }        
}