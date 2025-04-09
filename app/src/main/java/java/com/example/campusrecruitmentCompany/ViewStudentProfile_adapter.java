package com.example.campusrecruitmentCompany;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.campusrecruitmentCompany.ModelClasses.ApplicantRequests;
import com.example.campusrecruitmentCompany.ModelClasses.Company;
import com.example.campusrecruitmentCompany.ModelClasses.SelectedApplicants;
import com.example.campusrecruitmentCompany.ModelClasses.StudentResumeDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewStudentProfile_adapter extends RecyclerView.Adapter<ViewStudentProfile_adapter.ViewHolder> implements Filterable {
    public Context context;
    ArrayList<StudentResumeDetails> listData;
    String applicantnme,companynm,desc,studemail;
    DatabaseReference myRef;
    FirebaseUser user;
    Company company=new Company();
    FirebaseAuth auth;
    String userid;
    long id=0;

    public ViewStudentProfile_adapter(Context context, ArrayList<StudentResumeDetails> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_student_profile_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StudentResumeDetails studentResumeDetails=listData.get(position);
        holder.studentname.setText(studentResumeDetails.getStudname());
        holder.email.setText(studentResumeDetails.getStudemail());
        holder.phone.setText(studentResumeDetails.getPhoneno());
        holder.schoolnam.setText(studentResumeDetails.getSchoolname());
        holder.schoolpassingyr.setText(studentResumeDetails.getSchoolpassingyear().toString());
        holder.sscpercentage.setText(studentResumeDetails.getSchoolpercenatge().toString());
        holder.degreeclg.setText(studentResumeDetails.getDegclgname());
        holder.degreepassingyr.setText(studentResumeDetails.getDegreepassingyear().toString());
        holder.degreepercentage.setText(studentResumeDetails.getDegreepercentage().toString());
        holder.achieve1.setText(studentResumeDetails.getAchievement1());
        holder.achieve2.setText(studentResumeDetails.getAchievement2());
        holder.achieve3.setText(studentResumeDetails.getAchievement3());
        holder.achieve4.setText(studentResumeDetails.getAchievement4());
        holder.langknown.setText(studentResumeDetails.getLangknown());
        holder.dbknown.setText(studentResumeDetails.getDbknown());
        holder.osknown.setText(studentResumeDetails.getOsknown());
        holder.ex1.setText(studentResumeDetails.getExp1());
        holder.ex2.setText(studentResumeDetails.getExp2());
        holder.ex3.setText(studentResumeDetails.getExp3());
        holder.ex4.setText(studentResumeDetails.getExp4());
        Picasso.with(context).load(studentResumeDetails.getImageurl()).into(holder.studimg);

        applicantnme=studentResumeDetails.getStudname();
        studemail=studentResumeDetails.getEmail();

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView studentname,email,phone,schoolnam,schoolpassingyr,sscpercentage,degreeclg,degreepercentage,degreepassingyr,achieve1,achieve2,achieve3,achieve4,langknown,dbknown,osknown,ex1,ex2,ex3,ex4;
        private ImageView studimg;
        String id;
        private Button b1,b2;
        public ViewHolder(View itemView) {
            super(itemView);

            studimg=itemView.findViewById(R.id.studImage);
            studentname=itemView.findViewById(R.id.studNamevi);
            email=itemView.findViewById(R.id.studemail);
            phone=itemView.findViewById(R.id.studphno);
            schoolnam=itemView.findViewById(R.id.schoolnamevi);
            schoolpassingyr=itemView.findViewById(R.id.schoolpassingvi);
            sscpercentage=itemView.findViewById(R.id.sscpervi);
            degreeclg=itemView.findViewById(R.id.degreeclgvi);
            degreepassingyr=itemView.findViewById(R.id.degreepassvi);
            degreepercentage=itemView.findViewById(R.id.degreepervi);
            achieve1=itemView.findViewById(R.id.achievement1vi);
            achieve2=itemView.findViewById(R.id.achievement2vi);
            achieve3=itemView.findViewById(R.id.achievement3vi);
            achieve4=itemView.findViewById(R.id.achievement4vi);
            langknown=itemView.findViewById(R.id.languageknownvi);
            dbknown=itemView.findViewById(R.id.dbknownvi);
            osknown=itemView.findViewById(R.id.osknownvi);
            ex1=itemView.findViewById(R.id.studExp1);
            ex2=itemView.findViewById(R.id.studExp2);
            ex3=itemView.findViewById(R.id.studExp3);
            ex4=itemView.findViewById(R.id.studExp4);
            b1=itemView.findViewById(R.id.studpro_btn);
            b2=itemView.findViewById(R.id.studreject_btn);
            long id=0;

            auth= FirebaseAuth.getInstance();
            user=auth.getCurrentUser();
            userid=user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference("Company");
            company=new Company();

            Toast.makeText(context.getApplicationContext(), "HHii", Toast.LENGTH_SHORT).show();

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {

                        companynm=dataSnapshot.child(userid).child("companyname").getValue().toString();


                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //Toast.makeText(context,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final FirebaseDatabase db = FirebaseDatabase.getInstance();
                    final DatabaseReference myref = db.getReference("SelectedApplicants");

                    SelectedApplicants selectedApplicants=new SelectedApplicants();
                    selectedApplicants.setCompanyname(companynm);
                    selectedApplicants.setJobdescription(desc);
                    selectedApplicants.setStudentemail(studemail);
                    selectedApplicants.setStudentname(applicantnme);
                    myref.child(String.valueOf(id+1)).setValue(selectedApplicants);
                    Toast.makeText(context,"Student Selected Successfully", Toast.LENGTH_SHORT).show();
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeApplication();
                    Intent intent=new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            });

        }

        public void removeApplication()
        {
            final FirebaseDatabase db = FirebaseDatabase.getInstance();
            final DatabaseReference ref = db.getReference("ApplicantRequests");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        ApplicantRequests requests=new ApplicantRequests();
                        ApplicantRequests l=npsnapshot.getValue(ApplicantRequests.class);
                        String a=l.getApplicantname();
                        String b=l.getCompanyname();
                        if(a.equals(applicantnme) && b.equals(companynm))
                        {
                            npsnapshot.getRef().removeValue();
                        }
                        Toast.makeText(context,"Applicant Rejected SuccessFully",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(context,"Failed to Reject Applicant",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}



