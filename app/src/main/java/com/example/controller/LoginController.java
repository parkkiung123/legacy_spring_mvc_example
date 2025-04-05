package com.example.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import com.example.dto.LoginForm;
import com.example.dto.Student;
import com.example.dto.Teacher;
import com.example.service.DBService;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final DBService dbService;

    public LoginController(DBService dbService) {
        this.dbService = dbService;
    }

    @RequestMapping(value = "/login/{userType}", method = RequestMethod.POST)
    public String login(@PathVariable("userType") String userType,
                        @ModelAttribute("loginForm") @Valid LoginForm form,
                        BindingResult bindingResult,
                        HttpSession session,
                        Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("로그인 실패: " + bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("userType", userType);
            return "login"; // 로그인 페이지로 다시 이동
        } else {
            Object found = switch (userType) {
                case "teacher" -> dbService.getTeacherById(Integer.parseInt(form.getId()));
                case "student" -> dbService.getStudentById(Integer.parseInt(form.getId()));
                default -> throw new IllegalArgumentException("유저 타입이 유효하지 않습니다 (teacher 및 student 만 가능) : " + userType);
            };

            boolean isNameCorrect = found instanceof Student student && student.name().equals(form.getName()) ||
                                    found instanceof Teacher teacher && teacher.name().equals(form.getName());

            if (found != null && isNameCorrect) {
                logger.info("로그인 성공: " + form.getId() + ", " + form.getName());
                form.setUserType(userType);
                session.setAttribute("loginUser", form);
                return "redirect:/"; // 성적 페이지로 이동
            } else {
                logger.info("로그인 실패: " + form.getId() + ", " + form.getName());
                model.addAttribute("errorMessage", "아이디 또는 이름이 일치하지 않습니다.");
                model.addAttribute("userType", userType);
                return "login"; // 로그인 페이지로 다시 이동
            }
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에 저장된 사용자 정보 제거
        session.invalidate(); // 전체 세션 무효화 (또는 개별 속성 제거 가능)
        // 로그아웃 후 리다이렉트할 페이지로 이동 (예: 로그인 페이지)
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login"; // login.jsp로 이동
    }
}
