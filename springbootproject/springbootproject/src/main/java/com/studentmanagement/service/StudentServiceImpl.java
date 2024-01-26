package com.studentmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.beans.Student;
import com.studentmanagement.dao.StudentDao;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;

	@Override
	public List<Student> getAllStudents() {
		
		return studentDao.findAll();
	}
	
	@Override
	public Student findStudentById(int studentId) {
		
		return studentDao.findById(studentId).orElse(null);
		
	}

	@Override
	public Student saveStudent(Student student) {
		
		return studentDao.save(student);
	}

	@Override
	public void deleteById(int id) {
		studentDao.deleteById(id);
		
	}


}
