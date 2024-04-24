package DAO;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DbHelper {
    private static DbHelper instance = null;
    private Connection cn = null;

    private DbHelper() {

        try {


            String configFile = "jdbc:sqlserver://\\\\SQLEXPRESS:1433;databasename=MNHP;user=sa;password=123;trustServerCertificate=true";


            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = configFile;
            cn = DriverManager.getConnection(dbURL);
            System.out.println("Connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DbHelper getInstance() {
        if (instance == null) {
            instance = new DbHelper();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.cn;
    }

    public boolean DangNhap(String usn, String psw) {
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT * FROM TaiKhoan where idCBNV = ? and MatKhau = ?";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            stmt.setString(1, usn);
            stmt.setString(2, psw);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                System.out.println(rs.getString(1));
                return true;
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
