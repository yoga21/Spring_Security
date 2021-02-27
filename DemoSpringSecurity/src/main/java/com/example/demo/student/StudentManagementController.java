package com.example.demo.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
	
	
	

	private static final List<Student> students=Arrays.asList(
			
			new Student(1, "Suyog Jadhav"),
			new Student(2, "Mayuri Powar"),
			new Student(3, "Sanjay Jadhav")
			
		
			);
	
	//Method level permission based Authentication
	//hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuhority('permission')
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRANEE')")
	public List<Student>getAllStudents(){
		
		System.out.println("getAllStudent");
		return students;
		
	}
	
	@PostMapping

	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody Student student) {
		
		System.out.println("register a student");
		System.out.println(student);
	}
	
	@DeleteMapping(path="{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		
		System.out.println("Delete a student"+studentId);
		System.out.println(studentId);
		
	}
	
	@PutMapping(path="{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent( @PathVariable("studentId") Integer studentId,@RequestBody Student student) {
		
		System.out.println("Update the student"+studentId);
		System.out.println(String.format("%s %s" ,student, student));
	}

}
