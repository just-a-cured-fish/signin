package com.yxh.mapper;

import java.util.List;

import com.yxh.pojo.Student;
import com.yxh.pojo.news;
import com.yxh.pojo.thesign;

public interface thesignMapper {
	public int insertthesign(thesign thesign);
	public List<thesign> selectbyid(thesign thesign);
	public List<thesign> selectend(thesign thesign);
	public List<thesign> selectnotend(thesign thesign);
	public List<thesign> selectsame(thesign thesign);
	public int[] selectbysamekey(thesign thesign);
	public List<thesign> selectbyid2(thesign thesign);
	public List<thesign> selectnotsign(Student Student);
	public List<thesign> selectendis(Student Student);
	public List<thesign> selectendnot(Student Student);
	
	public List<thesign> selectbythesignid(thesign thesign);
	public int insertthesignmush(List<thesign> thesign1,thesign thesign);
}
