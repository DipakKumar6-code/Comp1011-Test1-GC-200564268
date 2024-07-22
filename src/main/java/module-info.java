module com.example.comp1011_midterm_gc200564268 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.comp1011_midterm_gc200564268 to javafx.fxml;
    exports com.example.comp1011_midterm_gc200564268;
}