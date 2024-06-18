package com.example.comp1011_midterm_gc;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Student {
    private static final Set<String> PROVINCES = new HashSet<>();
    static {
        PROVINCES.add("AB");
        PROVINCES.add("BC");
        PROVINCES.add("MB");
        PROVINCES.add("NB");
        PROVINCES.add("NL");
        PROVINCES.add("NS");
        PROVINCES.add("NT");
        PROVINCES.add("NU");
        PROVINCES.add("ON");
        PROVINCES.add("PE");
        PROVINCES.add("QC");
        PROVINCES.add("SK");
        PROVINCES.add("YT");
    }

    private int studentNumber;
    private String firstName;
    private String lastName;
    private String telephone;
    private String address;
    private String province;
    private int averageGrade;
    private String major;

    public Student(){}

    public Student(int studentNumber, String firstName, String lastName, String telephone, String address, String province, int averageGrade, String major) {
        setStudentNumber(studentNumber);
        setFirstName(firstName);
        setLastName(lastName);
        setTelephone(telephone);
        setAddress(address);
        setProvince(province);
        setAverageGrade(averageGrade);
        setMajor(major);
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        if (studentNumber > 200034000) {
            this.studentNumber = studentNumber;
        } else {
            throw new IllegalArgumentException("Student number must be greater than 200034000");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > 1) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("First name must be more than 1 character");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 1) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Last name must be more than 1 character");
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        if (Pattern.matches("^\\(\\d{3}\\) \\d{3}-\\d{4}$", telephone)) {
            this.telephone = telephone;
        } else {
            throw new IllegalArgumentException("Telephone number must match the North American dialing plan");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() > 6) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("Address must be more than 6 characters");
        }
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        if (PROVINCES.contains(province)) {
            this.province = province;
        } else {
            throw new IllegalArgumentException("Province must be one of the following: " + PROVINCES);
        }
    }

    public int getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(int averageGrade) {
        if (averageGrade >= 0 && averageGrade <= 100) {
            this.averageGrade = averageGrade;
        } else {
            throw new IllegalArgumentException("Average grade must be in the range of 0-100");
        }
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if (major.length() > 5) {
            this.major = major;
        } else {
            throw new IllegalArgumentException("Major must be greater than 5 characters");
        }
    }

    public static void main(String[] args) {
        try {
            Student student = new Student(200034001, "John", "Doe", "(123) 456-7890", "123 Main St", "ON", 85, "Computer Science");
            System.out.println("Student created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

