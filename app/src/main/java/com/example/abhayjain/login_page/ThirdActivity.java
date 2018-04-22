package com.example.abhayjain.login_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Abhay Jain on 14-03-2018.
 */

public class ThirdActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private Button buttonLogout;


    private DatabaseReference databaseReference;
    private EditText editTextName,editTextAddress;
    private Button buttonSave;

    private UserInformation userInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.layout3 );

        firebaseAuth=FirebaseAuth.getInstance(  );
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity( new Intent( this,MainActivity.class ) );
        }

        databaseReference= FirebaseDatabase.getInstance().getReference();
        editTextName =(EditText)findViewById( R.id.edit1111 );
        editTextAddress=(EditText)findViewById( R.id.edit2222 );
        buttonSave=(Button)findViewById( R.id.btn1111 );

        FirebaseUser user=firebaseAuth.getCurrentUser();

        textViewUserEmail=(TextView)findViewById( R.id.txt11 );
        textViewUserEmail.setText( "Welcome   "+user.getEmail() );

        buttonLogout=(Button)findViewById( R.id.btn11 );

        buttonLogout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==buttonLogout){
                    firebaseAuth.signOut();
                    finish();
                    startActivity( new Intent( ThirdActivity.this,MainActivity.class ) );
                }
            }
        } );

    buttonSave.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v==buttonSave){
                UserInformation();
            }

        }
    } );

    }

    private void UserInformation(){
        String name=editTextName.getText().toString().trim();
        String address=editTextAddress.getText().toString().trim();

        UserInformation userInformation  = new UserInformation( name, address );
        FirebaseUser user=firebaseAuth.getCurrentUser();

        databaseReference.child( user.getUid() ).setValue( userInformation );
        Toast.makeText( this,"Information Saved...." ,Toast.LENGTH_LONG).show();
    }



}
