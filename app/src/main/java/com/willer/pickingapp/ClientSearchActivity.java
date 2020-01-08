package com.willer.pickingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.willer.pickingapp.adapter.RecyclerViewAdapter;
import com.willer.pickingapp.data.DatabaseHandler;
import com.willer.pickingapp.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientSearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Client> clientArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_search);

        recyclerView = (RecyclerView) findViewById(R.id.rvListadoClientes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clientArrayList = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(ClientSearchActivity.this);

        // List all clients
        List<Client> clientList = db.getAllClients();
        for (Client client: clientList) {
            clientArrayList.add(client);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(ClientSearchActivity.this, clientArrayList);

        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
