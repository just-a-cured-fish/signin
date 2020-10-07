package com.yxh.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.yxh.mapper.StudentMapper;
import com.yxh.mapper.classMapper;
import com.yxh.mapper.teacherMapper;
import com.yxh.mapper.thesignMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.Teacher;
import com.yxh.pojo.class1;
import com.yxh.pojo.code;
import com.yxh.pojo.thesign;

@RestController
public class classController {
	@RequestMapping(value="/createclass",produces = "text/html;charset=UTF-8")
	public String json1(class1 class1) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		classMapper classMapper=context.getBean("classMapper",classMapper.class);
		
		String str="";
		Gson gson=new Gson();
		code code=new code();
		try {
		    String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		    class1.setCid(uuid);
			code.setCode(classMapper.insertclass(class1)-1);	
			code.setData(class1);	
			str=gson.toJson(code);
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping(value="/selectclass",produces = "text/html;charset=UTF-8")
	public String json2(class1 class1) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		classMapper classMapper=context.getBean("classMapper",classMapper.class);
		
		String str="";
		Gson gson=new Gson();
		code code=new code();
		try {
		    List<class1> class2=new ArrayList<>();
		    class2=classMapper.selectbyid(class1);
		    if(class2!=null) {
		    	code.setCode(0);
		    	code.setData(class2);
		    }else{
		    	code.setCode(1);
		    	
		    }
		
			str=gson.toJson(code);
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping(value="/stuclassamount",produces = "text/html;charset=UTF-8")
	public String json22(class1 class1) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		classMapper classMapper=context.getBean("classMapper",classMapper.class);
		thesignMapper TeacherMapper=context.getBean("thesignMapper",thesignMapper.class);
		thesign thesign=new thesign();
		
		String str="";
		Gson gson=new Gson();
		code code=new code();
		
		thesign.setTid(class1.getTid());
	    int b=0;
	    List<thesign> thesign2=TeacherMapper.selectbyid(thesign);
	    int a=0;
	    int c=0;
		try {
		    List<class1> class2=new ArrayList<>();
		   
		    class2=classMapper.selectbyid(class1);
		   
		    a=class2.size();
		    c=thesign2.size();
		    for(class1 class3:class2) {
		    	b=class3.getAmount()+b;
		    }
	
		
			str=gson.toJson(code);
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,Integer> map=new HashMap<>();
		map.put("班级人数", a);
		map.put("学生人数", b);
		map.put("发起的签到", c);
		return gson.toJson(map);
	}
	@RequestMapping(value="/stuclass",produces = "text/html;charset=UTF-8")
	public String json3(class1 class1) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		StudentMapper StudentMapper=context.getBean("StudentMapper",StudentMapper.class);
		
		String str="";
		Gson gson=new Gson();
		code code=new code();
		try {
		
		    List<Student> s=StudentMapper.selectbysclass(class1);
		    if(s!=null) {
		    	code.setCode(0);
		    	code.setData(s);
		    }else{
		    	code.setCode(1);
		    	
		    }
		
			str=gson.toJson(code);
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}


}
