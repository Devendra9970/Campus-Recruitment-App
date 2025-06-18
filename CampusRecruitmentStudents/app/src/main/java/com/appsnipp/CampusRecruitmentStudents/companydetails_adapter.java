package com.appsnipp.CampusRecruitmentStudents;

import android.content.Context;
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

import com.appsnipp.CampusRecruitmentStudents.ModelClasses.ApplicantRequests;
import com.appsnipp.CampusRecruitmentStudents.ModelClasses.SelectedApplicants;
import com.appsnipp.CampusRecruitmentStudents.ModelClasses.StudentResumeDetails;
import com.appsnipp.CampusRecruitmentStudents.ModelClasses.Vacancy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class companydetails_adapter extends RecyclerView.Adapter<companydetails_adapter.ViewHolder> implements Filterable {
    public Context context;
    String applicantnm,companynm,desc,workinghrs,developertype,imageurl,useid;
    ArrayList<Vacancy> listData;
    DatabaseReference myRef;
    FirebaseUser user;
    StudentResumeDetails studentResumeDetails;
    FirebaseAuth auth;
    String userid;
    int id=0;
    int ide=0;





    public companydetails_adapter(Context context, ArrayList<Vacancy> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_companydetails_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vacancy vacancy=listData.get(position);

        holder.companyname.setText(vacancy.getCompanyname());
        holder.companyaddress.setText(vacancy.getCompanyaddress());
        holder.description.setText(vacancy.getCompanydescprition());
        holder.devtype.setText(vacancy.getDevelopertype());
        holder.wrkinghrs.setText(vacancy.getWorkinghrs());
        holder.vacantseats.setText(vacancy.getApplicantrequired());

        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                companynm=vacancy.getCompanyname();
                desc=vacancy.getJobdesc();
                workinghrs=vacancy.getWorkinghrs();
                developertype=vacancy.getDevelopertype();

                final FirebaseDatabase db = FirebaseDatabase.getInstance();
                final DatabaseReference myref = db.getReference("ApplicantRequests");

                ApplicantRequests applicantRequests=new ApplicantRequests();
                applicantRequests.setApplicantname(applicantnm);
                applicantRequests.setJobdescription(desc);
                applicantRequests.setCompanyname(companynm);
                applicantRequests.setImageurl(imageurl);
                applicantRequests.setDevelopertype(developertype);
                applicantRequests.setWorkinghrs(workinghrs);
                myref.child(String.valueOf(id+1)).setValue(applicantRequests);
                Toast.makeText(context,"Applied Successfully", Toast.LENGTH_SHORT).show();
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
        private TextView companyname,companyaddress,description,vacantseats,devtype,wrkinghrs;
        private ImageView companyimg;
        private Button apply;
        public ViewHolder(View itemView) {
            super(itemView);

            companyname=itemView.findViewById(R.id.content_Companyname);
            companyaddress=itemView.findViewById(R.id.content_address);
            description=itemView.findViewById(R.id.content_description);
            devtype=itemView.findViewById(R.id.content_devtype);
            wrkinghrs=itemView.findViewById(R.id.content_workingHours);
            vacantseats=itemView.findViewById(R.id.content_applicantrequired);
            apply=itemView.findViewById(R.id.content_btn);


            auth= FirebaseAuth.getInstance();
            user=auth.getCurrentUser();
            userid=user.getUid();
            Toast.makeText(context.getApplicationContext(), userid,Toast.LENGTH_SHORT).show();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference("StudentResumeDetails");
            studentResumeDetails=new StudentResumeDetails();

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {

                        StudentResumeDetails l = npsnapshot.getValue(StudentResumeDetails.class);

                        if(l.getUserid().equals(userid))
                        {
                             applicantnm= l.getStudname();
                             imageurl=l.getImageurl();
                        }

                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //Toast.makeText(context,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

            Toast.makeText(context, applicantnm, Toast.LENGTH_SHORT).show();
            final FirebaseDatabase db = FirebaseDatabase.getInstance();
            final DatabaseReference reference = db.getReference("ApplicantRequests");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        id = (int) dataSnapshot.getChildrenCount();
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });





        }

    }
}


