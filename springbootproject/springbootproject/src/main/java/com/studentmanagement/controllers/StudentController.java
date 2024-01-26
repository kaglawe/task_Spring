package com.studentmanagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.beans.Student;
import com.studentmanagement.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		
				List<Student> list = studentService.getAllStudents();
				if(list.size()<=0) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				}
				return ResponseEntity.of(Optional.of(list));
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentId(@PathVariable int id) {
		
		Student student = studentService.findStudentById(id);
		if(student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(student));
	}
	
	@PostMapping("/create")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		
		try {
			Student newStudent =  studentService.saveStudent(student);	
			return ResponseEntity.of(Optional.of(newStudent));
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
		
		Student availableStudent = studentService.findStudentById(id);
		
		if(availableStudent != null) {
			availableStudent.setFirstName(student.getFirstName());
			availableStudent.setLastName(student.getLastName());
			availableStudent.setAge(student.getAge());
			availableStudent.setRollNo(student.getRollNo());
			
			return this.saveStudent(availableStudent);
		}
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
		
		try {
			
			studentService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}

}
