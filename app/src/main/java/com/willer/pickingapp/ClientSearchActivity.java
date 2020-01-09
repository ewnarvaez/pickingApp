package com.willer.pickingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.willer.pickingapp.adapter.RecyclerViewAdapter;
import com.willer.pickingapp.data.DatabaseHandler;
import com.willer.pickingapp.model.Client;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ClientSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Client> clientArrayList;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_search);

        recyclerView = (RecyclerView) findViewById(R.id.rvListadoClientes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clientArrayList = new ArrayList<>();

        searchView = findViewById(R.id.svBuscar);
        searchView.setQueryHint("Buscar Cliente");
        searchView.setOnQueryTextListener(this);

        DatabaseHandler db = new DatabaseHandler(ClientSearchActivity.this);

        // List all clients
        List<Client> clientList = db.getAllClients();
        for (Client client: clientList) {
            clientArrayList.add(client);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(ClientSearchActivity.this, clientArrayList);

        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String text = newText;
        return false;
    }
}
