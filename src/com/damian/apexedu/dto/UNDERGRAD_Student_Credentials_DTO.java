package com.damian.apexedu.dto;

public class UNDERGRAD_Student_Credentials_DTO {
    private String student_id;
    private String username;
    private String password;

    public UNDERGRAD_Student_Credentials_DTO() {
    }

    public UNDERGRAD_Student_Credentials_DTO(String student_id, String username, String password) {
        this.student_id = student_id;
        this.username = username;
        this.password = password;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
