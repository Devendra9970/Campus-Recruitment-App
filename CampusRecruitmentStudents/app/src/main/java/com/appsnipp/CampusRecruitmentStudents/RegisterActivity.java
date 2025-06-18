package com.appsnipp.CampusRecruitmentStudents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.CampusRecruitmentStudents.ModelClasses.Students;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextView t1;
    EditText name,mobileno,emailt,pass;
    Button submit;
    AwesomeValidation awesomeValidation;
    private FirebaseAuth mAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }

        name=findViewById(R.id.editTextName);
        mobileno=findViewById(R.id.editTextMobile);
        emailt=findViewById(R.id.editTextEmailres);
        pass=findViewById(R.id.editTextPasswordres);
        submit=findViewById(R.id.registerButton);
        t1=findViewById(R.id.signintextview1);

        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("Students");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


        t1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void registerUser() {
        final String Studname = name.getText().toString().trim();
        final String Mobileno = mobileno.getText().toString().trim();
        final String email = emailt.getText().toString().trim();
        final String password = pass.getText().toString().trim();


        if (Studname.isEmpty()) {
            name.setError(getString(R.string.input_error_name));
            name.requestFocus();
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

        if (Mobileno.isEmpty()) {
            mobileno.setError(getString(R.string.input_error_phone));
            mobileno.requestFocus();
            return;
        }

        if (Mobileno.length() != 10) {
            mobileno.setError(getString(R.string.input_error_phone_invalid));
            mobileno.requestFocus();
            return;
        }

        //final int Mobilenonew=Integer.parseInt(Mobileno);

        final ProgressDialog mdialog=new ProgressDialog(RegisterActivity.this);
        mdialog.setMessage("Please Wait");
        mdialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Students students = new Students(Studname,Mobileno,email,password);
                            FirebaseDatabase.getInstance().getReference("Students")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(students).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    mdialog.dismiss();
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
