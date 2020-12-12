package com.example.logreg;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class loggedin extends AppCompatActivity {

    Button buttonLogout;
    TextView textViewFullName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        DataBaseHelper dbhelper = new DataBaseHelper(loggedin.this);
        Cursor datas = dbhelper.getFullName();
        String login = dbhelper.getName();
        StringBuffer stringBuffer = new StringBuffer();

        if(datas.getCount() == 0)
        {
            stringBuffer.append("Üdvözöllek " + dbhelper.felhasznalo.getUsername());
        }

        while(datas.moveToNext())
        {
            stringBuffer.append("Üdvözöllek: "+datas.getString(0));
        }
        textViewFullName.setText(stringBuffer.toString());

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(loggedin.this,MainActivity.class);
                startActivity(logout);
                finish();
            }
        });
    }


    public void init()
    {
        buttonLogout = findViewById(R.id.buttonLogout);
        textViewFullName = findViewById(R.id.textViewFullName);
    }
}
