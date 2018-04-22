package com.example.abhayjain.login_page;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class MainActivity extends AppCompatActivity {

    private Button b3;
    private EditText email;
    private EditText password;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        TextView t1 = (TextView) findViewById( R.id.text1 );
        t1.setPaintFlags( t1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG );

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity( new Intent( getApplicationContext(), ThirdActivity.class ) );

        }


        progressDialog = new ProgressDialog( this );

        b3 = (Button) findViewById( R.id.Button1 );
        email = (EditText) findViewById( R.id.edit1 );
        password = (EditText) findViewById( R.id.edit2 );




        b3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == b3) {
                    LoginUser();
                }
            }
        } );
    }

    private void LoginUser() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();


        if (TextUtils.isEmpty( Email )) {
            //email is empty
            Toast.makeText( this, "Please enter email", Toast.LENGTH_SHORT ).show();
            //stopping the function executing further
            return;
        }
        if (TextUtils.isEmpty( Password )) {
            //password empty
            Toast.makeText( this, "enter password", Toast.LENGTH_SHORT ).show();
            return;
        }

        progressDialog.setMessage( "Register User...." );
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword( Email, Password )
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            startActivity( new Intent( getApplicationContext(), ThirdActivity.class ) );
                        } else {
                            Toast.makeText( MainActivity.this, "Incorrect login details", Toast.LENGTH_SHORT ).show();
                        }

                    }
                } );
    }


    public void forgot(View view) {
        Intent i = new Intent( MainActivity.this, FourthActivity.class );
        startActivity( i );
    }


    public void sum(View v) {

        Intent i = new Intent( MainActivity.this, SecondActivity.class );
        startActivity( i );
    }
}
