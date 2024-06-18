package com.example.comp1011_midterm_gc;

import com.example.comp1011_midterm_gc.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String dbUrl = "jdbc:mysql://database-2.czu8sy6gatbb.us-east-2.rds.amazonaws.com/College";
    private static final String dbUserName = "admin";
    private static final String password = "DipakKumar34";

    public static List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUserName, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students LIMIT 1000")) {

            while (resultSet.next()) {
                int studentNumber = resultSet.getInt("student_number");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String telephone = resultSet.getString("telephone");
                String address = resultSet.getString("address");
                String province = resultSet.getString("province");
                int averageGrade = resultSet.getInt("average_grade");
                String major = resultSet.getString("major");

                students.add(new Student(studentNumber, firstName, lastName, telephone, address, province, averageGrade, major));
            }
        }
        return students;
    }
}
