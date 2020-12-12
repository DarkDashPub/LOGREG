package com.example.logreg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Regisztracio extends AppCompatActivity {

    Button buttonRegister,buttonBack;
    EditText editTextRegEmail,editTextRegUsername,editTextRegPassword,editTextRegFullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextRegEmail.getText().toString();
                String username = editTextRegUsername.getText().toString();
                String password = editTextRegPassword.getText().toString();
                String fullname = editTextRegFullname.getText().toString();

                if(email.isEmpty() || username.isEmpty() || password.isEmpty() || fullname.isEmpty())
                {
                    Toast.makeText(Regisztracio.this,"ÜRES MEZŐ!",Toast.LENGTH_SHORT).show();
                    return;
                }
                dataRecord(email,username,password,fullname);
                Intent back = new Intent(Regisztracio.this,MainActivity.class);
                startActivity(back);
                finish();

            }
        });
    }

    public void dataRecord(String email,String username, String password, String fullname)
    {
        DataBaseHelper dbhelper = new DataBaseHelper(Regisztracio.this);
        if(dbhelper.dataRecord(email,username,password,fullname))
        {
            Toast.makeText(this,"Új felhasználó sikeresen létrehozva!",Toast.LENGTH_SHORT).show();
        }
        else
            {
                Toast.makeText(this,"Hiba!",Toast.LENGTH_SHORT).show();
            }
    }

    public void init()
    {
        buttonRegister = findViewById(R.id.buttonRegistrationrActivityRegister);
        buttonBack = findViewById(R.id.buttonBack);
        editTextRegEmail = findViewById(R.id.editTextRegistrationEmail);
        editTextRegUsername = findViewById(R.id.editTextRegistrationUsername);
        editTextRegPassword = findViewById(R.id.editTextRegistrationPassword);
        editTextRegFullname = findViewById(R.id.editTextRegistrationFullName);
    }
}
