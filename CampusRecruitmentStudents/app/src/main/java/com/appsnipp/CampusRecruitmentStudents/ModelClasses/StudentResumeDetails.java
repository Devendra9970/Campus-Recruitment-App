package com.appsnipp.CampusRecruitmentStudents.ModelClasses;

public class StudentResumeDetails {

    String studname,fathername,dateofbirth,nationality,hobbies,schoolname,degclgname,achievement1,achievement2,achievement3,achievement4,langknown,osknown,dbknown,role,imageurl,userid;
    Integer schoolpassingyear;
    Integer degreepassingyear;
    String email;
    Float schoolpercenatge,degreepercentage;



    public StudentResumeDetails() {
    }


    public StudentResumeDetails(String studname, String fathername, String dateofbirth, String nationality, String hobbies, String schoolname, String degclgname, String achievement1, String achievement2, String achievement3, String achievement4, String langknown, String osknown, String dbknown, String role, Integer schoolpassingyear, Integer degreepassingyear, Float schoolpercenatge, Float degreepercentage, String email, String imageurl, String userid) {
        this.studname = studname;
        this.fathername = fathername;
        this.dateofbirth = dateofbirth;
        this.nationality = nationality;
        this.hobbies = hobbies;
        this.schoolname = schoolname;
        this.degclgname = degclgname;
        this.achievement1 = achievement1;
        this.achievement2 = achievement2;
        this.achievement3 = achievement3;
        this.achievement4 = achievement4;
        this.langknown = langknown;
        this.osknown = osknown;
        this.dbknown = dbknown;
        this.role = role;
        this.schoolpassingyear = schoolpassingyear;
        this.degreepassingyear = degreepassingyear;
        this.schoolpercenatge = schoolpercenatge;
        this.degreepercentage = degreepercentage;
        this.email = email;
        this.imageurl = imageurl;
        this.userid = userid;


    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudname() {
        return studname;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public void setStudname(String studname) {
        this.studname = studname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getDegclgname() {
        return degclgname;
    }

    public void setDegclgname(String degclgname) {
        this.degclgname = degclgname;
    }

    public String getAchievement1() {
        return achievement1;
    }

    public void setAchievement1(String achievement1) {
        this.achievement1 = achievement1;
    }

    public String getAchievement2() {
        return achievement2;
    }

    public void setAchievement2(String achievement2) {
        this.achievement2 = achievement2;
    }

    public String getAchievement3() {
        return achievement3;
    }

    public void setAchievement3(String achievement3) {
        this.achievement3 = achievement3;
    }

    public String getAchievement4() {
        return achievement4;
    }

    public void setAchievement4(String achievement4) {
        this.achievement4 = achievement4;
    }

    public String getLangknown() {
        return langknown;
    }

    public void setLangknown(String langknown) {
        this.langknown = langknown;
    }

    public String getOsknown() {
        return osknown;
    }

    public void setOsknown(String osknown) {
        this.osknown = osknown;
    }

    public String getDbknown() {
        return dbknown;
    }

    public void setDbknown(String dbknown) {
        this.dbknown = dbknown;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getSchoolpassingyear() {
        return schoolpassingyear;
    }

    public void setSchoolpassingyear(Integer schoolpassingyear) {
        this.schoolpassingyear = schoolpassingyear;
    }

    public Integer getDegreepassingyear() {
        return degreepassingyear;
    }

    public void setDegreepassingyear(Integer degreepassingyear) {
        this.degreepassingyear = degreepassingyear;
    }

    public Float getSchoolpercenatge() {
        return schoolpercenatge;
    }

    public void setSchoolpercenatge(Float schoolpercenatge) {
        this.schoolpercenatge = schoolpercenatge;
    }

    public Float getDegreepercentage() {
        return degreepercentage;
    }

    public void setDegreepercentage(Float degreepercentage) {
        this.degreepercentage = degreepercentage;
    }
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
