package com.yxh.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.yxh.pojo.Student;
import com.yxh.pojo.thesign;

public class thesignMapperImpl implements thesignMapper{
	private SqlSessionTemplate sqlSession;

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public int insertthesign(thesign thesign) {
		// TODO Auto-generated method stub
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
	return mapper.insertthesign(thesign);
	}
	@Override
	public List<thesign> selectbyid(thesign thesign) {
		// TODO Auto-generated method stub
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
	return mapper.selectbyid(thesign);
	}
	@Override
	public List<thesign> selectbyid2(thesign thesign) {
		// TODO Auto-generated method stub
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
	return mapper.selectbyid2(thesign);
	}
	@Override
	public List<thesign> selectnotsign(Student Student) {
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
	return mapper.selectnotsign(Student);
	}
	@Override
	public List<thesign> selectendis(Student Student) {
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
		return mapper.selectendis(Student);
	}
	@Override
	public List<thesign> selectendnot(Student Student) {
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
		return mapper.selectendnot(Student);
	}
	@Override
	public List<thesign> selectbythesignid(thesign Student) {
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
		return mapper.selectbythesignid(Student);
	}
	@Override
	public int insertthesignmush(List<thesign> thesign1,thesign thesign) {
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
		return mapper.insertthesignmush(thesign1,thesign);
	}
	@Override
	public List<thesign> selectsame(thesign thesign) {
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
		return mapper.selectsame(thesign);
	}
	@Override
	public int[] selectbysamekey(thesign thesign) {
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
		return mapper.selectbysamekey(thesign);
	}
	@Override
	public List<thesign> selectend(thesign thesign) {
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
		return mapper.selectend(thesign);
	}
	@Override
	public List<thesign> selectnotend(thesign thesign) {
		thesignMapper mapper=sqlSession.getMapper(thesignMapper.class);
		return mapper.selectnotend(thesign);
	}
		
}
