package com.sxt;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MysqlUtil extends JFrame {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/w_w84_xinxy";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "kwlloveh";

    private JTextArea ta;

    public MysqlUtil() {
        // TODO Auto-generated constructor stub
        fm();
        connect();
    }

    public void fm() {
        this.setTitle("MySQL");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        ta=new JTextArea(30,50);
        this.add(ta);
    }
    public void connect() {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            ta.append("连接数据库...\r\n");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            ta.append(" 实例化Statement对...\r\n");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM admin_tb";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("adminname");

                // 输出数据
                ta.append("ID: " + id+"\r\n");
                ta.append(", 管理员: " + name+"\r\n");

            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");

    }
    public static void main(String[] args) {
        MysqlUtil mysql=new MysqlUtil();
        mysql.setVisible(true);
    }
}
