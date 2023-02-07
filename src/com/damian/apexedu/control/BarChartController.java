package com.damian.apexedu.control;

import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.CCS_Fees_DTO;
import com.damian.apexedu.dto.HND_Fees_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Fees_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.CCS_FeesServiceImpl;
import com.damian.apexedu.service.impl.HND_FeesServiceImpl;
import com.damian.apexedu.service.impl.UnderGrad_FeesServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BarChartController implements Initializable {
    public Label label;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    public double cf;
    public double hf;
    public double uf;
    public BarChart barChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series set1 = new XYChart.Series<>();

        CCS_FeesServiceImpl ccsFeesService = ServiceFactory.getServiceObject(ServiceTypes.CCS_FEES);
        ArrayList<CCS_Fees_DTO>ccsFeesList = ccsFeesService.getAllFees();
        for(CCS_Fees_DTO cssfees : ccsFeesList){
           cf+= cssfees.getAmount();
        }
        HND_FeesServiceImpl hndFeesService = ServiceFactory.getServiceObject(ServiceTypes.HND_FEES);
        ArrayList<HND_Fees_DTO> hndFees = hndFeesService.getAll();
        for(HND_Fees_DTO hndfees : hndFees){
            hf+= hndfees.getAmount();
        }
        UnderGrad_FeesServiceImpl undergradFeesService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_FEES);
        ArrayList<UNDERGRAD_Fees_DTO> undergradfees= undergradFeesService.getAll();
        for(UNDERGRAD_Fees_DTO undergradfees1 : undergradfees){
            uf+= undergradfees1.getAmount();
        }
        set1.getData().add(new XYChart.Data("CCS.",cf));
        set1.getData().add(new XYChart.Data("HND.",hf));
        set1.getData().add(new XYChart.Data("UNDERGRAD.",uf));

        barChart.getData().addAll(set1);

        Animator.setShake(barChart);

    }
}
