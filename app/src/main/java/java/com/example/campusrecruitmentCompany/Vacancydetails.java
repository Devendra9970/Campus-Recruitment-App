package com.example.campusrecruitmentCompany;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.campusrecruitmentCompany.ModelClasses.Vacancy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/* In This Activity a company can post their advertise for the
type of developer they want , no of developer they want,etc.
 */

public class Vacancydetails extends AppCompatActivity {

    EditText jobdesc,workinghrs,developertype,applicantno;
    Button submit;
    AwesomeValidation awesomeValidation;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    String userid;
    Vacancy vacancy;
    public String companyname,description,imageurl,email,address,tagline;
    int id=0;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancydetails);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }

        jobdesc=findViewById(R.id.vacancydetails_jobdesc);
        workinghrs=findViewById(R.id.vacancydetails_wrkinghours);
        developertype=findViewById(R.id.vacancydetails_developertype);
        applicantno=findViewById(R.id.vacancydetails_applicantno);
        submit=findViewById(R.id.vdbutton);
        vacancy=new Vacancy();

        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        userid=user.getUid();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myref = database.getReference("Vacancy");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    id= (int) dataSnapshot.getChildrenCount();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference("Company");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){

                    companyname=dataSnapshot.child(userid).child("companyname").getValue().toString();
                    description=dataSnapshot.child(userid).child("description").getValue().toString();
                    imageurl=dataSnapshot.child(userid).child("imageurl").getValue().toString();
                    email=dataSnapshot.child(userid).child("companyemail").getValue().toString();
                    address=dataSnapshot.child(userid).child("companyaddress").getValue().toString();
                    tagline=dataSnapshot.child(userid).child("tagline").getValue().toString();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Vacancydetails.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0) {
                    registerUser();
                }
                else{
                    Toast.makeText(getApplicationContext(), "You Just Created A Vacancy.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void registerUser(){

        try {

            final String Jobdescription = jobdesc.getText().toString().trim();
            final String typeofDeveloperrequired = developertype.getText().toString().trim();
            final String workinghours = workinghrs.getText().toString().trim();
            final String applicantrequired = applicantno.getText().toString().trim();


            if (Jobdescription.isEmpty()) {
                jobdesc.setError(getString(R.string.input_error_jobdescription));
                jobdesc.requestFocus();
                return;
            }
            if (typeofDeveloperrequired.isEmpty()) {
                developertype.setError(getString(R.string.input_error_developertype));
                developertype.requestFocus();
                return;
            }
            if (workinghours.isEmpty()) {
                workinghrs.setError(getString(R.string.input_error_Workinghr));
                workinghrs.requestFocus();
                return;
            }
            if (applicantrequired.isEmpty()) {
                applicantno.setError(getString(R.string.input_error_applicantno));
                applicantno.requestFocus();
                return;
            }
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myref = database.getReference("Vacancy");


            vacancy.setCompanyname(companyname);
            vacancy.setComapnytagline(tagline);
            vacancy.setCompanydescprition(description);
            vacancy.setCompanyaddress(address);
            vacancy.setImageurl(imageurl);
            vacancy.setCompanyemail(email);
            vacancy.setDevelopertype(typeofDeveloperrequired);
            vacancy.setCompanydescprition(Jobdescription);
            vacancy.setWorkinghrs(workinghours);
            vacancy.setApplicantrequired(applicantrequired);
            vacancy.setJobdesc(Jobdescription);
            myref.child(String.valueOf(id + 1)).setValue(vacancy);

            count = 1;
            Toast.makeText(getApplicationContext(), "Vacancy Created Successfully", Toast.LENGTH_SHORT).show();


        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error In Creating Vacancy. Please Check Your Network", Toast.LENGTH_LONG).show();
        }

    }
}