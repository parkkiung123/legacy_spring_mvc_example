package com.example;

import com.example.controller.StudentController;
import com.example.dto.Student;
import com.example.service.DBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",   // DBService 등 등록된 곳
    "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"    // controller 등 등록된 곳
})
public class StudentControllerTest {

    @Autowired
    private StudentController studentController;

    @Autowired
    private DBService dbService;

    @Test
    @Transactional
    @Rollback(true)
    public void testGetStudentById() {
        // Arrange: 테스트용 학생을 등록
        Student student = new Student(16, "홍길동", 3, "김선생", 90, 80, 85, 95, 70);
        dbService.addStudent(student);

        // Act
        Student result = studentController.getStudentById(16);

        // Assert
        assertNotNull(result);
        assertEquals("홍길동", result.name());
        assertEquals(Integer.valueOf(90), result.korean());
    }
}
