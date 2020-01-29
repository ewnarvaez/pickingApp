package com.willer.pickingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ClientEditActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvDNI;
    private TextView tvCity;
    private TextView tvMainPhone;
    private TextView tvSecondaryPhone;
    private TextView tvCompany;
    private TextView tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_edit);

        tvName = findViewById(R.id.tvNombreCliente);
        tvDNI = findViewById(R.id.tvDocumento);
        tvCity = findViewById(R.id.tvCiudad);
        tvMainPhone = findViewById(R.id.tvTel_01);
        tvSecondaryPhone = findViewById(R.id.tvTel_02);
        tvCompany = findViewById(R.id.tvNegocio);
        tvEmail = findViewById(R.id.tvCorreo);

        Bundle bundle = getIntent().getExtras(); // Getting extras from intent (RecyclerViewAdapter)
        // Show info in the view
        tvName.setText(bundle.getString("name"));
        tvDNI.setText(bundle.getString("dni"));
        tvCity.setText(bundle.getString("city"));
        tvMainPhone.setText(bundle.getString("mainPhone"));
        tvSecondaryPhone.setText(bundle.getString("secondPhone"));
        tvCompany.setText(bundle.getString("company"));
        tvEmail.setText(bundle.getString("email"));
    }

}
