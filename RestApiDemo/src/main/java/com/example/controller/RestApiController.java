package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.bean.Student;
import com.example.service.StudentService;

@RestController
@RequestMapping(value={"/stu"})
public class RestApiController {
	
	@Autowired
	StudentService studentservice;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Student stu = studentservice.findById(id);
        if (stu == null) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(stu, HttpStatus.OK);
    }
	
	 	@PostMapping(value="/create",headers="Accept=application/json")
	    public ResponseEntity<Void> createStudent(@RequestBody Student stu, UriComponentsBuilder ucBuilder){
	        System.out.println("Creating User "+stu.getName());
	        studentservice.createStudent(stu);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(stu.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	 	
	 	@GetMapping(value="/get", headers="Accept=application/json")
	    public List<Student> getAllStudents() {
	        List<Student> student=studentservice.getStudents();
	        return student;

	    }
}
