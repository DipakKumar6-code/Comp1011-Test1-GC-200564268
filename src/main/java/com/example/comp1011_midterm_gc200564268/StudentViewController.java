package com.example.comp1011_midterm_gc200564268;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StudentViewController {

    @FXML
    private ComboBox<String> areaCodeComboBox;
    @FXML
    private CheckBox ontarioCheckBox;
    @FXML
    private CheckBox honourRollCheckBox;
    @FXML
    private Label numOfStudentsLabel;
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student, Long> studentNumberColumn;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> telephoneNumberColumn;
    @FXML
    private TableColumn<Student, String> addressColumn;
    @FXML
    private TableColumn<Student, String> provinceColumn;
    @FXML
    private TableColumn<Student, Double> averageGradeColumn;
    @FXML
    private TableColumn<Student, String> majorColumn;

    private ObservableList<Student> students;
    private ObservableList<Student> filteredStudents;
    private Database database;
    private static final String phonePattern = "^\\+?1?\\s?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
    private static final Pattern phoneRegex = Pattern.compile(phonePattern);
    private static final Logger userLogger = Logger.getLogger(Database.class.getName());

    @FXML
    public void initialize() {
        database = new Database();
        students = FXCollections.observableArrayList(database.getAllStudents());
        filteredStudents = FXCollections.observableArrayList(students);
        studentTableView.setItems(filteredStudents);

        // Set up table columns
        studentNumberColumn.setCellValueFactory(cellData -> cellData.getValue().studentNumberProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        telephoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().telephoneNumberProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        provinceColumn.setCellValueFactory(cellData -> cellData.getValue().provinceProperty());
        averageGradeColumn.setCellValueFactory(cellData -> cellData.getValue().averageGradeProperty().asObject());
        majorColumn.setCellValueFactory(cellData -> cellData.getValue().majorProperty());

        // Populate areaCodeComboBox
        Set<String> areaCodes = students.stream()
                .map(student -> student.getTelephoneNumber().substring(0, 3))
                .collect(Collectors.toCollection(HashSet::new));
        List<String> sortedAreaCodes = areaCodes.stream().sorted().collect(Collectors.toList());
        areaCodeComboBox.getItems().addAll(sortedAreaCodes);
        areaCodeComboBox.getItems().add("All");

        // Initialize listeners
        areaCodeComboBox.setOnAction(event -> applyFilter());
        ontarioCheckBox.setOnAction(event -> applyFilter());
        honourRollCheckBox.setOnAction(event -> applyFilter());

        // Update student count label
        updateStudentCount();
    }

    private void applyFilter() {
        filteredStudents.setAll(students);

        if (ontarioCheckBox.isSelected()) {
            filteredStudents.removeIf(student -> !student.getProvince().equals("ON"));
        }

        if (honourRollCheckBox.isSelected()) {
            filteredStudents.removeIf(student -> student.getAverageGrade() < 80);
        }

        String selectedAreaCode = areaCodeComboBox.getSelectionModel().getSelectedItem();
        if (selectedAreaCode != null && !selectedAreaCode.equals("All")) {
            filteredStudents.removeIf(student -> !student.getTelephoneNumber().startsWith(selectedAreaCode));
        }

        updateStudentCount();
    }

    private void updateStudentCount() {
        numOfStudentsLabel.setText("Number of Students: " + filteredStudents.size());
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneRegex.matcher(phoneNumber).matches();
    }

    public void submitStudentForm(String studentNumber, String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            database.saveStudentData(studentNumber, phoneNumber);
        } else {
            userLogger.warning("Invalid phone number: " + phoneNumber);
        }
    }
}
