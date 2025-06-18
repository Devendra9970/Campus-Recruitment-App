package com.appsnipp.CampusRecruitmentStudents.ModelClasses;

public class Students {
    public String studentname;
    public String mobileno;
    public String email;
    public String password;

    public Students() {
    }

    public Students(String studentname, String mobileno, String email, String password) {
        this.studentname = studentname;
        this.mobileno = mobileno;
        this.email = email;
        this.password = password;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}