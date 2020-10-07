package com.yxh.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.yxh.pojo.class1;

public class classMapperImpl implements classMapper {
	private SqlSessionTemplate sqlSession;

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public int insertclass(class1 class1) {
		classMapper mapper=sqlSession.getMapper(classMapper.class);
		return mapper.insertclass(class1);
	}
	@Override
	public List<class1> selectbyid(class1 class1) {
		classMapper mapper=sqlSession.getMapper(classMapper.class);
		return mapper.selectbyid(class1);
	}

}
