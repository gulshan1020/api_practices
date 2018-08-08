package com.example.service;

import java.util.List;

import com.example.bean.Student;


public interface StudentService {
	
	public void createStudent(Student user);
    public List<Student> getStudents();
    public Student findById(int id);
    public void update(Student stu);
    public void deleteStudentById(int id);
    public void updatePartially(Student user, int id);

}
