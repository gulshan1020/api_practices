package com.example.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bean.Student;
import com.example.service.StudentService;

@Service
public class StudentServiceImpl  implements StudentService {
	
	
	private static List<Student> students;

    static {
    	students = dummyStudent();
    }

	@Override
	public void createStudent(Student std) {
		students.add(std);
		
	}

	@Override
	public List<Student> getStudents() {
		return students;
	}

	@Override
	public Student findById(int id) {
		for(Student std:students) {
			if(std.getId()==id) {
				return std;
			}
		}
		return null;
	}

	@Override
	public void update(Student stu) {
		  int index = students.indexOf(stu);
		  students.set(index, stu);
		
	}

	@Override
	public void deleteStudentById(int id) {
		// TODO Auto-generated method stub
		Iterator<Student> itr = students.iterator();
		while(itr.hasNext()) {
			Student st =(Student) itr.next();
			if(st.getId()==id) {
				itr.remove();
			}
		}
		
	}

	@Override
	public void updatePartially(Student currentstudent, int id) {
		// TODO Auto-generated method stub
		for(Student std:students) {
			if(std.getId()==id) {
				if(currentstudent.getCountry()!=null) {
					std.setId(id);
					std.setCountry(currentstudent.getCountry());
				}
				std.setName(std.getName());
				update(std);
			}
		}
		
	}
	
	 private static List<Student> dummyStudent() {
	        // TODO Auto-generated method stub
	        List<Student> stu = new ArrayList<Student>();
	        stu.add(new Student(14221, "John", "INDIA"));
	        stu.add(new Student(14222, "Ben", "UK"));
	        stu.add(new Student(14223, "Andrew", "INDIA"));
	        stu.add(new Student(14224, "Samuael", "USA"));
	        return stu;
	    }

}
