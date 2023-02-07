package com.damian.apexedu.util;

import com.damian.apexedu.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformCRUD {
    public static <T> T execute(String sql, Object...args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for(int i=0;i< args.length;i++){
            pstm.setObject((i+1),args[i]);
        }
        if(sql.startsWith("SELECT")|sql.startsWith("select")){
            ResultSet RS = pstm.executeQuery();
            return (T) RS;
        }
        else{

            int i = pstm.executeUpdate();
            if(i>0){
                return (T) (Boolean) true;
            }

            return (T) (Boolean) false;
        }
    }
}
