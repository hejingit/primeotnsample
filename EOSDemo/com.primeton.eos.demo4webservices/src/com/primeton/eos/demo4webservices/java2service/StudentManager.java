package com.primeton.eos.demo4webservices.java2service;

import org.osoa.sca.annotations.Remotable;

import com.primeton.eos.demo4webservices.java2service.DataModel.Student;

@Remotable
public interface StudentManager {
	public void addStudent(Student student);
}
