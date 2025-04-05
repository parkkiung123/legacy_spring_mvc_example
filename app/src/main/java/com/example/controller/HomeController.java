package com.example.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dto.LoginForm;
import com.example.dto.Student;
import com.example.service.DBService;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final DBService studentService;

    public HomeController(DBService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/")
    public String home(Model model, HttpSession session) {
        LoginForm form = (LoginForm) session.getAttribute("loginUser");
        if (form != null) {
            String userType = form.getUserType();
            switch (userType) {
                case "teacher":
                    logger.info("로그인한 선생님: " + form.getId() + ", " + form.getName());
                    List<Student> students = studentService.getAllStudents();
                    List<Double> averages = students.stream()
                            .mapToDouble(s -> (s.korean() + s.english() + s.math() + s.science() + s.history()) / 5.0)
                            .boxed()
                            .toList();              
                    model.addAttribute("students", students);
                    model.addAttribute("averages", averages);
                    break;
                case "student":
                    logger.info("로그인한 학생: "  + form.getId() + ", " + form.getName());
                    Student student = studentService.getStudentById(Integer.parseInt(form.getId()));
                    if (student != null) {
                        double average = (student.korean() + student.english() + student.math() + student.science() + student.history()) / 5.0;
                        model.addAttribute("students", List.of(student));
                        model.addAttribute("averages", List.of(average));
                    } else {
                        throw new IllegalArgumentException("학생 정보가 없습니다.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("잘못된 사용자 유형입니다.");                    
            }
        } else {
            logger.info("로그인한 유저가 없습니다.");
        }
        model.addAttribute("message", "Hello, Spring MVC!");
        return "home";
    }
}
