package com.damian.apexedu.control;

import com.damian.apexedu.db.DBConnection;
import com.damian.apexedu.dto.CCS_Fees_DTO;
import com.damian.apexedu.dto.HND_Fees_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Fees_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.CCS_FeesServiceImpl;
import com.damian.apexedu.service.impl.HND_FeesServiceImpl;
import com.damian.apexedu.service.impl.UnderGrad_FeesServiceImpl;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.Navigator;
import com.damian.apexedu.util.Routes;
import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PieChartController implements Initializable {
    public PieChart pieChart;
    public double ccs_fees;
    public double hnd_fees;
    public double undergrad_fees;
    public ImageView image;
    public JFXButton ccsReport;
    public JFXButton hndReport;
    public JFXButton undergradReport;
    public JFXButton barChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CCS_FeesServiceImpl ccsFeesService=ServiceFactory.getServiceObject(ServiceTypes.CCS_FEES);
        ArrayList<CCS_Fees_DTO> ccsFees = ccsFeesService.getAllFees();

        HND_FeesServiceImpl hndFeesService = ServiceFactory.getServiceObject(ServiceTypes.HND_FEES);
        ArrayList<HND_Fees_DTO> hndFees = hndFeesService.getAll();

        UnderGrad_FeesServiceImpl underGradFeesService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_FEES);
        ArrayList<UNDERGRAD_Fees_DTO> UnderGradFees = underGradFeesService.getAll();

        for(CCS_Fees_DTO cds : ccsFees){
            ccs_fees+=cds.getAmount();
        }
        for(HND_Fees_DTO hds : hndFees){
            hnd_fees+=hds.getAmount();

        }
        for(UNDERGRAD_Fees_DTO uds : UnderGradFees){
            undergrad_fees+=uds.getAmount();
        }
        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList(new PieChart.Data("CCS.",ccs_fees),new PieChart.Data("HND.",hnd_fees),new PieChart.Data("UNDERGRADS.",undergrad_fees));
        pieChart.setData(observableList);
    }

    public void clicked(MouseEvent mouseEvent) {
        ObservableList<PieChart.Data> data = pieChart.getData();

        for(PieChart.Data pd : data){
            Bounds b1 = pd.getNode().getBoundsInLocal();
            double newX = (b1.getWidth()) / 2.0 + b1.getMinX();

            System.out.println(b1.getMinX());
            double newY = (b1.getHeight()) / 2.0 + b1.getMinY();

            pd.getNode().setTranslateX(0);
            pd.getNode().setTranslateY(0);

            TranslateTransition tt = new TranslateTransition(
                    Duration.millis(1500), pd.getNode());

            tt.setByX(newX);
            tt.setByY(newY);

            tt.setAutoReverse(true);
            tt.setCycleCount(2);
            tt.play();
        }
    }

    public void ccsReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream inputStream= this.getClass().getResourceAsStream
                    ("/com/damian/apexedu/reports/CCS_STUDENT_FEES.jrxml");

            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert("An error occurred while generating report!","ERROR.", Alert.AlertType.ERROR);
        }
    }

    public void hndReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream inputStream= this.getClass().getResourceAsStream
                    ("/com/damian/apexedu/reports/HND_STUDENT_FEES.jrxml");

            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert("An error occurred while generating report!","ERROR.", Alert.AlertType.ERROR);
        }
    }

    public void undergradReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream inputStream= this.getClass().getResourceAsStream
                    ("/com/damian/apexedu/reports/UNDERGRAD_STUDENT_FEES.jrxml");

            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert("An error occurred while generating report!","ERROR.", Alert.AlertType.ERROR);
        }
    }


    public void barChartOnAction(ActionEvent actionEvent) throws IOException {
       Navigator.navigate(AdminDashBoardController.staticPane, Routes.BARCHART);
    }


}
