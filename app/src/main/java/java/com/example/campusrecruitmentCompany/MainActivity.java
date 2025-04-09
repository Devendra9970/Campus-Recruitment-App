package com.example.campusrecruitmentCompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.campusrecruitmentCompany.ModelClasses.ApplicantRequests;
import com.example.campusrecruitmentCompany.ModelClasses.Company;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button addvacancy;
    ArrayList<ApplicantRequests> listData;
    private RecyclerView rv;
    private Applicantrequests_adapter adapter;
    String userid;
    ImageView image,search;
    TextView textView1;
    String companyname;
    Button fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.mainprofileimage);
        addvacancy=findViewById(R.id.mainaddvacancy);
        textView1=findViewById(R.id.textView5);
        search=findViewById(R.id.search_image);
        image=findViewById(R.id.viewimage);

        rv=(RecyclerView)findViewById(R.id.homerecyclerview1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        listData=new ArrayList<ApplicantRequests>();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(i);
            }
        });

        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference myref = db.getReference("Company");
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot npsnapshot : ds.getChildren()){

                    String employeename=ds.child(userid).child("employeename").getValue().toString();
                    companyname=ds.child(userid).child("companyname").getValue().toString();
                    textView1.setText(employeename);
                    //Toast.makeText(MainActivity.this,companyname,Toast.LENGTH_LONG).show();


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



        final FirebaseUser user;
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("ApplicantRequests");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
//                    Company company=new Company();
//                    ApplicantRequests applicantRequests=new ApplicantRequests();
//                    Company l=npsnapshot.getValue(Company.class);
//                    String a=l.getCompanyname();
                    ApplicantRequests b=npsnapshot.getValue(ApplicantRequests.class);
                    String d=b.getCompanyname();
                    //Toast.makeText(getApplicationContext(),companyname,Toast.LENGTH_SHORT).show();
                    if(d.equals(companyname)) {
                        listData.add(b);
                    }
                }
                adapter=new Applicantrequests_adapter(MainActivity.this,listData);
                rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,profileactivity.class);
                startActivity(i);
            }
        });

        addvacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Vacancydetails.class);
                startActivity(i);
            }
        });


//        fab = findViewById(R.id.floatingActionButton);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Intent i=new Intent(MainActivity.this,MainActivity.class);
////                startActivity(i);
//
//
//            }
//        });
    }
}