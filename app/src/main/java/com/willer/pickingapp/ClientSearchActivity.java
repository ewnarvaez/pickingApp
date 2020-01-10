package com.willer.pickingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.willer.pickingapp.adapter.RecyclerViewAdapter;
import com.willer.pickingapp.data.DatabaseHandler;
import com.willer.pickingapp.model.Client;

import org.w3c.dom.Text;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
