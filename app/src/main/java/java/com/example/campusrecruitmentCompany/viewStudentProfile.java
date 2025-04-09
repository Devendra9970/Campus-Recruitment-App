package com.example.campusrecruitmentCompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.campusrecruitmentCompany.ModelClasses.StudentResumeDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class viewStudentProfile extends AppCompatActivity {
    ArrayList<StudentResumeDetails> listData;
    private RecyclerView rv;
    private ViewStudentProfile_adapter adapter;
    String userid,useid,imageurl,a,b;
    ImageView image;
    long id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_profile);

        //image=findViewById(R.id.studpro_img1);
        rv=(RecyclerView)findViewById(R.id.viewstudprofile_recyclerview1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(viewStudentProfile.this));
        listData=new ArrayList<StudentResumeDetails>();

        a=getIntent().getStringExtra("Applicantname");
        b=getIntent().getStringExtra("imageurl");

        Log.d("NAme",a);

        final FirebaseUser user;
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("StudentResumeDetails");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    StudentResumeDetails ld=npsnapshot.getValue(StudentResumeDetails.class);
                    String d=ld.getImageurl();
                    if(d.equals(b))
                    {
                        imageurl=dataSnapshot.child(String.valueOf(id+1)).child("imageurl").getValue().toString();
                    }




                    //StudentResumeDetails studentResumeDetails=new StudentResumeDetails();
                    //StudentResumeDetails l=npsnapshot.getValue(StudentResumeDetails.class);
                    String c=ld.getStudname();
                    //Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
                    Log.d(" Stud NAme",c);
                    if(c.equalsIgnoreCase(a)) {
                        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
                        listData.add(ld);
                    }
                }
                adapter=new ViewStudentProfile_adapter(viewStudentProfile.this,listData);
                rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });



    }
}