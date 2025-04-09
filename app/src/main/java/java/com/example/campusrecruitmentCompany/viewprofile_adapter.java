package com.example.campusrecruitmentCompany;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.campusrecruitmentCompany.ModelClasses.Company;

import java.util.ArrayList;

public class viewprofile_adapter extends RecyclerView.Adapter<viewprofile_adapter.ViewHolder> implements Filterable {
    public Context context;
    ArrayList<Company> listData;
    public viewprofile_adapter(Context context, ArrayList<Company> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_viewprofile_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Company company=listData.get(position);

        holder.employeename.setText(company.getEmployeename());
        holder.companyname.setText(company.getCompanyname());
        holder.companyaddress.setText(company.getCompanyaddress());
        holder.description.setText(company.getDescription());
        holder.tagline.setText(company.getTagline());
        holder.phoneno.setText(company.getMobileno());
        holder.email.setText(company.getCompanyemail());

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
        private TextView companyname,companyaddress,description,employeename,tagline,email,phoneno;
        public ViewHolder(View itemView) {
            super(itemView);

            companyname=itemView.findViewById(R.id.companynamevi);
            companyaddress=itemView.findViewById(R.id.addressvi);
            description=itemView.findViewById(R.id.descriptionvi);
            employeename=itemView.findViewById(R.id.employeeNamevi);
            tagline=itemView.findViewById(R.id.taglinevi);
            email=itemView.findViewById(R.id.emailvi);
            phoneno= itemView.findViewById(R.id.phnovi);

        }

    }
}








