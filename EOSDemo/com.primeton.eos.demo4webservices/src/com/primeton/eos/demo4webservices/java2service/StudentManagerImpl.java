package com.primeton.eos.demo4webservices.java2service;

import com.primeton.eos.demo4webservices.java2service.DataModel.Student;

public class StudentManagerImpl implements StudentManager {

	public void addStudent(Student student) {
		System.out.println("StudentManagerImpl invoker ....");
	}

}
