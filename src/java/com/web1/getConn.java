/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zan_Wang
 */
public class getConn {

    private static final String user_name = "root";
    private static final String pass_word = "1111";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/user";

    public Connection getConnection() {
        Connection conn = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(CONN_STRING, user_name, pass_word);
        } catch (SQLException ex) {
            Logger.getLogger(getConn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getConn.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return conn;
    }
}
