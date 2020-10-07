package com.yxh.controller;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.yxh.mapper.StudentMapper;
import com.yxh.mapper.teacherMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.Teacher;
import com.yxh.pojo.code;

@RestController
public class teacherController {
	@RequestMapping(value="/login",produces = "text/html;charset=UTF-8")
	public String json1(Teacher teacher) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		teacherMapper userMapper=context.getBean("teacherMapper",teacherMapper.class);
		
		String str="";
		Gson gson=new Gson();
		code code=new code();
		try {
	Teacher stu2=userMapper.selectbyid(teacher);
			System.out.println(stu2);
			if(stu2==null) {
				
				code.setCode(1);
				
				code.setData(userMapper.insertteacher(teacher));
				
				str=gson.toJson(code);
				
			}else {
				code.setCode(0);
				code.setData(stu2);
				
				str=gson.toJson(code);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping(value="/updateteacher",produces = "text/html;charset=UTF-8")
	public String json2(Teacher teacher) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		teacherMapper userMapper=context.getBean("teacherMapper",teacherMapper.class);
		int a=userMapper.updateteacher(teacher);
		code code=new code();
		Gson gson=new Gson();
		if(a>0) {
			
			code.setCode(0);
			code.setData(teacher);
		}else {
			code.setCode(1);
		}
		String str=gson.toJson(code);
		return str;
		
	}
	@RequestMapping(value="/findteaclastu",produces = "text/html;charset=UTF-8")
	public String json6(Teacher teacher) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		teacherMapper userMapper=context.getBean("teacherMapper",teacherMapper.class);
	
	
		Teacher a=userMapper.selectBlog(teacher);
		code code=new code();
			Gson gson=new Gson();
		
		if(a!=null) {
			
			code.setCode(0);
			code.setData(a);
		}else {
			code.setCode(1);
		}
		String str=gson.toJson(code);
		return str;
		
	}
	
}
