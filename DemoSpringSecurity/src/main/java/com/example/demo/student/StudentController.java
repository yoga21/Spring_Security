package com.example.demo.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/student")
public class StudentController {
	
	
	private static final List<Student> students=Arrays.asList(
			
			new Student(1, "Suyog Jadhav"),
			new Student(2, "Mayuri Powar"),
			new Student(3, "Sanjay Jadhav")
			
		
			);
			
	
			
			
			
			
	
	@GetMapping(path="{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return students.stream()
				.filter(student->studentId.equals(student.getStudentId()))
				.findFirst()
				.orElseThrow(()->new IllegalStateException("Student"+ studentId + "does not exists"));
		
		
		
	}

}
