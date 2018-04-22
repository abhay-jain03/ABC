package com.example.abhayjain.login_page;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

/**
 * Created by Abhay Jain on 14-03-2018.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1=null;
    EditText email=null;
    EditText password=null;


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout2 );

        TextView t1=(TextView)findViewById( R.id.text4 );
        t1.setPaintFlags( t1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG );


        firebaseAuth=FirebaseAuth.getInstance();


        email=(EditText) findViewById( R.id.e1 );
        password=(EditText)findViewById( R.id.e2 );
        b1=(Button)findViewById( R.id.btn1 );


        progressDialog = new ProgressDialog( this );
        b1.setOnClickListener( this );
    }



    private void registerUser(){
        String Email=email.getText().toString().trim();
        String Password=password.getText().toString().trim();

        if(TextUtils.isEmpty( Email )){
            Toast.makeText( this,"Please enter email",Toast.LENGTH_SHORT ).show();
            return;
        }

        if(TextUtils.isEmpty( Password )){
            Toast.makeText( this,"Please enter password",Toast.LENGTH_SHORT ).show();
            return;
        }

         progressDialog.setMessage("Register User....");
        progressDialog.show(  );

       firebaseAuth.createUserWithEmailAndPassword( Email,Password )
               .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       progressDialog.dismiss();
                       if (task.isSuccessful()){
                           Toast.makeText( SecondActivity.this,"Register Sucessfully",Toast.LENGTH_SHORT ).show();
                       }else {
                           if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                               Toast.makeText( SecondActivity.this,"You are already registered",Toast.LENGTH_SHORT ).show();
                           }else {
                             Toast.makeText( SecondActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT ).show();
                               Toast.makeText( SecondActivity.this,"Could not register. Please try again",Toast.LENGTH_SHORT ).show();
                       }}
                   }
               } );



    }





    public void Login(View view){
        Intent i=new Intent( SecondActivity.this,MainActivity.class );
        startActivity( i );
    }

    @Override
    public void onClick(View v) {
        if(v==b1){
            registerUser();
        }
    }
}
