package com.example.comp1011_midterm_gc200564268;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Database {

    private static final Logger LOGGER = Logger.getLogger(Database.class.getName());
    private static final String dbUrl = "jdbc:mysql://database-2.czu8sy6gatbb.us-east-2.rds.amazonaws.com/Georgian";
    private static final String dbUserName = "admin";
    private static final String password = "DipakKumar34";
    private static final String PHONE_PATTERN = "^\\+?1?\\s?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
    private static final Pattern PHONE_REGEX = Pattern.compile(PHONE_PATTERN);

//    public List<Student> getAllStudents() {
//        List<Student> students = new ArrayList<>();
//        String query = "SELECT studentNum, firstName, lastName, telephone, homeAddress, province, avgGrade, major FROM students";
//
//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUserName, password);
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//
//            while (resultSet.next()) {
//                try {
//                    long studentNumber = resultSet.getLong("studentNum");
//                    String firstName = resultSet.getString("firstName");
//                    String lastName = resultSet.getString("lastName");
//                    String telephoneNumber = resultSet.getString("telephone");
//                    String address = resultSet.getString("homeAddress");
//                    String province = resultSet.getString("province");
//                    double averageGrade = resultSet.getDouble("avgGrade");
//                    String major = resultSet.getString("major");
//
//                    if (!isValidPhoneNumber(telephoneNumber)) {
//                        LOGGER.log(Level.WARNING, "Invalid telephone number: " + telephoneNumber);
//                        continue; // Skip this student if the phone number is invalid
//                    }
//
//                    Student student = new Student(studentNumber, firstName, lastName, telephoneNumber, address, province, averageGrade, major);
//                    students.add(student);
//                } catch (IllegalArgumentException e) {
//                    // Log the invalid student data and continue
//                    LOGGER.log(Level.WARNING, "Invalid student data: " + e.getMessage());
//                }
//            }
//
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Database connection error", e);
//        }
//
//        return students;
//    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT studentNum, firstName, lastName, telephone, homeAddress, province, avgGrade, major FROM students";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUserName, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                try {
                    long studentNumber = resultSet.getLong("studentNum");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String telephoneNumber = resultSet.getString("telephone");
                    String address = resultSet.getString("homeAddress");
                    String province = resultSet.getString("province");
                    double averageGrade = resultSet.getDouble("avgGrade");
                    String major = resultSet.getString("major");

                    if (!isValidPhoneNumber(telephoneNumber)) {
                        LOGGER.log(Level.WARNING, "Invalid telephone number: " + telephoneNumber);
                        continue; // Skip this student if the phone number is invalid
                    }

                    Student student = new Student(studentNumber, firstName, lastName, telephoneNumber, address, province, averageGrade, major);
                    students.add(student);
                } catch (IllegalArgumentException e) {
                    // Log the invalid student data and continue
                    LOGGER.log(Level.WARNING, "Invalid student data: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database connection error", e);
        }

        return students;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return PHONE_REGEX.matcher(phoneNumber).matches();
    }

    public void saveStudentData(String studentNumber, String phoneNumber) {
        String query = "UPDATE students SET telephone = ? WHERE studentNum = ?";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUserName, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, phoneNumber);
            statement.setString(2, studentNumber);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "Student data updated successfully");
            } else {
                LOGGER.log(Level.WARNING, "No student found with studentNumber: " + studentNumber);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating student data", e);
        }
    }
}
