//package com.example.comp1011_midterm_gc;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//
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
//    private void applyFilter()  {
//    }
//
//    @FXML
//    void initialize() {
//        areaCodeComboBox.getItems().add("All");
//    }
//}

package com.example.comp1011_midterm_gc;

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

    @FXML
    private void applyFilter() {

    }

    @FXML
    void initialize() {
        areaCodeComboBox.getItems().add("All");

        try {
            List<Student> students = Database.getStudents();

            // Extract area codes and add them to the ComboBox
            Set<String> areaCodes = students.stream()
                    .map(student -> student.getTelephone().substring(0, 3)) // Get the area code
                    .collect(Collectors.toCollection(TreeSet::new)); // Collect into a TreeSet to sort and remove duplicates

            areaCodeComboBox.getItems().addAll(areaCodes);

            tableView.getItems().addAll(students);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
