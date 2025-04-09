package com.example.campusrecruitmentCompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.campusrecruitmentCompany.ModelClasses.Company;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class profileactivity extends AppCompatActivity {
    ArrayList<Company> listData;
    private RecyclerView rv;
    private viewprofile_adapter adapter;
    String userid;
    ImageView image;

    Button signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);

        image=findViewById(R.id.viewimage);
        rv=(RecyclerView)findViewById(R.id.viewrecyclerview1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(profileactivity.this));
        listData=new ArrayList<Company>();


        final FirebaseUser user;
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Company");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){

                    String imgurl=dataSnapshot.child(userid).child("imageurl").getValue().toString();

                    Picasso.with(profileactivity.this)
                            .load(imgurl)
                            .fit()
                            .centerCrop()
                            .into(image);

                    String userid=user.getEmail();
                    Company company=new Company();
                    Company l=npsnapshot.getValue(Company.class);
                    String a=l.getCompanyemail();
                    //Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
                    if(userid.equals(a)) {
                        listData.add(l);
                    }
                }
                adapter=new viewprofile_adapter(profileactivity.this,listData);
                rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });

        signout = findViewById(R.id.signout1);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}