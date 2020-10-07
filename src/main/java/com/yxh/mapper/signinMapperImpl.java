package com.yxh.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.yxh.pojo.Student;
import com.yxh.pojo.signin;

public class signinMapperImpl implements signinMapper{
	private SqlSessionTemplate sqlSession;

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public int insertinto(signin signin) {
		// TODO Auto-generated method stub
		signinMapper mapper=sqlSession.getMapper(signinMapper.class);
		int a=mapper.insertinto(signin);
		return a;
	}
	@Override
	public  List<signin> selecttrue(signin signin) {
		signinMapper mapper=sqlSession.getMapper(signinMapper.class);
		return mapper.selecttrue(signin);
		
	}
	@Override
	public  List<signin> selectnottrue(signin signin) {
		signinMapper mapper=sqlSession.getMapper(signinMapper.class);
		return mapper.selectnottrue(signin);
		
	}
	@Override
	public List<signin> selectnottrue2(signin signin) {
		signinMapper mapper=sqlSession.getMapper(signinMapper.class);
		return mapper.selectnottrue2(signin);
	}
	@Override
	public List<signin> selectnottrue3(signin signin) {
		signinMapper mapper=sqlSession.getMapper(signinMapper.class);
		return mapper.selectnottrue3(signin);
	}
	@Override
	public List<signin> selectchoose(signin signin) {
		signinMapper mapper=sqlSession.getMapper(signinMapper.class);
		return mapper.selectchoose(signin);
	}
	@Override
	public List<Student> selectstunot(signin signin) {
		signinMapper mapper=sqlSession.getMapper(signinMapper.class);
		return mapper.selectstunot(signin);
	}

}
