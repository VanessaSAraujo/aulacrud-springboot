package com.example.demo;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return runner -> {
//			saveStudent(new Student("Primeiro nome Teste1","Último nome Teste1", "Email Teste1" ), studentRepository);
//		getStudent(7, studentRepository);
//			getAllStudents(studentRepository);
//			getByLastName("Último nome Teste1", studentRepository);
//			updateStudent(8, studentRepository);
//			deleteStudentById(8, studentRepository);
			deleteAllStudents(studentRepository);
		};
	}

	private void deleteAllStudents(StudentRepository studentRepository) {
		studentRepository.deleteAll();
	}

	private void saveStudent(Student student, StudentRepository studentRepository) {
		studentRepository.save(student);
	}

	private void getStudent(int id, StudentRepository studentRepository) {
		Student student = studentRepository.findById(id);

		System.out.println(student);
	}

	private void getAllStudents(StudentRepository studentRepository) {
		List<Student> students = studentRepository.findAll();

		students.forEach(s -> {
			System.out.println(s);
		});
	}

	private void getByLastName(String lastName, StudentRepository studentRepository) {
		List<Student> students = studentRepository.findByLastName(lastName);

		students.forEach(s -> {
			System.out.println(s);
		});
	}

	private void updateStudent(int id, StudentRepository studentRepository) {
		Student student = new Student("Teste2", "Sobrenome2", "email2");
		studentRepository.update(id, student);
	}

	private void deleteStudentById(int id, StudentRepository studentRepository) {
		studentRepository.deleteById(id);
	}

}
