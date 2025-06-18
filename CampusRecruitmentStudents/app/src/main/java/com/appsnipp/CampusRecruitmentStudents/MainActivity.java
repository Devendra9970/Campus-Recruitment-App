package com.appsnipp.CampusRecruitmentStudents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.CampusRecruitmentStudents.ModelClasses.Vacancy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity {

    ImageView profile,selection;
    ArrayList<Vacancy> listData;
    private RecyclerView rv;
    private companyAdapter companyAdapter;
    ImageView viewstudentimage;
    Button selectionbtn,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile=findViewById(R.id.home_imageview1);
        selection=findViewById(R.id.home_imgview2);

        selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(), SelectedApplicantsactivity.class);
                startActivity(i1);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        rv=(RecyclerView)findViewById(R.id.homerecyclerview1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        listData=new ArrayList<Vacancy>();
        final FirebaseUser user;
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Vacancy");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    Vacancy l=npsnapshot.getValue(Vacancy.class);
                   // Toast.makeText(getApplicationContext(),"hello", Toast.LENGTH_SHORT).show();
                    listData.add(l);

                }
                companyAdapter=new companyAdapter(MainActivity.this,listData);
                rv.setAdapter(companyAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });




    }
}

