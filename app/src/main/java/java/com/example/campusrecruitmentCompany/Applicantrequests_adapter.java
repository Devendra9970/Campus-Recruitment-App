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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Applicantrequests_adapter extends RecyclerView.Adapter<Applicantrequests_adapter.ViewHolder> implements Filterable {
    public Context context;
    String applicantnme,imgurl;
    String imageurl,studname;
    ArrayList<ApplicantRequests> listData;

    public Applicantrequests_adapter(Context context, ArrayList<ApplicantRequests> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_studentapplications_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ApplicantRequests applicantRequests=listData.get(position);

        holder.applicantname.setText(applicantRequests.getApplicantname());
        holder.appliedfor.setText(applicantRequests.getJobdescription());
        holder.workinghrs.setText(applicantRequests.getWorkinghrs());
        holder.devtype.setText(applicantRequests.getDevelopertype());
        Picasso.with(context)
                .load(applicantRequests.getImageurl())
                .fit()
                .centerCrop()
                .into(holder.applicantimg);
        applicantnme=applicantRequests.getApplicantname();
        imgurl=applicantRequests.getImageurl();

        holder.viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studname = applicantRequests.getApplicantname();
                imageurl=applicantRequests.getImageurl();
                Intent i1=new Intent(context.getApplicationContext(),viewStudentProfile.class);
                i1.putExtra("Applicantname",studname);
                i1.putExtra("imageurl",imageurl);
                context.startActivity(i1);

                //Toast.makeText(context.getApplicationContext(), "Approve By Admin First", Toast.LENGTH_SHORT).show();
            }
        });
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
        private TextView applicantname,appliedfor,workinghrs,devtype;
        private ImageView applicantimg;
        String id;
        private Button viewprofile;
        public ViewHolder(View itemView) {
            super(itemView);

            applicantname=itemView.findViewById(R.id.applicantname_studappvi);
            appliedfor=itemView.findViewById(R.id.desc_studappvi);
            workinghrs=itemView.findViewById(R.id.workinghrs_studappvi);
            devtype=itemView.findViewById(R.id.developertype_studappvi);
            viewprofile=itemView.findViewById(R.id.btn1);
            applicantimg=itemView.findViewById(R.id.img_studappvi);



        }

    }
}






