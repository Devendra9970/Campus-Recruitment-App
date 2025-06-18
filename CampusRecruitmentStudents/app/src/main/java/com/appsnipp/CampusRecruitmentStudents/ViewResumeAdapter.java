package com.appsnipp.CampusRecruitmentStudents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.appsnipp.CampusRecruitmentStudents.ModelClasses.StudentResumeDetails;

import java.util.ArrayList;

public class ViewResumeAdapter extends RecyclerView.Adapter<ViewResumeAdapter.ViewHolder> implements Filterable {
    public Context context;
    ArrayList<StudentResumeDetails> listData;
    public ViewResumeAdapter(Context context, ArrayList<StudentResumeDetails> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_viewresume_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StudentResumeDetails studentResumeDetails=listData.get(position);
        holder.studentname.setText(studentResumeDetails.getStudname());
        holder.fathername.setText(studentResumeDetails.getFathername());
        holder.dob.setText(studentResumeDetails.getDateofbirth());
        holder.nationality.setText(studentResumeDetails.getNationality());
        holder.hobbies.setText(studentResumeDetails.getHobbies());
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
        holder.rolekn.setText(studentResumeDetails.getRole());

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
        private TextView studentname,fathername,dob,nationality,hobbies,schoolnam,schoolpassingyr,sscpercentage,degreeclg,degreepercentage,degreepassingyr,achieve1,achieve2,achieve3,achieve4,langknown,dbknown,osknown,rolekn;
        private ImageView studimg;
        String id;
        private ImageButton b1,b2;
        public ViewHolder(View itemView) {
            super(itemView);

            studentname=itemView.findViewById(R.id.studNamevi);
            fathername=itemView.findViewById(R.id.fathernamevi);
            dob=itemView.findViewById(R.id.dobvi);
            nationality=itemView.findViewById(R.id.nationalityvi);
            hobbies=itemView.findViewById(R.id.hobbiesvi);
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
            rolekn=itemView.findViewById(R.id.rolevi);

        }

    }
}

