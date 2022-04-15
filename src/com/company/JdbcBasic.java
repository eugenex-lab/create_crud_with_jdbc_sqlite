package com.company;

import java.sql.*;

public class JdbcBasic {


    public static final String DB = "emp.db";
    
    public static final String CONNECTION = "jdbc:sqlite:C:\\Users\\mac\\IdeaProjects\\sqlite\\src\\com\\company\\new.db" + DB;


    public static final String TABLE_EMPLOYEE = "employees";

    public static final String COLUMN_NAME = "employee_name";
    public static final String COLUMN_OKR_SCORE = "employee_okr_score";
    public static final String COLUMN_ROLE = "employee_role";
    public static final String COLUMN_DEPT = "employee_department";


    public static void main(String[] args) {

        try {
            
            Connection conn = DriverManager.getConnection(CONNECTION);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EMPLOYEE +
                    " (" + COLUMN_NAME + " text, " +
                    COLUMN_OKR_SCORE + " integer, " +
                    COLUMN_ROLE + " text" +
                    COLUMN_DEPT + " text" +
                    ")");

            

            // insert some employees into the database
            insertEmployee(statement, "John", 5, "CEO");
            insertEmployee(statement, "Jane", 4, "CFO");
            insertEmployee(statement, "Joe", 3, "COO");
            insertEmployee(statement, "Jack", 2, "CTO");
            insertEmployee(statement, "Jill", 1, "CMO");
            insertEmployee(statement, "Jim", 0, "CIO");

            // update statment
            statement.execute("UPDATE " + TABLE_EMPLOYEE + " SET " + COLUMN_OKR_SCORE + " = 1 WHERE " + COLUMN_NAME + " = 'John'");


            // delete statement
            statement.execute("DELETE FROM " + TABLE_EMPLOYEE + " WHERE " + COLUMN_NAME + " = 'Jane'");


            //Print out the table
            ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_EMPLOYEE);
            while (rs.next()) {
                System.out.println(rs.getString(COLUMN_NAME) + " " + rs.getInt(COLUMN_OKR_SCORE) + " " + rs.getString(COLUMN_ROLE) );

            }
            statement.close();
            conn.close();

//            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\databases\\testjava.db");
//            Class.forName("org.sql.JDBC");

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertEmployee(Statement statement, String employee_name, int employee_okr_score, String employee_role) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_EMPLOYEE + " VALUES ('" + employee_name + "', " + employee_okr_score + ", '" + employee_role + "')");
    }

}
