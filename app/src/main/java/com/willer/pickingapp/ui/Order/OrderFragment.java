package com.willer.pickingapp.ui.Order;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.willer.pickingapp.R;
import com.willer.pickingapp.adapter.ProductRecyclerViewAdapter;
import com.willer.pickingapp.data.AppDatabase;
import com.willer.pickingapp.entities.Price;
import com.willer.pickingapp.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductRecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Product> productArrayList;
    private ArrayList<Price> priceArrayList;
    // List all products
    List<Product> productList;
    // List all prizes
    List<Price> priceList;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvProductList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        productArrayList = new ArrayList<>();
        priceArrayList = new ArrayList<>();

        //DatabaseHandler db = new DatabaseHandler(this.getActivity());
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "mayorista").build();

            productList = db.productDao().getAllProducts();
            for (Product product: productList) {
                productArrayList.add(product);
            }

            priceList = db.priceDao().getAllPrices();
            for (Price price : priceList) {
                priceArrayList.add(price);
            }

        });

        recyclerViewAdapter = new ProductRecyclerViewAdapter(this.getActivity(), productArrayList, priceArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

}
