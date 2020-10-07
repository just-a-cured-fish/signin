package com.yxh.mapper;

import com.yxh.pojo.Student;
import com.yxh.pojo.Teacher;

public interface teacherMapper {
	public int insertteacher(Teacher stu);
	public int updateteacher(Teacher stu);
	public Teacher selectbyid(Teacher stu);
	public Teacher selectBlog(Teacher stu);
}
