package com.example.demo.repositories;

import com.example.demo.entities.Student;
import com.example.demo.interfaces.IStudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {
    private EntityManager entityManager;

    @Autowired
    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Student student) {
        this.entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return this.entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
//        o select é da classe e não do BD
        return entityManager.createQuery("select s from Student s ORDER BY s.firstName", Student.class). getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s WHERE s.lastName LIKE :name", Student.class);
        query.setParameter("name", "%" + lastName + "%");
        return  query.getResultList();
    }

    @Transactional
    @Override
    public void update(int id, Student student) {
        Student studentInDb = this.entityManager.find(Student.class, id);

        studentInDb.setFirstName(student.getFirstName());
        studentInDb.setLastName(student.getLastName());
        studentInDb.setEmail(student.getEmail());

        this.entityManager.merge(studentInDb);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Query query = entityManager.createQuery("delete from Student s WHERE s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    @Transactional
    @Override
    public void deleteAll() {
        Query query = entityManager.createQuery("delete from Student s");
        query.executeUpdate();
    }
}
