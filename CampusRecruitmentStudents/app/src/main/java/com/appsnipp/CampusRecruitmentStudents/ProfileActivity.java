package com.appsnipp.CampusRecruitmentStudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mikhaellopez.circularimageview.CircularImageView;

public class ProfileActivity extends AppCompatActivity {

    Button Resumedetails,uploadphoto,viewresume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Resumedetails=findViewById(R.id.fillresume);
        uploadphoto=findViewById(R.id.uploadphto);
        viewresume=findViewById(R.id.viewresum);



        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, UploadPhoto.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        viewresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ViewResume.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });


        CircularImageView circularImageView = (CircularImageView)findViewById(R.id.profile_image);

    }
}