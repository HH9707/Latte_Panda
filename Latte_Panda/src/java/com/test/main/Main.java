package com.test.main;

import com.test.main.util.DBUtil;

import java.sql.Connection;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        Connection conn = DBUtil.open();

    }
}
