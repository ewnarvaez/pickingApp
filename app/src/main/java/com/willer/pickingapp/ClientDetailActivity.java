package com.willer.pickingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClientDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName;
    private TextView tvDNI;
    private TextView tvCity;
    private TextView tvMainPhone;
    private TextView tvSecondaryPhone;
    private TextView tvCompany;
    private TextView tvEmail;
    private Button btnupdate_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);
        Referenciar();
        getSupportActionBar().hide(); // HIDE ACTION BAR
        tvName = findViewById(R.id.tvNombreCliente);
        tvDNI = findViewById(R.id.tvDocumento);
        tvCity = findViewById(R.id.tvCiudad);
        tvMainPhone = findViewById(R.id.tvTel_01);
        tvSecondaryPhone = findViewById(R.id.tvTel_02);
        tvCompany = findViewById(R.id.tvNegocio);
        tvEmail = findViewById(R.id.tvCorreo);
        btnupdate_user = findViewById(R.id.btnupdate_user);

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

    private void Referenciar() {
        btnupdate_user = findViewById(R.id.btnupdate_user);
        btnupdate_user.setOnClickListener((View.OnClickListener) this);

    }
    @Override
    public void onClick (View v){
        switch (v.getId()){

            case R.id.btnupdate_user:
            Toast.makeText( this, "ACTUALIZAR USUARIO", Toast.LENGTH_SHORT).show();
            Intent pasar= new Intent(ClientDetailActivity.this,ClientEditActivity.class);
            startActivity(pasar);
            break;
        }
    }

}
