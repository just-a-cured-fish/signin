package com.yxh.mapper;

import org.mybatis.spring.SqlSessionTemplate;

import com.yxh.pojo.Teacher;

public class teacherMapperImpl implements teacherMapper{
	private SqlSessionTemplate sqlSession;

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public int insertteacher(Teacher stu) {
		teacherMapper mapper=sqlSession.getMapper(teacherMapper.class);
		int a=mapper.insertteacher(stu);
		return a;
	}

	@Override
	public int updateteacher(Teacher stu) {
		teacherMapper mapper=sqlSession.getMapper(teacherMapper.class);
		int a=mapper.updateteacher(stu);
		return a;
	}

	@Override
	public Teacher selectbyid(Teacher stu) {
		teacherMapper mapper=sqlSession.getMapper(teacherMapper.class);
		Teacher t=mapper.selectbyid(stu);
		return t;
	}
	@Override
	public Teacher selectBlog(Teacher stu) {
		teacherMapper mapper=sqlSession.getMapper(teacherMapper.class);
		Teacher t=mapper.selectBlog(stu);
		return t;
	}

}
