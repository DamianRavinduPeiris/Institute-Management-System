package com.damian.apexedu.dto;

public class UNDERGRAD_Fees_DTO {
    private String date;
    private String student_id;
    private String student_name;
    private String payment_description;
    private double amount;

    public UNDERGRAD_Fees_DTO() {
    }

    public UNDERGRAD_Fees_DTO(String date, String student_id, String student_name, String payment_description, double amount) {
        this.date = date;
        this.student_id = student_id;
        this.student_name = student_name;
        this.payment_description = payment_description;
        this.amount = amount;
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

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getPayment_description() {
        return payment_description;
    }

    public void setPayment_description(String payment_description) {
        this.payment_description = payment_description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
