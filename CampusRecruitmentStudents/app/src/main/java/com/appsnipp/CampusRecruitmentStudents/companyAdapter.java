package com.appsnipp.CampusRecruitmentStudents;

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


import com.appsnipp.CampusRecruitmentStudents.ModelClasses.Vacancy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class companyAdapter extends RecyclerView.Adapter<companyAdapter.ViewHolder> implements Filterable {
    public Context context;
    String a,b;
    ArrayList<Vacancy> listData;
    String companynm,imgurl;
    public companyAdapter(Context context, ArrayList<Vacancy> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_companiesadapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vacancy vacancy=listData.get(position);

        holder.companyname.setText(vacancy.getCompanyname());
        holder.companyaddress.setText(vacancy.getCompanyaddress());
        holder.description.setText(vacancy.getCompanydescprition());
        holder.vacantseatsno.setText(vacancy.getApplicantrequired());
        Picasso.with(context)
                .load(vacancy.getImageurl())
                .fit()
                .centerCrop()
                .into(holder.companyimg);


        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companynm=vacancy.getCompanyname();
                imgurl=vacancy.getImageurl();
                Intent intent = new Intent(context, CompanyDetails.class);
                intent.putExtra("imageurl",imgurl);
                intent.putExtra("companyname",companynm);
                context.startActivity(intent);
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
        private TextView companyname,companyaddress,description,vacantseatsno;
        private ImageView companyimg;
        String id;
        private Button apply;
        public ViewHolder(View itemView) {
            super(itemView);

            companyimg=(ImageView) itemView.findViewById(R.id.company_image);
            companyname=itemView.findViewById(R.id.companynamevi);
            companyaddress=itemView.findViewById(R.id.adresscompanyvi);
            description=itemView.findViewById(R.id.company_jobdescription);
            vacantseatsno=itemView.findViewById(R.id.vacancy);
            apply=itemView.findViewById(R.id.applybtn);
            Toast.makeText(context,b, Toast.LENGTH_SHORT).show();


        }

    }
}


