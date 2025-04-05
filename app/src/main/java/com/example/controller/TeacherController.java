package com.example.controller;

import com.example.dto.Teacher;
import com.example.service.DBService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final DBService dbService;

    public TeacherController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return dbService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Integer id) {
        return dbService.getTeacherById(id);
    }

    @PostMapping
    public void addTeacher(@RequestBody Teacher teacher) {
        dbService.addTeacher(teacher);
    }

    @PutMapping("/{id}")
    public void updateTeacher(@PathVariable Integer id, @RequestBody Teacher teacher) {
        dbService.updateTeacher(new Teacher(id, teacher.name(), teacher.classNum()));
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Integer id) {
        dbService.deleteTeacher(id);
    }
}
