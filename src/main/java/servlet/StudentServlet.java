package servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.yxh.mapper.StudentMapper;
import com.yxh.mapper.classMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.class1;
import com.yxh.pojo.code;

@WebServlet("/Student.action")
public class StudentServlet extends BaseServlet {
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=request.getParameter("op");
		if("insert".equals(op)) {
			doinsert(request,response);
		}else if("update".equals(op)) {
			doupdate(request,response);
		}else if("login".equals(op)) {
			doLogin(request,response);
		}else if("where14".equals(op)) {
			dowhere14(request,response);
		}
	}

	private void dowhere14(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		StudentMapper userMapper=context.getBean("StudentMapper",StudentMapper.class);
		
		
			
		try {
			Student stu = parseRequest(request, Student.class);
			code code=new code();
			List<Student> stu2=userMapper.selectwhere14(stu);
			System.out.println(stu2);
		
		
				code.setCode(0);
				code.setData(stu2);
				toPrintJson(response,code);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		StudentMapper userMapper=context.getBean("StudentMapper",StudentMapper.class);
		
		
			
		try {
			Student stu = parseRequest(request, Student.class);
			code code=new code();
			Student stu2=userMapper.selectbyid(stu);
			System.out.println(stu2);
		
			if(stu2==null) {
				doinsert(request,response);
			}else {
				if(stu2.getSclass()!=null) {
				classMapper classMapper=context.getBean("classMapper",classMapper.class);
				class1 class1=new class1();
				class1.setCid(stu2.getSclass());
					List<class1> class2=classMapper.selectbyid(class1);
					for(class1 class3:class2) {
						stu2.setCname(class3.getCname());
					}
				}
				code.setCode(0);
				code.setData(stu2);
				toPrintJson(response,code);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void doupdate(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		StudentMapper userMapper=context.getBean("StudentMapper",StudentMapper.class);
		
		
			
		try {
			Student stu = parseRequest(request, Student.class);
			code code=new code();
			if(userMapper.updatestudent(stu)==1) {
				code.setCode(0);
			}else {
				code.setCode(1);
			}
			code.setData(stu);
			toPrintJson(response,code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doinsert(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		StudentMapper userMapper=context.getBean("StudentMapper",StudentMapper.class);
	
		
			
		try {
			code code=new code();
			code.setCode(1);
			Student stu = parseRequest(request, Student.class);
			code.setData(userMapper.insertstudent(stu));
			toPrintJson(response,code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
