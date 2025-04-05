package com.example.controller;

import com.example.dto.Student;
import com.example.service.DBService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final DBService dbService;

    public StudentController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return dbService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return dbService.getStudentById(id);
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        dbService.addStudent(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        dbService.updateStudent(new Student(id, student.name(), student.classNum(), student.teacher(),
                student.korean(), student.english(), student.math(), student.science(), student.history()));
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        dbService.deleteStudent(id);
    }
}
