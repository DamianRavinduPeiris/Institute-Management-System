package com.damian.apexedu.entity;

import com.damian.apexedu.entity.util.SuperEntity;

public class CCS_Student_Credentials implements SuperEntity {
    private String student_id;
    private String username;
    private String password;

    public CCS_Student_Credentials() {
    }
    public CCS_Student_Credentials(String student_id, String username, String password) {
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
