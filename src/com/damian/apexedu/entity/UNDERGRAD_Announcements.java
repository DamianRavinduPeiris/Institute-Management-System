package com.damian.apexedu.entity;

import com.damian.apexedu.entity.util.SuperEntity;

public class UNDERGRAD_Announcements implements SuperEntity {
    private String date;
    private String lecturer_name;
    private String description;
    private String due_date;

    public UNDERGRAD_Announcements() {
    }

    public UNDERGRAD_Announcements(String date, String lecturer_name, String description, String due_date) {
        this.date = date;
        this.lecturer_name = lecturer_name;
        this.description = description;
        this.due_date = due_date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}

