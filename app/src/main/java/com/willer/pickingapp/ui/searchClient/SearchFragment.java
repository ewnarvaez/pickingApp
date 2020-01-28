package com.willer.pickingapp.ui.searchClient;


import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.willer.pickingapp.R;
import com.willer.pickingapp.adapter.RecyclerViewAdapter;
import com.willer.pickingapp.data.DatabaseHandler;
import com.willer.pickingapp.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Client> clientArrayList;
    // List all clients
    List<Client> clientList;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true); // VERY IMPORTANT (TO INFLATE THE MAIN MENU IN THE ACTION BAR)
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvListadoClientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        clientArrayList = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(this.getActivity());

        clientList = db.getAllClients();
        for (Client client: clientList) {
            clientArrayList.add(client);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(this.getActivity(), clientArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        recyclerViewAdapter.getFilter().filter(newText);
        return false;
    }
}
