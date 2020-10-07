package com.yxh.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.yxh.pojo.news;

public class newsMapperImpl implements newsMapper{

	@Override
	public int insertnews(List<news> news) {
		newsMapper mapper=sqlSession.getMapper(newsMapper.class);
		return mapper.insertnews(news);
	}
	private SqlSessionTemplate sqlSession;

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public List<news> selectvalue() {
		// TODO Auto-generated method stub
		newsMapper mapper=sqlSession.getMapper(newsMapper.class);
		return mapper.selectvalue();
	}
}
