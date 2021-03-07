package com.company.database;

import com.company.database.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL implements IDB {
    private IDB db;

    public PostgreSQL(IDB db) {
        this.db = db;
    }

    public PostgreSQL() {

    }

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/ass5";
        try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "1234567898");

            return con;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}
