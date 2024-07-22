
package com.example.comp1011_midterm_gc200564268;

import javafx.beans.property.*;

import java.util.List;

public class Student {

    private final LongProperty studentNumber;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty telephoneNumber;
    private final StringProperty address;
    private final StringProperty province;
    private final DoubleProperty averageGrade;
    private final StringProperty major;

    private static final String phonePattern = "^\\+?1?\\s?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";

    public Student(long studentNumber, String firstName, String lastName, String telephoneNumber,
                   String address, String province, double averageGrade, String major) {

        validate(studentNumber, firstName, lastName, telephoneNumber, address, province, averageGrade, major);

        this.studentNumber = new SimpleLongProperty(studentNumber);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.telephoneNumber = new SimpleStringProperty(telephoneNumber);
        this.address = new SimpleStringProperty(address);
        this.province = new SimpleStringProperty(province);
        this.averageGrade = new SimpleDoubleProperty(averageGrade);
        this.major = new SimpleStringProperty(major);
    }

    private void validate(long studentNumber, String firstName, String lastName, String telephoneNumber,
                          String address, String province, double averageGrade, String major) {
        if (studentNumber <= 200034000)
            throw new IllegalArgumentException("Student number must be greater than 200034000");
        if (firstName == null || firstName.length() <= 1)
            throw new IllegalArgumentException("First name must be more than 1 character");
        if (lastName == null || lastName.length() <= 1)
            throw new IllegalArgumentException("Last name must be more than 1 character");
        if (telephoneNumber == null || !telephoneNumber.matches(phonePattern))
            throw new IllegalArgumentException("Telephone number should match the North American dialing plan");
        if (address == null || address.length() <= 6)
            throw new IllegalArgumentException("Address must be more than 6 characters");
        if (province == null || !List.of("AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT").contains(province))
            throw new IllegalArgumentException("Province must be in the list of valid provinces");
        if (averageGrade < 0 || averageGrade > 100)
            throw new IllegalArgumentException("Average grade must be in the range of 0-100 (inclusive)");
        if (major == null || major.length() <= 5)
            throw new IllegalArgumentException("Major must be greater than 5 characters");
    }

    public long getStudentNumber() {
        return studentNumber.get();
    }

    public LongProperty studentNumberProperty() {
        return studentNumber;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber.get();
    }

    public StringProperty telephoneNumberProperty() {
        return telephoneNumber;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getProvince() {
        return province.get();
    }

    public StringProperty provinceProperty() {
        return province;
    }

    public double getAverageGrade() {
        return averageGrade.get();
    }

    public DoubleProperty averageGradeProperty() {
        return averageGrade;
    }

    public String getMajor() {
        return major.get();
    }

    public StringProperty majorProperty() {
        return major;
    }
}

