////
////
////package com.example.comp1011_midterm_gc;
////
////import javafx.fxml.FXML;
////import javafx.scene.control.*;
////
////import java.sql.SQLException;
////import java.util.List;
////import java.util.Set;
////import java.util.TreeSet;
////import java.util.stream.Collectors;
////
////public class StudentViewController {
////
////    @FXML
////    private TableView<Student> tableView;
////
////    @FXML
////    private TableColumn<Student, Integer> studentNumCol;
////
////    @FXML
////    private TableColumn<Student, String> firstNameCol;
////
////    @FXML
////    private TableColumn<Student, String> lastNameCol;
////
////    @FXML
////    private TableColumn<Student, String> telephoneCol;
////
////    @FXML
////    private TableColumn<Student, String> addressCol;
////
////    @FXML
////    private TableColumn<Student, String> provinceCol;
////
////    @FXML
////    private TableColumn<Student, Integer> avgGradeCol;
////
////    @FXML
////    private TableColumn<Student, String> majorCol;
////
////    @FXML
////    private CheckBox ontarioCheckBox;
////
////    @FXML
////    private Label numOfStudentsLabel;
////
////    @FXML
////    private CheckBox honourRollCheckBox;
////
////    @FXML
////    private ComboBox<String> areaCodeComboBox;
////
////    @FXML
////    private void applyFilter() {
////
////    }
////
////    @FXML
////    void initialize() {
////        areaCodeComboBox.getItems().add("All");
////
////        try {
////            List<Student> students = Database.getStudents();
////
////            // Extract area codes and add them to the ComboBox
////            Set<String> areaCodes = students.stream()
////                    .map(student -> student.getTelephone().substring(0, 3)) // Get the area code
////                    .collect(Collectors.toCollection(TreeSet::new)); // Collect into a TreeSet to sort and remove duplicates
////
////            areaCodeComboBox.getItems().addAll(areaCodes);
////
////            tableView.getItems().addAll(students);
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
////}
//
//package com.example.comp1011_midterm_gc;
//
//import javafx.collections.ListChangeListener;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Set;
//import java.util.TreeSet;
//import java.util.stream.Collectors;
//
//public class StudentViewController {
//
//    @FXML
//    private TableView<Student> tableView;
//
//    @FXML
//    private TableColumn<Student, Integer> studentNumCol;
//
//    @FXML
//    private TableColumn<Student, String> firstNameCol;
//
//    @FXML
//    private TableColumn<Student, String> lastNameCol;
//
//    @FXML
//    private TableColumn<Student, String> telephoneCol;
//
//    @FXML
//    private TableColumn<Student, String> addressCol;
//
//    @FXML
//    private TableColumn<Student, String> provinceCol;
//
//    @FXML
//    private TableColumn<Student, Integer> avgGradeCol;
//
//    @FXML
//    private TableColumn<Student, String> majorCol;
//
//    @FXML
//    private CheckBox ontarioCheckBox;
//
//    @FXML
//    private Label numOfStudentsLabel;
//
//    @FXML
//    private CheckBox honourRollCheckBox;
//
//    @FXML
//    private ComboBox<String> areaCodeComboBox;
//
//    @FXML
//    private void applyFilter() {
//    }
//
//    @FXML
//    void initialize() {
//        areaCodeComboBox.getItems().add("All");
//
//        try {
//            List<Student> students = Database.getStudents();
//
//            // Extract area codes and add them to the ComboBox
//            Set<String> areaCodes = students.stream()
//                    .map(student -> student.getTelephone().substring(0, 3)) // Get the area code
//                    .collect(Collectors.toCollection(TreeSet::new)); // Collect into a TreeSet to sort and remove duplicates
//
//            areaCodeComboBox.getItems().addAll(areaCodes);
//
//            // Populate the TableView
//            tableView.getItems().addAll(students);
//
//            // Update the number of students label
//            updateStudentsLabel();
//
//            // Add a listener to update the label whenever the items change
//            tableView.getItems().addListener((ListChangeListener<Student>) change -> updateStudentsLabel());
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateStudentsLabel() {
//        int numOfStudents = tableView.getItems().size();
//        numOfStudentsLabel.setText("Number of Students: " + numOfStudents);
//    }
//}

package com.example.comp1011_midterm_gc;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class StudentViewController {

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Integer> studentNumCol;

    @FXML
    private TableColumn<Student, String> firstNameCol;

    @FXML
    private TableColumn<Student, String> lastNameCol;

    @FXML
    private TableColumn<Student, String> telephoneCol;

    @FXML
    private TableColumn<Student, String> addressCol;

    @FXML
    private TableColumn<Student, String> provinceCol;

    @FXML
    private TableColumn<Student, Integer> avgGradeCol;

    @FXML
    private TableColumn<Student, String> majorCol;

    @FXML
    private CheckBox ontarioCheckBox;

    @FXML
    private Label numOfStudentsLabel;

    @FXML
    private CheckBox honourRollCheckBox;

    @FXML
    private ComboBox<String> areaCodeComboBox;

    private ObservableList<Student> allStudents;

    @FXML
    void initialize() {
        areaCodeComboBox.getItems().add("All");

        try {
            List<Student> students = Database.getStudents();
            allStudents = FXCollections.observableArrayList(students);

            // Extract area codes and add them to the ComboBox
            Set<String> areaCodes = students.stream()
                    .map(student -> student.getTelephone().substring(0, 3)) // Get the area code
                    .collect(Collectors.toCollection(TreeSet::new)); // Collect into a TreeSet to sort and remove duplicates

            areaCodeComboBox.getItems().addAll(areaCodes);

            // Populate the TableView
            tableView.getItems().addAll(students);

            // Update the number of students label
            updateNumOfStudentsLabel();

            // Add listeners to update the label whenever the items change
            tableView.getItems().addListener((ListChangeListener<Student>) change -> updateNumOfStudentsLabel());

            // Add listeners to filters
            ontarioCheckBox.setOnAction(event -> applyFilter());
            honourRollCheckBox.setOnAction(event -> applyFilter());
            areaCodeComboBox.setOnAction(event -> applyFilter());

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error (show an alert, for example)
        }
    }

    @FXML
    private void applyFilter() {
        List<Student> filteredStudents = allStudents.stream()
                .filter(this::filterByOntario)
                .filter(this::filterByHonourRoll)
                .filter(this::filterByAreaCode)
                .collect(Collectors.toList());

        tableView.getItems().setAll(filteredStudents);
    }

    private boolean filterByOntario(Student student) {
        if (ontarioCheckBox.isSelected()) {
            return "ON".equals(student.getProvince());
        }
        return true;
    }

    private boolean filterByHonourRoll(Student student) {
        if (honourRollCheckBox.isSelected()) {
            return student.getAvgGrade() >= 80; // Assuming 80 or above is honour roll
        }
        return true;
    }

    private boolean filterByAreaCode(Student student) {
        String selectedAreaCode = areaCodeComboBox.getValue();
        if (selectedAreaCode != null && !"All".equals(selectedAreaCode)) {
            return student.getTelephone().startsWith(selectedAreaCode);
        }
        return true;
    }

    private void updateNumOfStudentsLabel() {
        int numOfStudents = tableView.getItems().size();
        numOfStudentsLabel.setText("Number of Students: " + numOfStudents);
    }
}
