package com.appsnipp.CampusRecruitmentStudents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.appsnipp.CampusRecruitmentStudents.ModelClasses.SelectedApplicants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectedApplicantsactivity extends AppCompatActivity {
    ArrayList<SelectedApplicants> listData;
    private RecyclerView rv;
    private SelectedApplicants_adapter adapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String a;
    LinearLayout lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_applicants);

        rv=(RecyclerView)findViewById(R.id.selectedapplicants_recyclerview1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(SelectedApplicantsactivity.this));
        listData = new ArrayList<SelectedApplicants>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        a=firebaseUser.getEmail();

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("SelectedApplicants");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                    SelectedApplicants l = npsnapshot.getValue(SelectedApplicants.class);
                    if (l.getStudentemail().contains(a.toString())) {
                        listData.add(l);
                    }
                }
                adapter = new SelectedApplicants_adapter(SelectedApplicantsactivity.this, listData);
                rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SelectedApplicantsactivity.this, "opps.....", Toast.LENGTH_SHORT).show();
            }
        });
    }


}