/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class DBconnect {
   private static String DB_URL = "jdbc:sqlserver://LAPTOP-H7V6GC9S;"
            + "databaseName=QLCHXM";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "cuongbom"; 
    public static Connection conn = null;
    
     public DBconnect() {
    }
    
    public static Connection getConnection(){
        try {            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Lỗi thiếu thư viện kết nối");
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối CSDL!");
        }
       return conn;
    }
    
}
