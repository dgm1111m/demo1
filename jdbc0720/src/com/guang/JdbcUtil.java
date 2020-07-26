package com.guang;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    //读取文件，只需要读取一次即可拿这些值
    static {
        try {
            //创建Properties集合类
            Properties pro = new Properties();
            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            String path = res.getPath();
            System.out.println(path);
            //加载文件

            pro.load(new FileReader(path));
            //获取数值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            Class.forName(driver);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    //释放资源
    public static void close(ResultSet rs, Statement stmt, Connection conn){

            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

    }

}


