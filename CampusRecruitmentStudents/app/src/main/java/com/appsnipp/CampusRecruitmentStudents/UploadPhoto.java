package com.appsnipp.CampusRecruitmentStudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.appsnipp.CampusRecruitmentStudents.ModelClasses.StudentResumeDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadPhoto extends AppCompatActivity {

    Button uploadbtn,choosebtn;
    ImageView img1;
    DatabaseReference myRef, refg;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseUser user;
    StudentResumeDetails studentResumeDetails;
    private static final int CHOOSE_IMAGE = 1;
    FirebaseAuth auth;
    long id=0;
    String email,useid;
    private Uri imgUrl;
    private StorageTask mUploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);


        img1=findViewById(R.id.profile_image);
        uploadbtn=findViewById(R.id.uploadphotobtn);
        choosebtn=findViewById(R.id.choosephotobtn);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("StudentResumeDetails");
        refg = database.getReference("Students");
        useid=user.getUid();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        studentResumeDetails=new StudentResumeDetails();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    id=(dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                    email= dataSnapshot.child(useid).child("email").getValue().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });


    }
    private void chooseImage() {
        Intent i1 = new Intent();
        i1.setType("image/*");
        i1.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i1, CHOOSE_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUrl = data.getData();

            Picasso.with(this).load(imgUrl).into(img1);
        }
    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
    private void uploadImage() {
        if (imgUrl != null) {

            final StorageReference ref = storageReference.child("Studentimages/" + System.currentTimeMillis() + "." + GetFileExtension(imgUrl));
            mUploadTask = ref.putFile(imgUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String Imageurl = uri.toString();
                                    //Toast.makeText(UploadPhoto.this, Imageurl, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(UploadPhoto.this, FillResumeDertails.class);
                                    intent.putExtra("Imageurl", Imageurl);
                                    startActivity(intent);

                                }
                            });
                        }
                    })


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }


    }
}