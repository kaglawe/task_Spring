package com.studentmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmanagement.beans.Student;

public interface StudentDao extends JpaRepository<Student, Integer> {

	
	

}
