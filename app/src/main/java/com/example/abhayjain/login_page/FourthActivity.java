package com.example.abhayjain.login_page;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Abhay Jain on 17-03-2018.
 */

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout4 );

        TextView t1=(TextView)findViewById( R.id.text6 );
        t1.setPaintFlags(t1.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);


    }

    public void login(View view){
        Intent i=new Intent( FourthActivity.this,MainActivity.class );
        startActivity( i );
    }
}
