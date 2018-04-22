package com.example.abhayjain.login_page;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by Abhay Jain on 18-03-2018.
 */

public class FifthActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout5 );

       firebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            super.onCreateOptionsMenu( menu );
            MenuInflater inflater = getMenuInflater();
            inflater.inflate( R.menu.menu, menu );
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            super.onOptionsItemSelected( item );
          /*  switch (item.getItemId()) {
                case R.id.menuLogout:

                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity( new Intent( FifthActivity.this, MainActivity.class ) );
                    break;
            }
            return true;*/

          if ((item.getItemId())==R.id.menuLogout){
              user_logout();
          }
          return true;
        }
        private void user_logout(){
            FirebaseAuth.getInstance().signOut();
            startActivity( new Intent( this,MainActivity.class ) );
             finish();
           // Log.d( Tag, "User_logout:User signout" );

        }


    }

