package com.appsnipp.CampusRecruitmentStudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.appsnipp.CampusRecruitmentStudents.ModelClasses.StudentResumeDetails;
import com.appsnipp.CampusRecruitmentStudents.ModelClasses.Students;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FillResumeDertails extends AppCompatActivity {


    TextInputEditText studname,fathername,dateofbirth,nationality,hobbies,schoolname,schoolpassingyear,sscpercent,
    degclgname,degpassing,degpercentage,achievement1,achievement2,achievement3,achievement4,langknown,osknown,dbknown,role;

    Button submit;

    DatabaseReference myRef, refg;
    FirebaseAuth auth;
    FirebaseUser user;
    String email,useid;
    Students students;
    long id=0;
    StudentResumeDetails studentResumeDetails;
    final Calendar myCalendar = Calendar.getInstance();
    public String ImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_resume_dertails);

        studname=findViewById(R.id.studentname);
        fathername=findViewById(R.id.fathername);
        dateofbirth=findViewById(R.id.dateofbirth);
        nationality=findViewById(R.id.nationality);
        hobbies=findViewById(R.id.hobbies);
        schoolname=findViewById(R.id.eschname);
        schoolpassingyear=findViewById(R.id.eschendd);
        sscpercent=findViewById(R.id.sccpercentage);
        degclgname=findViewById(R.id.clgname);
        degpassing=findViewById(R.id.graduationcompletedate);
        degpercentage=findViewById(R.id.degreepercentage);
        achievement1=findViewById(R.id.achievement1);
        achievement2=findViewById(R.id.achievement2);
        achievement3=findViewById(R.id.achievement3);
        achievement4=findViewById(R.id.achievement4);
        langknown=findViewById(R.id.languageknown);
        osknown=findViewById(R.id.operatingsystem);
        dbknown=findViewById(R.id.databaseknown);
        role=findViewById(R.id.role);
        submit=findViewById(R.id.submitbtnprofiledetails);
        ImageUrl = getIntent().getStringExtra("Imageurl");


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //updateLabel();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dateofbirth.setText(sdf.format(myCalendar.getTime()));
            }

        };

        dateofbirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FillResumeDertails.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("StudentResumeDetails");

        studentResumeDetails=new StudentResumeDetails();
        refg = database.getReference("Students");
        useid=user.getUid();
        Toast.makeText(getApplicationContext(),useid,Toast.LENGTH_SHORT).show();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    id=(dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                    email= dataSnapshot.child(useid).child("email").getValue().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata();
            }
        });

    }

//    private void updateLabel(){
//
//    }

    public void savedata()
    {
        final ProgressDialog progressDialog = new ProgressDialog(FillResumeDertails.this);
        progressDialog.setTitle("Saving...");
        progressDialog.show();

        String stdname,fatname,dob,natnlity,hob,schlname,
                degclgnm,achieve1,achieve2,
                achieve3,achieve4,langknownnn,operatingsystemknown,databaseknown,rolein;
        Integer schoolpassingyr,degpassng;
        Float sscper,degper;
        Toast.makeText(this,useid,Toast.LENGTH_SHORT).show();
        stdname=studname.getText().toString();
        fatname=fathername.getText().toString();
        dob=dateofbirth.getText().toString();
        natnlity=nationality.getText().toString();
        hob=hobbies.getText().toString();
        schlname=schoolname.getText().toString();
        schoolpassingyr=Integer.parseInt(schoolpassingyear.getText().toString());
        sscper=Float.parseFloat(sscpercent.getText().toString());
        degclgnm=degclgname.getText().toString();
        degpassng=Integer.parseInt(degpassing.getText().toString());
        degper=Float.parseFloat(degpercentage.getText().toString());
        achieve1=achievement1.getText().toString();
        achieve2=achievement2.getText().toString();
        achieve3=achievement3.getText().toString();
        achieve4=achievement4.getText().toString();
        langknownnn=langknown.getText().toString();
        operatingsystemknown=osknown.getText().toString();
        databaseknown=dbknown.getText().toString();
        rolein=role.getText().toString();


        studentResumeDetails.setStudname(stdname);
        studentResumeDetails.setFathername(fatname);
        studentResumeDetails.setDateofbirth(dob);
        studentResumeDetails.setNationality(natnlity);
        studentResumeDetails.setHobbies(hob);
        studentResumeDetails.setSchoolname(schlname);
        studentResumeDetails.setSchoolpassingyear(schoolpassingyr);
        studentResumeDetails.setSchoolpercenatge(sscper);
        studentResumeDetails.setDegclgname(degclgnm);
        studentResumeDetails.setDegreepassingyear(degpassng);
        studentResumeDetails.setDegreepercentage(degper);
        studentResumeDetails.setAchievement1(achieve1);
        studentResumeDetails.setAchievement2(achieve2);
        studentResumeDetails.setAchievement3(achieve3);
        studentResumeDetails.setAchievement4(achieve4);
        studentResumeDetails.setLangknown(langknownnn);
        studentResumeDetails.setOsknown(operatingsystemknown);
        studentResumeDetails.setDbknown(databaseknown);
        studentResumeDetails.setFathername(fatname);
        studentResumeDetails.setRole(rolein);
        studentResumeDetails.setEmail(email);
        studentResumeDetails.setImageurl(ImageUrl);
        studentResumeDetails.setUserid(useid);
        myRef.child(String.valueOf(id+1)).setValue(studentResumeDetails);
        Toast.makeText(getApplicationContext(),"Profile Inserted Successully",Toast.LENGTH_SHORT).show();


    }
}
