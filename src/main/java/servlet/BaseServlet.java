package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class BaseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req,resp);
	}
	public void setCharacterEncoding(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}
	public void toPrintJson(HttpServletResponse response, Object obj) throws IOException {
		Gson gson=new Gson();
		String str=gson.toJson(obj);
		PrintWriter out=response.getWriter();
		out.print(str);
	}
	public  <T> T parseRequest(HttpServletRequest request, Class<T> cls) throws InstantiationException, IllegalAccessException, NumberFormatException, IllegalArgumentException, InvocationTargetException {
		T t=null;
		Field [] fields=cls.getDeclaredFields();
		Method []methods=cls.getDeclaredMethods();
		t=cls.newInstance();
		for(Field f:fields) {
			String name=f.getName();
			String value=request.getParameter(name);
			if(null==value||"".equals(name)) {
				continue;
			}
			for(Method m:methods) {
				if(("set"+name).equalsIgnoreCase(m.getName())){
					String typeName=m.getParameterTypes()[0].getName();
					if("java.lang.Integer".equals(typeName)){
						m.invoke(t, Integer.parseInt(value));
					}else if("java.lang.Double".equals(typeName)){
						m.invoke(t, Double.parseDouble(value));
					}else if("java.lang.Float".equals(typeName)){
						m.invoke(t, Float.parseFloat(value));
					}else if("java.lang.Long".equals(typeName)){
						m.invoke(t, Long.parseLong(value));
					}else if("java.lang.String".equals(typeName)){
						m.invoke(t, value);
					}else {
						
					}
				}
			}
		}
		return t;
	}
	
}
