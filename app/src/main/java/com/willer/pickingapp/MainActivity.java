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

import com.willer.pickingapp.data.DatabaseHandler;
import com.willer.pickingapp.model.Client;

import java.util.List;

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

                if ((etUser.getText().toString().trim()).equals("mmmm") ) {
                    if (etPass.getText().toString().trim().equals("1234")) {
                        intent = new Intent(getApplicationContext(), ClientSearchActivity.class);
                        startActivity(intent);
                    } else {
                        tvErrors.setText("La contraseña no coincide con la registrada en la base de datos");
                    }
                } else {
                    tvErrors.setText("El usuario no existe en la base de datos");
                }
            }
        });

        // Clients creation
        Client duber = new Client();
        duber.setName("Duber Leivys Delgado Burbano"); duber.setDni("102102365");
        duber.setCity("Popayán"); duber.setMainPhoneNumber("8241414");
        duber.setSecondPhoneNumber("3133307332"); duber.setCompany("Papelería N y M");
        duber.setEmail("dubernm@gmail.com");

        Client erwin = new Client();
        erwin.setName("Erwin Meza Vega"); erwin.setDni("102145784");
        erwin.setCity("Bucaramanga"); erwin.setMainPhoneNumber("8372484");
        erwin.setSecondPhoneNumber("3154070506"); erwin.setCompany("Ingenia S.A.S");
        erwin.setEmail("emezav@gmail.com");

        Client felipe = new Client();
        felipe.setName("José Felipe Betancourt"); felipe.setDni("1023256215");
        felipe.setCity("Popayán"); felipe.setMainPhoneNumber("3113617453");
        felipe.setSecondPhoneNumber("3113617453"); felipe.setCompany("Soluciones Innovadoras");
        felipe.setEmail("felipe157@gmail.com");

        Client edwin = new Client();
        edwin.setName("Edwin Willer Narváez Burbano"); edwin.setDni("763256987");
        edwin.setCity("Popayán"); edwin.setMainPhoneNumber("3105160807");
        edwin.setSecondPhoneNumber("3105160807"); edwin.setCompany("Ingenia Soluciones S.A.S");
        edwin.setEmail("ewnarvaez@gmail.com");

        // Clients creation in database
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
//        db.addClient(duber);
//        db.addClient(erwin);
//        db.addClient(felipe);
//        db.addClient(edwin);

        // Update a client
        Client c = db.getClient(4);
//        c.setName("Duber Leivys Delgado Burbano");
//        c.setCity("Popayán");
//        int rowUpdated = db.updateClient(c);

        // Delete a client
        //db.deleteClient(c);


        // List all clients
        List<Client> clientList = db.getAllClients();
        for (Client client: clientList) {
            Log.d("Client List", "onCreate: " + client.getName() + ", " + client.getCity());
        }
    }
}
