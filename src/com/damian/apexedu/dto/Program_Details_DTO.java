package com.damian.apexedu.dto;

public class Program_Details_DTO {
    private String program_id;
    private String program_name;
    private  String program_fee;

    public Program_Details_DTO(String program_id, String program_name, String program_fee) {
        this.program_id = program_id;
        this.program_name = program_name;
        this.program_fee = program_fee;
    }

    public Program_Details_DTO() {
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getProgram_fee() {
        return program_fee;
    }

    public void setProgram_fee(String program_fee) {
        this.program_fee = program_fee;
    }
}
