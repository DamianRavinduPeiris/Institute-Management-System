package com.damian.apexedu.dto;

public class Lecturer_Details_DTO {
    private String lecturer_id;
    private String program_id;
    private String lecturer_name;
    private String lecturer_address;
    private String lecturer_email;
    private int lecturer_telephone;
    private double basic_salary;

    public Lecturer_Details_DTO() {
    }

    public Lecturer_Details_DTO(String lecturer_id, String program_id, String lecturer_name, String lecturer_address, String lecturer_email, int lecturer_telephone, double basic_salary) {
        this.lecturer_id = lecturer_id;
        this.program_id = program_id;
        this.lecturer_name = lecturer_name;
        this.lecturer_address = lecturer_address;
        this.lecturer_email = lecturer_email;
        this.lecturer_telephone = lecturer_telephone;
        this.basic_salary = basic_salary;
    }

    public String getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(String lecturer_id) {
        this.lecturer_id = lecturer_id;
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public String getLecturer_address() {
        return lecturer_address;
    }

    public void setLecturer_address(String lecturer_address) {
        this.lecturer_address = lecturer_address;
    }

    public String getLecturer_email() {
        return lecturer_email;
    }

    public void setLecturer_email(String lecturer_email) {
        this.lecturer_email = lecturer_email;
    }

    public int getLecturer_telephone() {
        return lecturer_telephone;
    }

    public void setLecturer_telephone(int lecturer_telephone) {
        this.lecturer_telephone = lecturer_telephone;
    }

    public double getBasic_salary() {
        return basic_salary;
    }

    public void setBasic_salary(double basic_salary) {
        this.basic_salary = basic_salary;
    }
}
