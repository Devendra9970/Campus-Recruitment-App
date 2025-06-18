package com.appsnipp.CampusRecruitmentStudents.ModelClasses;

public class SelectedApplicants {
    String Studentname,companyname,jobdescription,studentemail;

    public SelectedApplicants(String studentname, String companyname, String jobdescription, String studentemail) {
        Studentname = studentname;
        this.companyname = companyname;
        this.jobdescription = jobdescription;
        this.studentemail = studentemail;
    }

    public SelectedApplicants() {
    }

    public String getStudentname() {
        return Studentname;
    }

    public void setStudentname(String studentname) {
        Studentname = studentname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail;
    }
}
