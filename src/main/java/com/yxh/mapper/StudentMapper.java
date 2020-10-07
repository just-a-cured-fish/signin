package com.yxh.mapper;

import java.util.List;

import com.yxh.pojo.Student;
import com.yxh.pojo.User;
import com.yxh.pojo.class1;

public interface StudentMapper {
	public int insertstudent(Student stu);
	public int updatestudent(Student stu);
	public Student selectbyid(Student stu);
	public List<Student> selectbysclass(class1 class1);
	public List<Student> selectwhere14(Student stu);
}
