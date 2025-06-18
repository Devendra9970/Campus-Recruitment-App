package com.appsnipp.CampusRecruitmentStudents;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.appsnipp.CampusRecruitmentStudents.ModelClasses.SelectedApplicants;

import java.util.ArrayList;

public class SelectedApplicants_adapter extends RecyclerView.Adapter<SelectedApplicants_adapter.ViewHolder> implements Filterable {
    public Context context;
    String a,b;
    ArrayList<SelectedApplicants> listData;
    public SelectedApplicants_adapter(Context context, ArrayList<SelectedApplicants> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_selected_applicants_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SelectedApplicants selectedApplicants=listData.get(position);
        a=selectedApplicants.getCompanyname();
        b=selectedApplicants.getStudentname();
        holder.notification.setText("Congratulation! "+b+" You have been selected by"+a);
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
        private TextView notification;
        public ViewHolder(View itemView) {
            super(itemView);
            notification=itemView.findViewById(R.id.notification_selectedapp);

        }

    }
}




