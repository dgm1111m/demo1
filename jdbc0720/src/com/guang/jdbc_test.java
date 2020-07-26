package com.guang;


import java.sql.*;
import java.util.Scanner;
import com.guang.JdbcUtil;

public class jdbc_test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String name = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();
        boolean loginflag = new jdbc_test().login(name, password);
        if (loginflag) {
            System.out.println("登陆成功");
        } else {
            System.out.println("密码错误");
        }
        System.out.println("11");


    }

    //登录方法
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            //定义sql
            String sql = "select *from user where name = '" + username + "' and password = '" + password + "'";
            //获取执行sql对象
            stmt = conn.createStatement();
            rs=stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(rs, stmt, conn);
        }

        return false;
    }
}
