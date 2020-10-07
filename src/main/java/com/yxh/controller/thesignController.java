package com.yxh.controller;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import com.google.gson.GsonBuilder;
import com.yxh.mapper.StudentMapper;
import com.yxh.mapper.signinMapper;
import com.yxh.mapper.teacherMapper;
import com.yxh.mapper.thesignMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.Teacher;
import com.yxh.pojo.code;
import com.yxh.pojo.signin;
import com.yxh.pojo.thesign;

@RestController
public class thesignController {
	@RequestMapping(value="/opensign",produces = "text/html;charset=UTF-8")
	public String opensign(thesign thesign) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			code.setCode(thesignMapper.insertthesign(thesign)-1);	
			code.setData(thesign);	
			str=gson.toJson(code);
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping(value="/openmuchsign",produces = "text/html;charset=UTF-8")
	public String openmuchsign(thesign thesign,Integer amount) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		
		List<thesign> list=new ArrayList();
		String str="";
		
		
		 
		for(int i=0;i<=amount;i++) {
			thesign thesign1=new thesign();
		
			Calendar c = Calendar.getInstance();  
	        c.setTime(thesign.getStartday());  
	        c.add(Calendar.DAY_OF_MONTH, i);           //利用Calendar 实现 Date日期+1天  
	        
	        thesign1.setStartday(c.getTime());
//	        Calendar c1 = Calendar.getInstance();  
	        c.setTime(thesign.getEndday());  
	        c.add(Calendar.DAY_OF_MONTH, i);           //利用Calendar 实现 Date日期+1天  
	        
	        thesign1.setEndday(c.getTime());
	        System.out.println(thesign1);
	        list.add(thesign1);
		}
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		code code=new code();
		try {
			code.setCode(thesignMapper.insertthesignmush(list,thesign));	
			code.setData(list);	
			str=gson.toJson(code);
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping(value="/selectbythesignid",produces = "text/html;charset=UTF-8")
	public String json6(thesign thesign) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<thesign> arrlist=new ArrayList<thesign>();
			arrlist=thesignMapper.selectbythesignid(thesign);
			if(arrlist!=null) {
			code.setData(arrlist);	
			code.setCode(0);
			str=gson.toJson(code);
			}else {
				code.setCode(1);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	@RequestMapping(value="/selectbysamekey",produces = "text/html;charset=UTF-8")
	public String jsonsamekey(thesign thesign,signin signin9) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		List<List<signin>> signin2=new ArrayList<>();

		List<List<Student>> studenttotal=new ArrayList<>();
		List<signin> signin1=new ArrayList<>();
		List<signin> signinnew=new ArrayList<>();
		try {
		
			int[] a=thesignMapper.selectbysamekey(thesign);
			for(int i:a) {
				signin signin=new signin();
				signin.setThesignid(i);
				signin.setIstrue(signin9.getIstrue());
				signin.setIstrue2(signin9.getIstrue2());
				signin.setIstrue3(signin9.getIstrue3());				
				signinnew=signinMapper.selectchoose(signin);
				for(signin signinadd:signinnew) {
					signin1.add(signinadd);
				}
//				thesign thesign1=new thesign();
				
//				List<thesign> listthesign=thesignMapper.selectbythesignid(thesign1);
				List<Student> stu=signinMapper.selectstunot(signin);
				studenttotal.add(stu);
//				for(Student st:stu) {
//					signin signinnew=new signin();
//					signinnew.setId(-1);
//					signinnew.setDan(listthesign.get(0).getTitle()+" "+formatter.format(listthesign.get(0).getStartday())+"未签到");
//					signinnew.setSname(st.getSname());
//					signin1.add(signinnew);
//				}
				
				
				
			}
			if(signin1!=null) {
				code.setData(signin1);	
					
				code.setData1(studenttotal);
				code.setCode(0);
				str=gson.toJson(code);
				}else {
					code.setCode(1);
				}
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	@RequestMapping(value="/selectbysamekey2",produces = "text/html;charset=UTF-8")
	public String json23(thesign thesign) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		List<List<signin>> signin2=new ArrayList<>();
		List<List<signin>> signin3=new ArrayList<>();
		List<List<signin>> signin4=new ArrayList<>();
		List<List<signin>> signin5=new ArrayList<>();
		List<List<Student>> studenttotal=new ArrayList<>();
		List<signin> signin1=new ArrayList<>();
		try {
		
			int[] a=thesignMapper.selectbysamekey(thesign);
			for(int i:a) {
				signin signin=new signin();
				signin.setThesignid(i);
				signin.setIstrue(0);				
				signin.setIstrue3(0);				
				signin1=signinMapper.selectchoose(signin);
				signin2.add(signin1);
				signin.setIstrue(0);				
				signin.setIstrue3(1);				
				signin1=signinMapper.selectchoose(signin);
				signin3.add(signin1);
				signin.setIstrue(1);				
				signin.setIstrue3(0);				
				signin1=signinMapper.selectchoose(signin);
				signin4.add(signin1);
				signin.setIstrue(1);				
				signin.setIstrue3(1);				
				signin1=signinMapper.selectchoose(signin);
				signin5.add(signin1);
//				thesign thesign1=new thesign();
				
//				List<thesign> listthesign=thesignMapper.selectbythesignid(thesign1);
				List<Student> stu=signinMapper.selectstunot(signin);
				studenttotal.add(stu);
//				for(Student st:stu) {
//					signin signinnew=new signin();
//					signinnew.setId(-1);
//					signinnew.setDan(listthesign.get(0).getTitle()+" "+formatter.format(listthesign.get(0).getStartday())+"未签到");
//					signinnew.setSname(st.getSname());
//					signin1.add(signinnew);
//				}
				
				
				
			}
			if(signin1!=null) {
				code.setData(signin2);	
				code.setData2(signin3);	
				code.setData3(signin4);
				code.setData4(signin5);	
				code.setData1(studenttotal);
				code.setCode(0);
				str=gson.toJson(code);
				}else {
					code.setCode(1);
				}
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	@RequestMapping(value="/selectsame",produces = "text/html;charset=UTF-8")
	public String json22(thesign thesign) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<thesign> arrlist=new ArrayList<thesign>();
			arrlist=thesignMapper.selectsame(thesign);
			if(arrlist!=null) {
			code.setData(arrlist);	
			code.setCode(0);
			str=gson.toJson(code);
			}else {
				code.setCode(1);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	@RequestMapping(value="/select",produces = "text/html;charset=UTF-8")
	public String select(thesign thesign) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<thesign> arrlist=new ArrayList<thesign>();
			arrlist=thesignMapper.selectbyid(thesign);
			if(arrlist!=null) {
			code.setData(arrlist);	
			code.setCode(0);
			str=gson.toJson(code);
			}else {
				code.setCode(1);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	@RequestMapping(value="/selectnotend",produces = "text/html;charset=UTF-8")
	public String json229(thesign thesign) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<thesign> arrlist=new ArrayList<thesign>();
			arrlist=thesignMapper.selectnotend(thesign);
			if(arrlist!=null) {
			code.setData(arrlist);	
			code.setCode(0);
			str=gson.toJson(code);
			}else {
				code.setCode(1);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	@RequestMapping(value="/selectend",produces = "text/html;charset=UTF-8")
	public String json29(thesign thesign) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<thesign> arrlist=new ArrayList<thesign>();
			arrlist=thesignMapper.selectend(thesign);
			if(arrlist!=null) {
			code.setData(arrlist);	
			code.setCode(0);
			str=gson.toJson(code);
			}else {
				code.setCode(1);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	@RequestMapping(value="/selectinstudent",produces = "text/html;charset=UTF-8")
	public String json3(thesign thesign) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<thesign> arrlist=new ArrayList<thesign>();
			arrlist=thesignMapper.selectbyid2(thesign);
			if(arrlist!=null) {
			code.setData(arrlist);	
			code.setCode(0);
			str=gson.toJson(code);
			}else {
				code.setCode(1);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	@RequestMapping(value="/findnotsign",produces = "text/html;charset=UTF-8")
	public String json4(Student Student) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		StudentMapper StudentMapper=context.getBean("StudentMapper",StudentMapper.class);
		Student=StudentMapper.selectbyid(Student);
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		code code=new code();
		try {
			List<thesign> arrlist=new ArrayList<thesign>();
			arrlist=thesignMapper.selectnotsign(Student);
			if(arrlist!=null) {
			code.setData(arrlist);	
			code.setCode(0);
			str=gson.toJson(code);
			}else {
				code.setCode(1);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	@RequestMapping(value="/findendnotsign",produces = "text/html;charset=UTF-8")
	public String json5(Student Student) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		StudentMapper StudentMapper=context.getBean("StudentMapper",StudentMapper.class);
		Student=StudentMapper.selectbyid(Student);
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<thesign> arrlist=new ArrayList<thesign>();
			arrlist=thesignMapper.selectendnot(Student);
			
	
			if(arrlist!=null) {
			code.setData(arrlist);	
			code.setCode(0);
			str=gson.toJson(code);
			}else {
				code.setCode(1);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	@RequestMapping(value="/findendissign",produces = "text/html;charset=UTF-8")
	public String json6(Student Student) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		thesignMapper thesignMapper=context.getBean("thesignMapper",thesignMapper.class);
		StudentMapper StudentMapper=context.getBean("StudentMapper",StudentMapper.class);
		Student=StudentMapper.selectbyid(Student);
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<thesign> arrlist=new ArrayList<thesign>();
			arrlist=thesignMapper.selectendis(Student);
			if(arrlist!=null) {
			code.setData(arrlist);	
			code.setCode(0);
			str=gson.toJson(code);
			}else {
				code.setCode(1);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	  @InitBinder  
	  protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {  
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	       
	        CustomDateEditor editor = new CustomDateEditor(df, true);//true表示允许为空，false反之  
	        
	        binder.registerCustomEditor(Date.class, editor);  
	    }  
	
}
