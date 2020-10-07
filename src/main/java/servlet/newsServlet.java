package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yxh.mapper.allhaveMapper;
import com.yxh.mapper.newsMapper;
import com.yxh.pojo.allhave;
import com.yxh.pojo.code;
import com.yxh.pojo.news;
@WebServlet("/news.action")
public class newsServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=request.getParameter("op");
		if("findfive".equals(op)) {
			dofind5(request,response);
		}
	}

	private void dofind5(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");	
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		newsMapper newsMapper=context.getBean("newsMapper",newsMapper.class);
		List<news> news=newsMapper.selectvalue();
		code code=new code();
		if(news!=null) {
			code.setCode(0);
		}else {
			code.setCode(1);
		}
		code.setData(news);
	
		try {
			toPrintJson(response,code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
