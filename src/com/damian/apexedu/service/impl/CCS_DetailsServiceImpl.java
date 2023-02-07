package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.CCS_CredentialsImpl_DAO;
import com.damian.apexedu.dao.impl.CCS_Feesimpl_DAO;
import com.damian.apexedu.dao.impl.CCS_Impl_DAO;
import com.damian.apexedu.db.DBConnection;
import com.damian.apexedu.dto.CCS_DTO;
import com.damian.apexedu.dto.CCS_Fees_DTO;
import com.damian.apexedu.dto.CCS_Student_Credentials_DTO;
import com.damian.apexedu.entity.CCS;
import com.damian.apexedu.entity.CCS_Fees;
import com.damian.apexedu.entity.CCS_Student_Credentials;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.custom.CCS_Credentials_Service;
import com.damian.apexedu.service.custom.CCS_Details_Service;
import com.damian.apexedu.service.custom.CCS_Fees_Service;
import com.damian.apexedu.service.util.Converter;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import javafx.scene.control.Alert;
import org.apache.hadoop.hdfs.web.resources.GetOpParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CCS_DetailsServiceImpl implements CCS_Details_Service {
    public CCS_Student_Credentials_DTO ccs_student_credentials_dto;
    public CCS_Fees_DTO ccs_fees_dto;

    public void setValues(CCS_Student_Credentials_DTO ccs_student_credentials_dto,CCS_Fees_DTO ccs_fees_dto){
        this.ccs_fees_dto = ccs_fees_dto;
        this.ccs_student_credentials_dto = ccs_student_credentials_dto;
    }


    @Override
    public boolean add(CCS_DTO ccs_dto) {
        CCS ccs = Converter.toCCS(ccs_dto);
        CCS_Impl_DAO  ccsImplDao = DAOFactory.getDAOObject(DAOTypes.CCS_IMPL);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.toString());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();

        }
        boolean b1 = ccsImplDao.add(ccs);
        if(b1){
            CCS_Credentials_Service ccsCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.CCS_CREDENTIALS);
            boolean b2 = ccsCredentialsService.add(this.ccs_student_credentials_dto);
            if(b2){
                CCS_FeesServiceImpl ccsFeesService=ServiceFactory.getServiceObject(ServiceTypes.CCS_FEES);
                boolean b3 = ccsFeesService.add(this.ccs_fees_dto);
                if(b3){
                    try {
                        DBConnection.getInstance().getConnection().commit();
                    } catch (SQLException | ClassNotFoundException e) {
                        Alert a = GetAlert.getInstance().getAlertReference();
                        a.setContentText(e.toString());
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.show();
                    }
                    return true;
                }else{
                    try {
                        DBConnection.getInstance().getConnection().rollback();
                    } catch (SQLException | ClassNotFoundException e) {
                        Alert a = GetAlert.getInstance().getAlertReference();
                        a.setContentText(e.toString());
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.show();
                    }
                }

            }
            else{
                try {
                    DBConnection.getInstance().getConnection().rollback();
                } catch (SQLException | ClassNotFoundException e) {
                    Alert a = GetAlert.getInstance().getAlertReference();
                    a.setContentText(e.toString());
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.show();
                }
            }
        }
        else{
            try {
                DBConnection.getInstance().getConnection().rollback();
            } catch (SQLException | ClassNotFoundException e) {
                Alert a = GetAlert.getInstance().getAlertReference();
                a.setContentText(e.toString());
                a.setAlertType(Alert.AlertType.ERROR);
                a.show();
            }
            finally {
                try {
                    DBConnection.getInstance().getConnection().setAutoCommit(true);
                } catch (SQLException | ClassNotFoundException e) {
                    Alert a = GetAlert.getInstance().getAlertReference();
                    a.setContentText(e.toString());
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.show();
                }
            }
        }
        return false;

    }

    @Override
    public boolean update(CCS_DTO ccs_dto, String id) {
        CCS ccs = Converter.toCCS(ccs_dto);
        CCS_Impl_DAO ccsImplDao = DAOFactory.getDAOObject(DAOTypes.CCS_IMPL);

        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.toString());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();

        }
        boolean b1 = ccsImplDao.update(ccs,id);
        if(b1){
            CCS_CredentialImplService ccsCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.CCS_CREDENTIALS);
            boolean b2 = ccsCredentialsService.update(this.ccs_student_credentials_dto, id);
            System.out.println(b2);
            if(b2){
                CCS_FeesServiceImpl ccsFeesService = ServiceFactory.getServiceObject(ServiceTypes.CCS_FEES);
                boolean b3 = ccsFeesService.update(this.ccs_fees_dto, id);
                if(b3){
                    try {
                        DBConnection.getInstance().getConnection().commit();
                    } catch (SQLException | ClassNotFoundException e) {
                        Alert a = GetAlert.getInstance().getAlertReference();
                        a.setContentText(e.toString());
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.show();
                    }finally {
                        try {
                            DBConnection.getInstance().getConnection().setAutoCommit(true);
                        } catch (SQLException | ClassNotFoundException e) {
                            Alert a = GetAlert.getInstance().getAlertReference();
                            a.setContentText(e.toString());
                            a.setAlertType(Alert.AlertType.ERROR);
                            a.show();
                        }
                    }
                    return true;

                }
                else{
                    try {
                        DBConnection.getInstance().getConnection().rollback();
                    } catch (SQLException | ClassNotFoundException e) {
                        Alert a = GetAlert.getInstance().getAlertReference();
                        a.setContentText(e.toString());
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.show();
                    }
                }

            }
            else{
                try {
                    DBConnection.getInstance().getConnection().rollback();
                } catch (SQLException | ClassNotFoundException e) {
                    Alert a = GetAlert.getInstance().getAlertReference();
                    a.setContentText(e.toString());
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.show();
                }
            }
        }else{
            try {
                DBConnection.getInstance().getConnection().rollback();
            } catch (SQLException | ClassNotFoundException e) {
                Alert a = GetAlert.getInstance().getAlertReference();
                a.setContentText(e.toString());
                a.setAlertType(Alert.AlertType.ERROR);
                a.show();
            }
        }
        return false;

    }

    @Override
    public boolean delete(String id) {
        CCS_Impl_DAO ccsImplDao = DAOFactory.getDAOObject(DAOTypes.CCS_IMPL);
        boolean delete = ccsImplDao.delete(id);
        return delete;

    }

    @Override
    public CCS_DTO search(String id) {
        CCS_Impl_DAO ccsImplDao =DAOFactory.getDAOObject(DAOTypes.CCS_IMPL);
        Optional<CCS> student = ccsImplDao.search(id);
        if(student.isPresent()){
            CCS_DTO ccsDto = Converter.toCCSDto((CCS) student.get());
            return ccsDto;
        }
        return null;
    }




    @Override
    public ArrayList<CCS_DTO> getAll() {
      CCS_Impl_DAO ccsImplDao= DAOFactory.getDAOObject(DAOTypes.CCS_IMPL);
        List<CCS> all = ccsImplDao.getAll();

        return Converter.toCCS_list_dto_arraylist((ArrayList<CCS>) all);
    }
}
