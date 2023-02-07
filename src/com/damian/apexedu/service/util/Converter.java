package com.damian.apexedu.service.util;

import com.damian.apexedu.dao.custom.HND_Credentials;
import com.damian.apexedu.dto.*;
import com.damian.apexedu.entity.*;

import java.util.ArrayList;

public class Converter {

    public static Admin_Details toAdminDetails(Admin_Details_DTO adminDetailsDto){
        return new Admin_Details(adminDetailsDto.getAdmin_id(),adminDetailsDto.getUsername(),adminDetailsDto.getPassword());
    }
    public static CCS_Announcements toCCSAnnouncements(CCS_Announcements_DTO ccsAnnouncementsDto){
        return new CCS_Announcements(ccsAnnouncementsDto.getDate(),ccsAnnouncementsDto.getLecturer_name(),ccsAnnouncementsDto.getDescription(),ccsAnnouncementsDto.getDue_date());
    }
    public static CCS_Fees toCCSFees(CCS_Fees_DTO ccsFeesDto){
        return new CCS_Fees(ccsFeesDto.getDate(),ccsFeesDto.getStudent_id(),ccsFeesDto.getStudent_name(),ccsFeesDto.getPayment_description(),ccsFeesDto.getAmount());
    }
    public static CCS_Attendance toCCSAttendance(CCS_Attendance_DTO cad) {
        return new CCS_Attendance(cad.getDate(), cad.getStudent_id(), cad.getStatus());
    }
    public static CCS_Student_Credentials toCCSStudentCredentials(CCS_Student_Credentials_DTO cscd) {
        return new CCS_Student_Credentials(cscd.getStudent_id(), cscd.getUsername(), cscd.getPassword());
    }
    public static CCS toCCS(CCS_DTO cd){
        return new CCS(cd.getProgram_id(), cd.getStudent_id(),cd.getStudent_name(),cd.getStudent_address(),cd.getStudent_email(),cd.getTelephone());
    }
    public static HND_Announcements toHNDAnnouncements(HND_Announcements_DTO hndAnnouncementsDto){
        return new HND_Announcements(hndAnnouncementsDto.getDate(),hndAnnouncementsDto.getLecturer_name(),hndAnnouncementsDto.getDescription(),hndAnnouncementsDto.getDue_date());
    }
    public static HND_Fees toHNDFees(HND_Fees_DTO hndFeesDto){
        return new HND_Fees(hndFeesDto.getDate(),hndFeesDto.getStudent_id(),hndFeesDto.getStudent_name(),hndFeesDto.getPayment_description(),hndFeesDto.getAmount());
    }
    public static HND_Attendance toHNDAttendance(HND_Attendance_DTO had) {
        return new HND_Attendance(had.getDate(), had.getStudent_id(), had.getStatus());
    }
    public static HND_Student_Credentials toHNDStudentCredentials(HND_Student_Credentials_DTO hscd) {
        return new HND_Student_Credentials(hscd.getStudent_id(), hscd.getUsername(), hscd.getPassword());
    }
    public static HND toHND(HND_DTO hd){
        return new HND(hd.getProgram_id(), hd.getStudent_id(),hd.getStudent_name(),hd.getStudent_address(),hd.getStudent_email(),hd.getTelephone());
    }
    public  static Lecturer_Details toLecturerDetails(Lecturer_Details_DTO ld){
        return new Lecturer_Details(ld.getLecturer_id(),ld.getProgram_id(),ld.getLecturer_name(),ld.getLecturer_address(),ld.getLecturer_email(),ld.getLecturer_telephone(),ld.getBasic_salary());
    }
    public  static Lecturer_Credentials toLecturerCredentials(Lecturer_Credentials_DTO lcd){
        return new Lecturer_Credentials(lcd.getLecturer_id(),lcd.getUsername(),lcd.getPassword());
    }
    public  static Program_Details toProgramDetails(Program_Details_DTO pd){
        return new Program_Details(pd.getProgram_id(),pd.getProgram_name(),pd.getProgram_fee());
    }
    public   static  UNDERGRAD toUnderGrad(UNDERGRAD_DTO undergradDto){
        return new UNDERGRAD(undergradDto.getProgram_id(),undergradDto.getStudent_id(),undergradDto.getStudent_name(),undergradDto.getStudent_address(),undergradDto.getStudent_email(),undergradDto.getTelephone());
    }
    public static  UNDERGRAD_Attendance toUnderGradAttendance(UNDERGRAD_Attendance_DTO uad) {
        return new UNDERGRAD_Attendance(uad.getDate(), uad.getStudent_id(), uad.getStatus());

    }
    public static UNDERGRAD_Announcements toUnderGradAnnouncements(UNDERGRAD_Announcements_DTO uad) {
        return new UNDERGRAD_Announcements(uad.getDate(), uad.getLecturer_name(), uad.getDescription(), uad.getDue_date());
    }
    public  static UNDERGRAD_Fees toUnderGradFees(UNDERGRAD_Fees_DTO ufd) {
        return new UNDERGRAD_Fees(ufd.getDate(), ufd.getStudent_id(), ufd.getStudent_name(), ufd.getPayment_description(), ufd.getAmount());
    }
    public  static UNDERGRAD_Student_Credentials toUnderGradStudentCredentials(UNDERGRAD_Student_Credentials_DTO uscd) {
        return new UNDERGRAD_Student_Credentials(uscd.getStudent_id(), uscd.getUsername(), uscd.getPassword());
    }
    public static CCS_DTO ccs_dto(CCS ccs){
        return new CCS_DTO(ccs.getProgram_id(),ccs.getStudent_id(),ccs.getStudent_name(),ccs.getStudent_address(),ccs.getStudent_email(),ccs.getTelephone());
    }
    public static CCS_Fees_DTO toCCS_fees_dto(CCS_Fees ccs_fees){
        return new CCS_Fees_DTO(ccs_fees.getDate(),ccs_fees.getStudent_id(),ccs_fees.getStudent_name(),ccs_fees.getPayment_description(),ccs_fees.getAmount());
    }
    public static CCS_Attendance_DTO toCCS_attendance_dto(CCS_Attendance ccs_attendance){
        return new CCS_Attendance_DTO(ccs_attendance.getDate(),ccs_attendance.getStudent_id(),ccs_attendance.getStatus());
    }
    public static CCS_Student_Credentials_DTO toCCS_student_credentials_dto(CCS_Student_Credentials ccs_student_credentials){
        return new CCS_Student_Credentials_DTO(ccs_student_credentials.getStudent_id(),ccs_student_credentials.getUsername(),ccs_student_credentials.getPassword());
    }
    public static CCS_Announcements_DTO toCCS_announcements_dto(CCS_Announcements ccs_announcements){
        return new CCS_Announcements_DTO(ccs_announcements.getDate(),ccs_announcements.getLecturer_name(),ccs_announcements.getDescription(),ccs_announcements.getDue_date());
    }
    public static HND_DTO toHND_dto(HND hnd){
        return new HND_DTO(hnd.getProgram_id(),hnd.getStudent_id(),hnd.getStudent_name(),hnd.getStudent_address(),hnd.getStudent_email(),hnd.getTelephone());
    }
    public static HND_Fees_DTO toHND_fees_dto(HND_Fees hnd_fees){
        return new HND_Fees_DTO(hnd_fees.getDate(),hnd_fees.getStudent_id(),hnd_fees.getStudent_name(),hnd_fees.getPayment_description(),hnd_fees.getAmount());
    }
    public static HND_Attendance_DTO toHND_attendance_dto(HND_Attendance hnd_attendance){
        return new HND_Attendance_DTO(hnd_attendance.getDate(),hnd_attendance.getStudent_id(),hnd_attendance.getStatus());
    }
    public static HND_Student_Credentials_DTO toHND_student_credentials_dto(HND_Student_Credentials hnd_student_credentials){
        return new HND_Student_Credentials_DTO(hnd_student_credentials.getStudent_id(),hnd_student_credentials.getUsername(),hnd_student_credentials.getPassword());
    }
    public static HND_Announcements_DTO toHND_announcements_dto(HND_Announcements hnd_announcements){
        return new HND_Announcements_DTO(hnd_announcements.getDate(),hnd_announcements.getLecturer_name(),hnd_announcements.getDescription(),hnd_announcements.getDue_date());
    }

    public static UNDERGRAD_DTO toUnderGradDto(UNDERGRAD undergrad){
        return new UNDERGRAD_DTO(undergrad.getProgram_id(),undergrad.getStudent_id(),undergrad.getStudent_name(),undergrad.getStudent_address(),undergrad.getStudent_email(),undergrad.getTelephone());
    }
    public static UNDERGRAD_Fees_DTO toUnderGradFeesDto(UNDERGRAD_Fees undergrad_fees){
        return new UNDERGRAD_Fees_DTO(undergrad_fees.getDate(),undergrad_fees.getStudent_id(),undergrad_fees.getStudent_name(),undergrad_fees.getPayment_description(),undergrad_fees.getAmount());
    }
    public static UNDERGRAD_Attendance_DTO toUnderGradAttendanceDto(UNDERGRAD_Attendance undergrad_attendance){
        return new UNDERGRAD_Attendance_DTO(undergrad_attendance.getDate(),undergrad_attendance.getStudent_id(),undergrad_attendance.getStatus());
    }
    public static UNDERGRAD_Student_Credentials_DTO toUnderGradStudentCredentialsDto(UNDERGRAD_Student_Credentials undergrad_student_credentials){
        return new UNDERGRAD_Student_Credentials_DTO(undergrad_student_credentials.getStudent_id(),undergrad_student_credentials.getUsername(),undergrad_student_credentials.getPassword());
    }
    public static UNDERGRAD_Announcements_DTO toUnderGradAnnouncementsDto(UNDERGRAD_Announcements undergrad_announcements){
        return new UNDERGRAD_Announcements_DTO(undergrad_announcements.getDate(),undergrad_announcements.getLecturer_name(),undergrad_announcements.getDescription(),undergrad_announcements.getDue_date());
    }

    public static CCS_DTO toCCSDto(CCS ccs){
        return new CCS_DTO(ccs.getProgram_id(),ccs.getStudent_id(),ccs.getStudent_name(),ccs.getStudent_address(),ccs.getStudent_email(),ccs.getTelephone());
    }

    public static Lecturer_Details_DTO toLecturerDetailsDto(Lecturer_Details lecturer_details){
        return new Lecturer_Details_DTO(lecturer_details.getLecturer_id(),lecturer_details.getProgram_id(),lecturer_details.getLecturer_name(),lecturer_details.getLecturer_address(),lecturer_details.getLecturer_email(),lecturer_details.getLecturer_telephone(),lecturer_details.getBasic_salary());
    }

    public static Lecturer_Credentials_DTO toLecturerCredentialsDto(Lecturer_Credentials lecturer_credentials){
        return new Lecturer_Credentials_DTO(lecturer_credentials.getLecturer_id(),lecturer_credentials.getUsername(),lecturer_credentials.getPassword());
    }

    public static ArrayList<CCS_Attendance_DTO> toCCS_attendance_dto_arraylist(ArrayList<CCS_Attendance> ccs_attendance_arraylist){
        ArrayList<CCS_Attendance_DTO> ccs_attendance_dto_arraylist = new ArrayList<>();
        for (CCS_Attendance ccs_attendance:ccs_attendance_arraylist) {
            ccs_attendance_dto_arraylist.add(toCCS_attendance_dto(ccs_attendance));
        }
        return ccs_attendance_dto_arraylist;
    }

    public static ArrayList<HND_Attendance_DTO> toHND_attendance_dto_arraylist(ArrayList<HND_Attendance> hnd_attendance_arraylist){
        ArrayList<HND_Attendance_DTO> hnd_attendance_dto_arraylist = new ArrayList<>();
        for (HND_Attendance hnd_attendance:hnd_attendance_arraylist) {
            hnd_attendance_dto_arraylist.add(toHND_attendance_dto(hnd_attendance));
        }
        return hnd_attendance_dto_arraylist;
    }

   public static ArrayList<UNDERGRAD_Attendance_DTO> undergradAttendanceDtos(ArrayList<UNDERGRAD_Attendance> uae){
        ArrayList<UNDERGRAD_Attendance_DTO >undergradAttendanceDtos = new ArrayList<>();
      for(UNDERGRAD_Attendance ua : uae){
          undergradAttendanceDtos.add(toUnderGradAttendanceDto(ua));
      }
        return undergradAttendanceDtos;
    }
    public static ArrayList<CCS_Fees_DTO> toCCS_fees_dto_arraylist(ArrayList<CCS_Fees> ccs_fees_arraylist){
        ArrayList<CCS_Fees_DTO> ccs_fees_dto_arraylist = new ArrayList<>();
        for (CCS_Fees ccs_fees:ccs_fees_arraylist) {
            ccs_fees_dto_arraylist.add(toCCS_fees_dto(ccs_fees));
        }
        return ccs_fees_dto_arraylist;
    }
    public static ArrayList<HND_Fees_DTO> toHND_fees_dto_arraylist(ArrayList<HND_Fees> hnd_fees_arraylist){
        ArrayList<HND_Fees_DTO> hnd_fees_dto_arraylist = new ArrayList<>();
        for (HND_Fees hnd_fees:hnd_fees_arraylist) {
            hnd_fees_dto_arraylist.add(toHND_fees_dto(hnd_fees));
        }
        return hnd_fees_dto_arraylist;
    }
    public static ArrayList<UNDERGRAD_Fees_DTO> toUndergrad_fees_dto_arraylist(ArrayList<UNDERGRAD_Fees> undergrad_fees_arraylist){
        ArrayList<UNDERGRAD_Fees_DTO> undergrad_fees_dto_arraylist = new ArrayList<>();
        for (UNDERGRAD_Fees undergrad_fees:undergrad_fees_arraylist) {
            undergrad_fees_dto_arraylist.add(toUnderGradFeesDto(undergrad_fees));
        }
        return undergrad_fees_dto_arraylist;
    }

    public static ArrayList<UNDERGRAD_Announcements_DTO> toUndergrad_announcements_dto_arraylist(ArrayList<UNDERGRAD_Announcements> undergrad_announcements_arraylist){
        ArrayList<UNDERGRAD_Announcements_DTO> undergrad_announcements_dto_arraylist = new ArrayList<>();
        for (UNDERGRAD_Announcements undergrad_announcements:undergrad_announcements_arraylist) {
            undergrad_announcements_dto_arraylist.add(toUnderGradAnnouncementsDto(undergrad_announcements));
        }
        return undergrad_announcements_dto_arraylist;
    }
    public static ArrayList<HND_Announcements_DTO> toHND_announcements_dto_arraylist(ArrayList<HND_Announcements> hnd_announcements_arraylist){
        ArrayList<HND_Announcements_DTO> hnd_announcements_dto_arraylist = new ArrayList<>();
        for (HND_Announcements hnd_announcements:hnd_announcements_arraylist) {
            hnd_announcements_dto_arraylist.add(toHND_announcements_dto(hnd_announcements));
        }
        return hnd_announcements_dto_arraylist;
    }
    public static ArrayList<CCS_Announcements_DTO> toCCS_announcements_dto_arraylist(ArrayList<CCS_Announcements> ccs_announcements_arraylist){
        ArrayList<CCS_Announcements_DTO> ccs_announcements_dto_arraylist = new ArrayList<>();
        for (CCS_Announcements ccs_announcements:ccs_announcements_arraylist) {
            ccs_announcements_dto_arraylist.add(toCCS_announcements_dto(ccs_announcements));
        }
        return ccs_announcements_dto_arraylist;
    }

    public static ArrayList<UNDERGRAD_DTO> toUndergrad_list_dto_arraylist(ArrayList<UNDERGRAD> undergrad_list_arraylist){
        ArrayList<UNDERGRAD_DTO> undergrad_list_dto_arraylist = new ArrayList<>();
        for (UNDERGRAD undergrad_list:undergrad_list_arraylist) {
            undergrad_list_dto_arraylist.add(toUnderGradDto(undergrad_list));
        }
        return undergrad_list_dto_arraylist;
    }
    public static ArrayList<HND_DTO> toHND_list_dto_arraylist(ArrayList<HND> hnd_list_arraylist){
        ArrayList<HND_DTO> hnd_list_dto_arraylist = new ArrayList<>();
        for (HND hnd_list:hnd_list_arraylist) {
            hnd_list_dto_arraylist.add(toHND_dto(hnd_list));
        }
        return hnd_list_dto_arraylist;
    }
    public static ArrayList<CCS_DTO> toCCS_list_dto_arraylist(ArrayList<CCS> ccs_list_arraylist){
        ArrayList<CCS_DTO> ccs_list_dto_arraylist = new ArrayList<>();
        for (CCS ccs_list:ccs_list_arraylist) {
            ccs_list_dto_arraylist.add(toCCSDto(ccs_list));
        }
        return ccs_list_dto_arraylist;
    }

}




