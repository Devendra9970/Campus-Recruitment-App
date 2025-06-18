package com.appsnipp.CampusRecruitmentStudents.ModelClasses;

public class ApplicantRequests {
    String Jobdescription,developertype,companyname,workinghrs,applicantname,imageurl;

    public ApplicantRequests(String jobdescription, String developertype, String companyname, String workinghrs, String applicantname, String imageurl) {
        Jobdescription = jobdescription;
        this.developertype = developertype;
        this.companyname = companyname;
        this.workinghrs = workinghrs;
        this.applicantname = applicantname;
        this.imageurl = imageurl;
    }

    public ApplicantRequests() {
    }

    public String getJobdescription() {
        return Jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        Jobdescription = jobdescription;
    }

    public String getDevelopertype() {
        return developertype;
    }

    public void setDevelopertype(String developertype) {
        this.developertype = developertype;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getWorkinghrs() {
        return workinghrs;
    }

    public void setWorkinghrs(String workinghrs) {
        this.workinghrs = workinghrs;
    }

    public String getApplicantname() {
        return applicantname;
    }

    public void setApplicantname(String applicantname) {
        this.applicantname = applicantname;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
