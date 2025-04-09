package com.example.campusrecruitmentCompany.ModelClasses;

public class Company {
    String employeename,companyname,companyaddress,mobileno,
            companyemail,companypassword,description,tagline,imageurl;

    public Company(String employeename, String companyname, String companyaddress, String companyemail, String companypassword, String mobileno, String description, String tagline, String imageurl) {
        this.employeename = employeename;
        this.companyname = companyname;
        this.companyaddress = companyaddress;
        this.companyemail = companyemail;
        this.companypassword = companypassword;
        this.mobileno = mobileno;
        this.description=description;
        this.tagline = tagline;
        this.imageurl = imageurl;
    }

    public Company() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getCompanypassword() {
        return companypassword;
    }

    public void setCompanypassword(String companypassword) {
        this.companypassword = companypassword;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
}
