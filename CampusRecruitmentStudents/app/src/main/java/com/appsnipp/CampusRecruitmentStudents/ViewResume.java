package com.appsnipp.CampusRecruitmentStudents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.appsnipp.CampusRecruitmentStudents.ModelClasses.StudentResumeDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewResume extends AppCompatActivity {

    ArrayList<StudentResumeDetails> listData;
    private RecyclerView rv;
    private ViewResumeAdapter viewResumeAdapter;
    ImageView viewstudentimage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resume);

        viewstudentimage=findViewById(R.id.viewprofile_image);

        rv=(RecyclerView)findViewById(R.id.viewrecyclerview1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(ViewResume.this));
        listData=new ArrayList<StudentResumeDetails>();
        final FirebaseUser user;
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("StudentResumeDetails");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    String email=user.getEmail();
                    StudentResumeDetails studentResumeDetails=new StudentResumeDetails();
                    StudentResumeDetails l=npsnapshot.getValue(StudentResumeDetails.class);
                    String a=l.getEmail();
                    Toast.makeText(getApplicationContext(),"hello", Toast.LENGTH_SHORT).show();
                    if(email.equals(a)) {
                        listData.add(l);
                    }

                }
                viewResumeAdapter=new ViewResumeAdapter(ViewResume.this,listData);
                rv.setAdapter(viewResumeAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });



    }
}