package com.damian.apexedu.control;

import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.CCS_Attendance_DTO;
import com.damian.apexedu.dto.HND_Attendance_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Attendance_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.CCS_AttendanceServiceImpl;
import com.damian.apexedu.service.impl.HND_AttendanceServiceImpl;
import com.damian.apexedu.service.impl.UnderGrad_AttendanceServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AttendanceViewerController implements Initializable {

    public Label l1;

    public ImageView i2;
    public TableView tableView;
    public TableColumn c1;
    public TableColumn c2;
    public TableColumn c3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodeArray = {l1,i2,tableView};
        for(Node n : nodeArray){
            Animator.setJackInTheBox(n);
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("date"));
        c2.setCellValueFactory(new PropertyValueFactory<>("status"));


        if(StudentLoginController.status.equals("CCS.")){
            CCS_AttendanceServiceImpl ccsAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.CCS_ATTENDANCE);
            ArrayList<CCS_Attendance_DTO> ccs = ccsAttendanceService.getAll();
            ObservableList<CCS_Attendance_DTO> obList = FXCollections.observableArrayList();
            for(CCS_Attendance_DTO c : ccs){
                obList.add(c);
            }
            tableView.setItems(obList);
        }
        if(StudentLoginController.status.equals("HND.")){
          HND_AttendanceServiceImpl hndAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.HND_ATTENDANCE);
            ArrayList<HND_Attendance_DTO> hnd = hndAttendanceService.getAll();
            ObservableList<HND_Attendance_DTO> obList = FXCollections.observableArrayList();
            for(HND_Attendance_DTO h : hnd){
                obList.add(h);
            }
            tableView.setItems(obList);
        }
        if(StudentLoginController.status.equals("UNDERGRAD.")){
            UnderGrad_AttendanceServiceImpl underGradAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_ATTENDANCE);
            ArrayList<UNDERGRAD_Attendance_DTO> ud = underGradAttendanceService.getAll();

            ObservableList<UNDERGRAD_Attendance_DTO> obList = FXCollections.observableArrayList();
            for(UNDERGRAD_Attendance_DTO u : ud){
                obList.add(u);
            }
            tableView.setItems(obList);
        }

    }

}
