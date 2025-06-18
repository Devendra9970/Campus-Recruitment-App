package com.appsnipp.CampusRecruitmentStudents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.appsnipp.CampusRecruitmentStudents.ModelClasses.Vacancy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CompanyDetails extends AppCompatActivity {
    ArrayList<Vacancy> listData;
    private RecyclerView rv;
    private companydetails_adapter adapter;
    String userid;
    ImageView image;
    public String a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);


        image=findViewById(R.id.companydetails_image);
        rv=(RecyclerView)findViewById(R.id.companydetails_recyclerview1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(CompanyDetails.this));
        a=getIntent().getStringExtra("imageurl");
        b=getIntent().getStringExtra("companyname");
        //Toast.makeText(getApplicationContext(),b,Toast.LENGTH_SHORT).show();

        listData=new ArrayList<Vacancy>();
        Picasso.with(CompanyDetails.this)
                .load(a)
                .fit()
                .centerCrop()
                .into(image);
        final FirebaseUser user;
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Vacancy");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    Vacancy l=npsnapshot.getValue(Vacancy.class);
                    String c=l.getCompanyname();
                    if(c.equals(b)) {
                        listData.add(l);
                    }
                }
                adapter=new companydetails_adapter(CompanyDetails.this,listData);
                rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });



    }
}