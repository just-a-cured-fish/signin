package com.yxh.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.yxh.pojo.Student;
import com.yxh.pojo.User;
import com.yxh.pojo.class1;

public class StudentMapperlmpl implements StudentMapper{
	private SqlSessionTemplate sqlSession;

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public int insertstudent(Student stu) {
		// TODO Auto-generated method stub
		
	
		StudentMapper mapper=sqlSession.getMapper(StudentMapper.class);
		int a=mapper.insertstudent(stu);
		return a;
	}
	@Override
	public int updatestudent(Student stu) {
		StudentMapper mapper=sqlSession.getMapper(StudentMapper.class);
		int a=mapper.updatestudent(stu);
		return a;
	}
	@Override
	public Student selectbyid(Student stu) {
		// TODO Auto-generated method stub
		StudentMapper mapper=sqlSession.getMapper(StudentMapper.class);
		Student s=mapper.selectbyid(stu);
		return s;
	}
	@Override
	public List<Student> selectbysclass(class1 class1) {
		StudentMapper mapper=sqlSession.getMapper(StudentMapper.class);
		List<Student> s=mapper.selectbysclass(class1);
		return s;
	}
	@Override
	public List<Student> selectwhere14(Student stu) {
		StudentMapper mapper=sqlSession.getMapper(StudentMapper.class);
		List<Student> s=mapper.selectwhere14(stu);
		return s;
	}

}
