package com.appsnipp.CampusRecruitmentStudents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    TextView t1,t2;
    EditText e1,e2;
    Button b1;
    FirebaseAuth firebaseAuth;
    FirebaseUser stud;
    String userid;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1=(EditText)findViewById(R.id.editTextEmail);
        e2=(EditText)findViewById(R.id.editTextPassword);
        b1=(Button)findViewById(R.id.cirLoginButton);
        t1=(TextView)findViewById(R.id.signuptextview1);
       // t2=(TextView)findViewById(R.id.textView11);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference ref=database.getReference("Students");



        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                stud= firebaseAuth.getCurrentUser();

                if (stud != null) {
                    Toast.makeText(LoginActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    // Intent I = new Intent(login_act.this, MainActivity.class);
                    //startActivity(I);
                } else {
                    Toast.makeText(LoginActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mdialog=new ProgressDialog(LoginActivity.this);
                mdialog.setMessage("Please Wait");
                mdialog.show();
                String userEmail = e1.getText().toString();
                String userPaswd = e2.getText().toString();
                if (userEmail.isEmpty()) {
                    e1.setError("Provide your Email first!");
                    e1.requestFocus();
                } else if (userPaswd.isEmpty()) {
                    e2.setError("Enter your Password!");
                    e2.requestFocus();
                } else if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            mdialog.dismiss();
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        t1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
