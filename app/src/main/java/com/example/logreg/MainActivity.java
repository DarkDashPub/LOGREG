package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonLogin,buttonRegister;
    EditText editTextUsername,editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegiserActivity = new Intent(MainActivity.this,Regisztracio.class);
                startActivity(toRegiserActivity);
                finish();
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dbhelper = new DataBaseHelper(MainActivity.this);
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if(username.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Felhasználónév vagy E-mail és jelszó szükséges!",Toast.LENGTH_SHORT).show();
                }
                else
                    {

                        if(dbhelper.Login(username,password))
                        {
                            Intent loggedin = new Intent(MainActivity.this, loggedin.class);
                            startActivity(loggedin);
                            finish();
                        }
                        else
                            {
                                Toast.makeText(MainActivity.this,"Helytelen felhasználónév vagy jelszó!",Toast.LENGTH_SHORT).show();
                            }

                    }


            }
        });

    }


    public void init()
    {
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        editTextUsername = findViewById(R.id.editText_LoginUsername);
        editTextPassword = findViewById(R.id.editTextTextPassword);
    }
}