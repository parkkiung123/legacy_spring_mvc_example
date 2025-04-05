package com.example.repository;

import com.example.dto.Teacher;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TeacherRepository {
    List<Teacher> findAll();
    Teacher findById(Integer id);
    void insert(Teacher student);
    void update(Teacher student);
    void delete(Integer id);
}
