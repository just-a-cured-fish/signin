package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yxh.mapper.StudentMapper;
import com.yxh.mapper.allhaveMapper;
import com.yxh.mapper.signinMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.allhave;
import com.yxh.pojo.code;
import com.yxh.pojo.signin;
@WebServlet("/signin.action")
public class signinServlet extends BaseServlet {
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=request.getParameter("op");
		if("signin".equals(op)) {
			dosignin(request,response);
		}
	}

	private void dosignin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		allhaveMapper allhaveMapper=context.getBean("allhaveMapper",allhaveMapper.class);
		try {
			signin stu = parseRequest(request, signin.class);
			
			 
				Calendar c = Calendar.getInstance();  
		        c.setTime(c.getTime());  
		        c.add(Calendar.HOUR_OF_DAY, 0);           //利用Calendar 实现 Date日期+1天  
		        
			  Date nowTime=c.getTime();
			
			
			  stu.setTime(nowTime);
			  System.out.println(nowTime);
			code code=new code();
			if(stu.getAddress().indexOf("省")!=-1) {
				String city=stu.getAddress().substring(0,stu.getAddress().indexOf("省"));
				allhave all1=new allhave();
				all1.setName(city);
				System.out.println(city);
				if(allhaveMapper.selectdan(all1).get(0)!=null) {
				allhave all=allhaveMapper.selectdan(all1).get(0);
				System.out.println(all);
				if(!all.getValue().equals("0")) {
				stu.setDan("该学生打卡地点有"+all.getValue()+"例疫情");
				System.out.println(stu);
				}
				}
			}
			
		
		
			System.out.println(stu);
			int a=signinMapper.insertinto(stu);
			code.setCode(a);
			toPrintJson(response,code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
}
