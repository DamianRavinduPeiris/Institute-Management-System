package com.damian.apexedu.entity;

import com.damian.apexedu.entity.util.SuperEntity;

public class Lecturer_Credentials implements SuperEntity {
    private String lecturer_id;
    private String username;
    private String  password;

    public Lecturer_Credentials() {
    }

    public Lecturer_Credentials(String lecturer_id, String username, String password) {
        this.lecturer_id = lecturer_id;
        this.username = username;
        this.password = password;
    }

    public String getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(String lecturer_id) {
        this.lecturer_id = lecturer_id;
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
