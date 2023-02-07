package com.damian.apexedu.entity;

import com.damian.apexedu.entity.util.SuperEntity;

public class UNDERGRAD_Attendance implements SuperEntity {
    private String date;
    private String student_id;
    private String status;

    public UNDERGRAD_Attendance() {
    }

    public UNDERGRAD_Attendance(String date, String student_id, String status) {
        this.date = date;
        this.student_id = student_id;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
