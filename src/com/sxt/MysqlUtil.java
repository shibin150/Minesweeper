package com.sxt;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MysqlUtil extends JFrame {

    // JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/w_w84_xinxy";

    // ���ݿ���û��������룬��Ҫ�����Լ�������
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
            // ע�� JDBC ����
            Class.forName("com.mysql.jdbc.Driver");

            // ������
            ta.append("�������ݿ�...\r\n");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // ִ�в�ѯ
            ta.append(" ʵ����Statement��...\r\n");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM admin_tb";
            ResultSet rs = stmt.executeQuery(sql);

            // չ����������ݿ�
            while(rs.next()){
                // ͨ���ֶμ���
                int id  = rs.getInt("id");
                String name = rs.getString("adminname");

                // �������
                ta.append("ID: " + id+"\r\n");
                ta.append(", ����Ա: " + name+"\r\n");

            }
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
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
