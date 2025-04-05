package com.example.repository;

import com.example.dto.Student;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface StudentRepository {
    List<Student> findAll();
    Student findById(Integer id);
    void insert(Student student);
    void update(Student student);
    void delete(Integer id);
}
