package com.willer.pickingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etUser, etPass;
    private Button btnLogin;
    private TextView tvErrors;
    Intent intent;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (EditText) findViewById(R.id.etUserName);
        etPass = (EditText) findViewById(R.id.etPass);
        tvErrors = (TextView) findViewById(R.id.tvErrors);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((etUser.getText().toString().trim()).equals("mayorista") ) {
                    if (etPass.getText().toString().trim() == "mayorista") {
                        intent = new Intent(getApplicationContext(), ClientDetailActivity.class);
                        startActivity(intent);
                    } else {
                        tvErrors.setText("La contraseña no coincide con la registrada en la base de datos");
                    }
                } else {
                    tvErrors.setText("El usuario no existe en la base de datos");
                }
            }
        });
    }
}
