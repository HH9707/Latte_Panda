package com.test.main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 오라클 클라우드 DB 접속 시 사용하는 클래스
 */
public class DBUtil {

    /**
     * DB연결
     * @return Connection
     */
    public static Connection open() {

        Connection conn = null;
        String url = "jdbc:oracle:thin:@db202201141741_high?TNS_ADMIN=/Users/jinn/Oracle/teampl/network/admin";
        String id = "admin";
        String pw = "Webproject4$";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, id, pw);

            setTimeZone(conn); //오라클 클라우드 시간 설정
            printTimeZone(conn); //timezone 변경 테스트

            return conn;

        } catch (Exception e) {

            System.out.println("DBUtil.open()");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 타임존 설정
     * @param conn
     */
    public static void setTimeZone(Connection conn) {

        try {

            String sql = "ALTER SESSION SET TIME_ZONE='Asia/Seoul'";
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);

        } catch (Exception e) {

            System.out.println("DBUtil.setTimeZone()");
            e.printStackTrace();
        }
    }

    /**
     * 타임존 콘솔창 출력
     * @param conn
     */
    public static void printTimeZone(Connection conn) {

        try {

            String sql = "SELECT SYSDATE, CURRENT_DATE, DBTIMEZONE, SESSIONTIMEZONE FROM DUAL";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while(rs.next()) {

                System.out.println("SYSDATE : " + rs.getString("SYSDATE"));
                System.out.println("CURRENT_DATE : " + rs.getString("CURRENT_DATE"));
                System.out.println("DBTIMEZONE : " + rs.getString("DBTIMEZONE"));
                System.out.println("SESSIONTIMEZONE : " + rs.getString("SESSIONTIMEZONE"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
