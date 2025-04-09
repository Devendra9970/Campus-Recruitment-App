package com.example.campusrecruitmentCompany;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.campusrecruitmentCompany.ModelClasses.Company;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Registerationactivity extends AppCompatActivity {
    TextView t1;
    EditText name,companynam,companyaddres,phoneno,desc,emailt,pass,tagline;
    ImageView imageView1;
    Button submit;
    AwesomeValidation awesomeValidation;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    private static final int CHOOSE_IMAGE = 1;
    private Uri imgUrl;
    private StorageTask mUploadTask;
    public  String Imageurl;
    StorageReference storageReference;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerationactivity);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }

        name=findViewById(R.id.name);
        companynam=findViewById(R.id.registercompanyname);
        companyaddres=findViewById(R.id.registercompanyaddress);
        phoneno=findViewById(R.id.registerphno);
        emailt=findViewById(R.id.registeremail);
        pass=findViewById(R.id.registerpassword);
        tagline=findViewById(R.id.taglinet);
        desc=findViewById(R.id.registerdesc);
        submit=findViewById(R.id.button);
        t1=findViewById(R.id.textView4);

        imageView1=findViewById(R.id.registerationimg1);

        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("Company");

       imageView1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               chooseImage();
           }
       });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


        t1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Registerationactivity.this, LoginActivity.class);
                startActivity(intent);
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
            Picasso.with(this).load(imgUrl).into(imageView1);
        }
    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    private void registerUser() {

        if (imgUrl != null) {
            Toast.makeText(Registerationactivity.this, "Registering...", Toast.LENGTH_SHORT).show();
            final StorageReference ref = storageReference.child("Companyimages/" + System.currentTimeMillis() + "." + GetFileExtension(imgUrl));
            mUploadTask = ref.putFile(imgUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Imageurl = uri.toString();
                                    //final String imageurl=Imageurl;
                                    final String employeename = name.getText().toString().trim();
                                    final String companyname = companynam.getText().toString().trim();
                                    final String companyaddress = companyaddres.getText().toString().trim();
                                    final String email = emailt.getText().toString().trim();
                                    final String password = pass.getText().toString().trim();
                                    final String mobileno = phoneno.getText().toString().trim();
                                    final String description = desc.getText().toString().trim();
                                    final String taglin = tagline.getText().toString().trim();




                                    if (employeename.isEmpty()) {
                                        name.setError(getString(R.string.input_error_name));
                                        name.requestFocus();
                                        return;
                                    }
                                    if (companyname.isEmpty()) {
                                        companynam.setError(getString(R.string.input_error_address));
                                        companynam.requestFocus();
                                        return;
                                    }
                                    if (companyaddress.isEmpty()) {
                                        companyaddres.setError(getString(R.string.input_error_address));
                                        companyaddres.requestFocus();
                                        return;
                                    }
                                    if (taglin.isEmpty()) {
                                        tagline.setError(getString(R.string.input_error_tagline));
                                        tagline.requestFocus();
                                        return;
                                    }
                                    if (description.isEmpty()) {
                                        desc.setError(getString(R.string.input_error_description));
                                        desc.requestFocus();
                                        return;
                                    }
                                    if (email.isEmpty()) {
                                        emailt.setError(getString(R.string.input_error_email));
                                        emailt.requestFocus();
                                        return;
                                    }

                                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                        pass.setError(getString(R.string.input_error_email_invalid));
                                        pass.requestFocus();
                                        return;
                                    }

                                    if (password.isEmpty()) {
                                        pass.setError(getString(R.string.input_error_password));
                                        pass.requestFocus();
                                        return;
                                    }

                                    if (password.length() < 6) {
                                        pass.setError(getString(R.string.input_error_password_length));
                                        pass.requestFocus();
                                        return;
                                    }

                                    if (mobileno.isEmpty()) {
                                        phoneno.setError(getString(R.string.input_error_phone));
                                        phoneno.requestFocus();
                                        return;
                                    }

                                    if (mobileno.length() != 10) {
                                        phoneno.setError(getString(R.string.input_error_phone_invalid));
                                        phoneno.requestFocus();
                                        return;
                                    }


                                    final ProgressDialog mdialog=new ProgressDialog(Registerationactivity.this);
                                    mdialog.setMessage("Uploading Photo...Please Wait...");
                                    mdialog.show();
                                    mAuth.createUserWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {

                                                    if (task.isSuccessful()) {
                                                        Company company = new Company(employeename,companyname,companyaddress,email,password,mobileno,description,taglin,Imageurl);
                                                        FirebaseDatabase.getInstance().getReference("Company")
                                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                .setValue(company).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                mdialog.dismiss();
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(Registerationactivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                                                } else {
                                                                    mdialog.dismiss();
                                                                    Toast.makeText(getApplicationContext(), "Netowrk Error. Please Try Again", Toast.LENGTH_LONG).show();
                                                                }
                                                            }
                                                        });

                                                    } else {
                                                        mdialog.dismiss();
                                                        Toast.makeText(Registerationactivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
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
        else{

                Toast.makeText(Registerationactivity.this, "Please Choose Profile Photo To Register", Toast.LENGTH_LONG).show();


        }


    }

}
