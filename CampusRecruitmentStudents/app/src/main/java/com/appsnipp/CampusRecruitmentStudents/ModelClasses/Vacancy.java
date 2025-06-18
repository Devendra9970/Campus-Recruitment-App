package com.appsnipp.CampusRecruitmentStudents.ModelClasses;

public class Vacancy {
    String jobdesc,workinghrs,developertype,applicantrequired,companyname,
            companyemail,comapnytagline,companydescprition,imageurl,companyaddress;

    public Vacancy(String jobdesc, String workinghrs, String developertype, String applicantrequired, String companyname, String companyemail, String comapnytagline, String companydescprition, String imageurl) {
        this.jobdesc = jobdesc;
        this.workinghrs = workinghrs;
        this.developertype = developertype;
        this.applicantrequired = applicantrequired;
        this.companyname = companyname;
        this.companyemail = companyemail;
        this.comapnytagline = comapnytagline;
        this.companydescprition = companydescprition;
        this.imageurl = imageurl;
        this.companyaddress = companyaddress;
    }

    public Vacancy() {
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public String getJobdesc() {
        return jobdesc;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }

    public String getWorkinghrs() {
        return workinghrs;
    }

    public void setWorkinghrs(String workinghrs) {
        this.workinghrs = workinghrs;
    }

    public String getDevelopertype() {
        return developertype;
    }

    public void setDevelopertype(String developertype) {
        this.developertype = developertype;
    }

    public String getApplicantrequired() {
        return applicantrequired;
    }

    public void setApplicantrequired(String applicantrequired) {
        this.applicantrequired = applicantrequired;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getComapnytagline() {
        return comapnytagline;
    }

    public void setComapnytagline(String comapnytagline) {
        this.comapnytagline = comapnytagline;
    }

    public String getCompanydescprition() {
        return companydescprition;
    }

    public void setCompanydescprition(String companydescprition) {
        this.companydescprition = companydescprition;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
