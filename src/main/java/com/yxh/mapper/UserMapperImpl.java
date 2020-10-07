package com.yxh.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.yxh.pojo.User;

public class UserMapperImpl implements UserMapper{
	private SqlSessionTemplate sqlSession;
	public List<User> selectUser() {
		// TODO Auto-generated method stub
	UserMapper mapper=sqlSession.getMapper(UserMapper.class);
	return mapper.selectUser();
	}
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

}
