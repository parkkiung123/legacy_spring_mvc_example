package com.example.dto;
import javax.validation.constraints.NotBlank;

public class LoginForm {

    @NotBlank(message = "아이디를 입력하세요.")
    private String id;

    @NotBlank(message = "이름을 입력하세요.")
    private String name;

    private String userType; // "student" or "teacher"

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
}
