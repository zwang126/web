/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zan_Wang
 */
public class userBeanAction {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    public ArrayList<userBean> getUserByPage(int pageSize, int pageNow) {
        int rowCount = 0;
        int pageCount = 0;
        ArrayList<userBean> al = new ArrayList<userBean>();
        userBean ub = new userBean();
        getConn g = new getConn();
        try {
            conn = g.getConnection();
            
            st = conn.prepareStatement("SELECT count(*) FROM users");
            rs = st.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
            
            if (rowCount % pageSize == 0) {
                pageCount = rowCount / pageSize;
            } else {
                pageCount = rowCount / pageSize + 1;
            }
            
            st = conn.prepareStatement("SELECT * FROM users LIMIT " + pageSize * (pageNow - 1) + "," + pageSize * (pageNow) + "");
            
            rs = st.executeQuery();
            while(rs.next()){
                ub.setUserId(rs.getInt(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setEmail(rs.getString(4));
                ub.setGrade(rs.getInt(5));
                al.add(ub);
            }
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getStackTrace());
        } catch (Exception ex){
            ex.printStackTrace();
        } finally{
            close();
        }
        return al;
    }

    public boolean verify_user(String u, String p) {
        boolean res = false;
        try {
            getConn g = new getConn();
            conn = g.getConnection();
            st = conn.prepareStatement("SELECT password from users where username ='" + u + "' LIMIT 1");
            rs = st.executeQuery();
            if (rs.next()) {
                String db_pass = rs.getString(1);
                if (db_pass.equals(p)) {
                    res = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(userBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return res;
    }

    private void close() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
            if (st != null) {
                st.close();
                st = null;

            }
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getStackTrace());
        }

    }
}
