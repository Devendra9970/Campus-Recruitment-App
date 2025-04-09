package com.example.campusrecruitmentCompany;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.campusrecruitmentCompany.ModelClasses.StudentResumeDetails;

import java.util.ArrayList;

public class searchadapter extends RecyclerView.Adapter<searchadapter.MyViewHolder> {
    String imageurl,pgname;
    public Context context;
    ArrayList<StudentResumeDetails> list;


    public searchadapter(Context context, ArrayList<StudentResumeDetails> list){
        this.context = context;
        this.list=list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_search_adapter,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        StudentResumeDetails studentResumeDetails=list.get(i);
        holder.studname.setText(list.get(i).getStudname());
        holder.langknown.setText(list.get(i).getLangknown());
       // holder.rent.setText(list.get(i).getRent());
        Glide.with(holder.ImageView.getContext()).load(studentResumeDetails.getImageurl()).into(holder.ImageView);

        holder.cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgname = studentResumeDetails.getStudname();
                imageurl=studentResumeDetails.getImageurl();
                Intent i = new Intent(context.getApplicationContext(), viewStudentProfile.class);
                i.putExtra("Applicantname",pgname);
                i.putExtra("imageurl",imageurl);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView studname,langknown;
        private ImageView ImageView;
        private CardView cv1;

        public MyViewHolder(View itemView){
            super(itemView);


            studname=itemView.findViewById(R.id.Studentname_search);
            langknown=itemView.findViewById(R.id.langknown_search);
            //rent=itemView.findViewById(R.id.rent_search);
            ImageView = (ImageView)itemView.findViewById(R.id.imageView2);
            cv1=itemView.findViewById(R.id.cardv);
        }
    }
}
