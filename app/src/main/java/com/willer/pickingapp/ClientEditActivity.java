package com.willer.pickingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.willer.pickingapp.data.DatabaseHandler;
import com.willer.pickingapp.model.Client;

public class ClientEditActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvName;
    private TextView tvDNI;
    private EditText etCity;
    private EditText etMainPhone;
    private Spinner spStatus;
    private EditText etCompany;
    private EditText etEmail;

    private Button btnCancel;
    private Button btnUpdate;
    private Bundle bundle;

    private static final String[] Status = {"Activo", "Inactivo"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_edit);
        getSupportActionBar().hide();

        tvName = findViewById(R.id.tvNombreCliente);
        tvDNI = findViewById(R.id.tvDocumento);
        etCity = findViewById(R.id.etCiudad);
        etMainPhone = findViewById(R.id.etTel01);
        spStatus = findViewById(R.id.spStatus);
        etCompany = findViewById(R.id.etNegocio);
        etEmail = findViewById(R.id.etCorreo);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ClientEditActivity.this, android.R.layout.simple_spinner_dropdown_item, Status);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(adapter);

        bundle = getIntent().getExtras(); // Getting extras from intent (RecyclerViewAdapter)
        // Show info in the view
        tvName.setText(bundle.getString("name"));
        tvDNI.setText(bundle.getString("dni"));
        etCity.setText(bundle.getString("city"));
        etMainPhone.setText(bundle.getString("mainPhone"));

//        Código para mostrar el valor que tiene actualmente el campo estado
        if ((bundle.getString("status").equals("Activo"))) {
            spStatus.setSelection(0);
        } else {
            spStatus.setSelection(1);
        }
        
        etCompany.setText(bundle.getString("company"));
        etEmail.setText(bundle.getString("email"));
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setEnabled(true);

        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                super.onBackPressed();
                break;

            case R.id.btnUpdate:
                Client client = new Client();
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                client = db.getClient(bundle.getInt("id"));
                client.setCity(etCity.getText().toString());
                client.setMainPhoneNumber(etMainPhone.getText().toString());
                client.setStatus(spStatus.getSelectedItem().toString());
                client.setCompany(etCompany.getText().toString());
                client.setEmail(etEmail.getText().toString());

                // Client update action
                db.updateClient(client);

                Intent intent = new Intent(getApplicationContext(), ClientDetailActivity.class);
                intent.putExtra("id", client.getId());
                intent.putExtra("name", client.getName());
                intent.putExtra("dni", client.getDni());
                intent.putExtra("city", client.getCity());
                intent.putExtra("mainPhone", client.getMainPhoneNumber());
                intent.putExtra("status", client.getStatus());
                intent.putExtra("company", client.getCompany());
                intent.putExtra("email", client.getEmail());

                // Sending the intent
                startActivity(intent);
                break;
        }
    }
}
