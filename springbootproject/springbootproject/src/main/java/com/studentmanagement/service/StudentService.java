package com.studentmanagement.service;

import java.util.List;
import java.util.Optional;

import com.studentmanagement.beans.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student saveStudent(Student student);

	void deleteById(int id);

	Student findStudentById(int studentId);

}
