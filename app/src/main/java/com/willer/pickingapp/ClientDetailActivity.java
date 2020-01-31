package com.willer.pickingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.willer.pickingapp.model.Client;

public class ClientDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName;
    private TextView tvDNI;
    private TextView tvCity;
    private TextView tvMainPhone;
    private TextView tvSecondaryPhone;
    private TextView tvCompany;
    private TextView tvEmail;
    private TextView tvStatus;
    private Button btnUpdateUser;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);
        Referenciar();
        getSupportActionBar().hide(); // HIDE ACTION BAR

        bundle = getIntent().getExtras(); // Getting extras from intent (RecyclerViewAdapter)
        // Show info in the view
        tvName.setText(bundle.getString("name"));
        tvDNI.setText(bundle.getString("dni"));
        tvCity.setText(bundle.getString("city"));
        tvMainPhone.setText(bundle.getString("mainPhone"));
        tvSecondaryPhone.setText(bundle.getString("secondPhone"));
        tvCompany.setText(bundle.getString("company"));
        tvEmail.setText(bundle.getString("email"));

        btnUpdateUser.setOnClickListener(this);

    }

    @Override
    public void onClick (View v){
        switch (v.getId()){

            case R.id.btnUpdateUser:
                Intent intent= new Intent(ClientDetailActivity.this, ClientEditActivity.class);
                intent.putExtra("id", bundle.getString("id"));
                intent.putExtra("name", bundle.getString("name"));
                intent.putExtra("dni", bundle.getString("dni"));
                intent.putExtra("city", bundle.getString("city"));
                intent.putExtra("mainPhone", bundle.getString("mainPhone"));
                intent.putExtra("secondPhone", bundle.getString("secondPhone"));
                intent.putExtra("company", bundle.getString("company"));
                intent.putExtra("email", bundle.getString("email"));
                startActivity(intent);
                break;

            case R.id.btnOrder:
                break;
        }
    }

    private void Referenciar() {
        tvName = findViewById(R.id.tvNombreCliente);
        tvDNI = findViewById(R.id.tvDocumento);
        tvCity = findViewById(R.id.tvCiudad);
        tvMainPhone = findViewById(R.id.tvTel_01);
        tvSecondaryPhone = findViewById(R.id.tvTel_02);
        tvCompany = findViewById(R.id.tvNegocio);
        tvEmail = findViewById(R.id.tvCorreo);
        btnUpdateUser = findViewById(R.id.btnUpdateUser);
    }

}
