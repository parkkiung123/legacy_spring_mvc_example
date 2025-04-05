package com.example.service;

import com.example.dto.Student;
import com.example.dto.Teacher;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DBService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public DBService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id);
    }
    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id);
    }

    @Transactional
    public void addStudent(Student student) {
        studentRepository.insert(student);
    }
    @Transactional
    public void addTeacher(Teacher teacher) {
        teacherRepository.insert(teacher);
    }
    
    @Transactional
    public void updateStudent(Student student) {
        studentRepository.update(student);
    }
    @Transactional
    public void updateTeacher(Teacher teacher) {
        teacherRepository.update(teacher);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        studentRepository.delete(id);
    }
    @Transactional
    public void deleteTeacher(int id) {
        teacherRepository.delete(id);
    }
}
