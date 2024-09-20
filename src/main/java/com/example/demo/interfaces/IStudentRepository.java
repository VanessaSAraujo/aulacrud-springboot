package com.example.demo.interfaces;

import com.example.demo.entities.Student;

import java.util.List;

public interface IStudentRepository {
    void save(Student student);

    Student findById(int id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(int id, Student student);

    void deleteById(int id);

    void deleteAll();
}
