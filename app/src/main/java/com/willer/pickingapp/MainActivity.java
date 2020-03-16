package com.willer.pickingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.willer.pickingapp.api.RestApi;
import com.willer.pickingapp.data.AppDatabase;
import com.willer.pickingapp.entities.Price;
import com.willer.pickingapp.model.Auth;
import com.willer.pickingapp.entities.Client;
import com.willer.pickingapp.entities.Product;
import com.willer.pickingapp.util.Util;


import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etUser;
    private EditText etPass;
    private TextView tvErrors;
    Intent intent;
    //private DatabaseHandler db;

    //private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //db = new DatabaseHandler(MainActivity.this);
        Objects.requireNonNull(getSupportActionBar()).hide(); // HIDE ACTION BAR

        etUser = findViewById(R.id.etUserName);
        etPass = findViewById(R.id.etPass);
        tvErrors = findViewById(R.id.tvErrors);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {

            String username = etUser.getText().toString();
            String password = etPass.getText().toString();

            // Validate fields to login
            if (validateLogin(username, password)) {
                // do Login
                doLogin(username, password);
            }
        });
    }

    private boolean validateLogin(String username, String password){
        if (username == null || username.trim().length() == 0) {
            tvErrors.setText("El usuario es obligatorio");
            return false;
        }

        if (password == null || password.trim().length() == 0) {
            tvErrors.setText("La contraseña es obligatoria");
            return false;
        }
        return true;
    }

    private void doLogin(String username, String password) {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mayorista").build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Util.BASE_URL + "auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApi restApi = retrofit.create(RestApi.class);
        Call<Auth> call = restApi.authResponse(username, password);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.isSuccessful()) {
                    Auth auth = response.body();
                    if (auth != null && auth.getMessage().equals("Successfully login.")) {
                        String authHeader = auth.getToken();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Util.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        RestApi restApi = retrofit.create(RestApi.class);
                        Call<List<Product>> callProduct = restApi.getProductList(authHeader);
                        Call<List<Price>> callPrice = restApi.getPriceList(authHeader);
                        Call<List<Client>> callClient = restApi.getClientList(authHeader);
                        //Call to web service for client data
                        callClient.enqueue(new Callback<List<Client>>() {
                            @Override
                            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                                if (response.isSuccessful()) {
                                    List<Client> clientList = response.body();
                                    if (clientList != null) {
                                        Executors.newSingleThreadExecutor().execute(() -> {
                                            for (Client client: clientList) {
                                                db.clientDao().insertAll(client);
                                                //db.addClient(client);
                                            }
                                        });
                                    }
                                    else {
                                        tvErrors.setText("Error en el cuerpo de la solicitud");
                                    }
                                }
                                else {
                                    tvErrors.setText((String.valueOf(response.code())));
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Client>> call, Throwable t) {
                                tvErrors.setText(t.getMessage());
                            }
                        });
                        // Call to web service for products data
                        callProduct.enqueue(new Callback<List<Product>>() {
                            @Override
                            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                                if (response.isSuccessful()) {
                                    List<Product> productList = response.body();
                                    if (productList != null) {
                                        Executors.newSingleThreadExecutor().execute(() -> {
                                            for (Product product: productList) {
                                                db.productDao().insertAll(product);
                                                //db.addClient(client);
                                            }
                                        });
                                    }
                                    else {
                                        tvErrors.setText("Error en el cuerpo de la solicitud");
                                    }
                                }
                                else {
                                    tvErrors.setText((String.valueOf(response.code())));
                                }
                            }
                            @Override
                            public void onFailure(Call<List<Product>> call, Throwable t) {
                                tvErrors.setText(t.getMessage());
                            }
                        });
                        // Call to web service for prizes data
                        callPrice.enqueue(new Callback<List<Price>>() {
                            @Override
                            public void onResponse(Call<List<Price>> call, Response<List<Price>> response) {
                                if (response.isSuccessful()) {
                                    List<Price> priceList = response.body();
                                    if (priceList != null) {
                                        Executors.newSingleThreadExecutor().execute(() -> {
                                            for (Price price : priceList) {
                                                db.priceDao().insertAll(price);
                                                //db.addClient(client);
                                            }
                                        });
                                    }
                                    else {
                                        tvErrors.setText("Error en el cuerpo de la solicitud");
                                    }
                                }
                                else {
                                    tvErrors.setText((String.valueOf(response.code())));
                                }
                            }
                            @Override
                            public void onFailure(Call<List<Price>> call, Throwable t) {
                                tvErrors.setText(t.getMessage());
                            }
                        });

                        intent = new Intent(getApplicationContext(), BottomNavigationActivity.class);
                        startActivity(intent);
                    }
                    else {
                        if (auth == null) throw new AssertionError();
                        tvErrors.setText((auth.getMessage()));
                    }
                }
                else {
                    //tvErrors.setText((String.valueOf(response.code())));
                    tvErrors.setText(response.message());
                }
            }
            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                tvErrors.setText(t.getMessage());
            }
        });
    }
}



// Clients creation
//        ClientDao duber = new ClientDao();
//        duber.setName("Duber Leivys Delgado Burbano"); duber.setDni("102102365");
//        duber.setCity("Popayán"); duber.setMainPhoneNumber("8241414");
//        duber.setSecondPhoneNumber("3133307332"); duber.setCompany("Papelería N y M");
//        duber.setEmail("dubernm@gmail.com");
//        duber.setStatus("Activo");
//
//        ClientDao erwin = new ClientDao();
//        erwin.setName("Erwin Meza Vega"); erwin.setDni("102145784");
//        erwin.setCity("Bucaramanga"); erwin.setMainPhoneNumber("8372484");
//        erwin.setSecondPhoneNumber("3154070506"); erwin.setCompany("Ingenia S.A.S");
//        erwin.setEmail("emezav@gmail.com");
//        erwin.setStatus("Activo");
//
//        ClientDao felipe = new ClientDao();
//        felipe.setName("José Felipe Betancourt"); felipe.setDni("1023256215");
//        felipe.setCity("Popayán"); felipe.setMainPhoneNumber("3113617453");
//        felipe.setSecondPhoneNumber("3113617453"); felipe.setCompany("Soluciones Innovadoras");
//        felipe.setEmail("felipe157@gmail.com");
//        felipe.setStatus("Activo");
//
//        ClientDao edwin = new ClientDao();
//        edwin.setName("Edwin Willer Narváez Burbano"); edwin.setDni("763256987");
//        edwin.setCity("Popayán"); edwin.setMainPhoneNumber("3105160807");
//        edwin.setSecondPhoneNumber("3105160807"); edwin.setCompany("Ingenia Soluciones S.A.S");
//        edwin.setEmail("ewnarvaez@gmail.com");
//        edwin.setStatus("Activo");

// Clients creation in database
//        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
//        db.addClient(duber);
//        db.addClient(erwin);
//        db.addClient(felipe);
//        db.addClient(edwin);

// Update a client
//        ClientDao c = db.getClient(4);
//        c.setName("Duber Leivys Delgado Burbano");
//        c.setCity("Popayán");
//        c.setEmail("leivyspresleyburbanodel@gmail.com");
//        int rowUpdated = db.updateClient(c);

// Delete a client
//        for (int i= 1; i < 17; i++) {
//            ClientDao c = db.getClient(i);
//            db.deleteClient(c);
//        }