package com.willer.pickingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.willer.pickingapp.ProductDetailActivity;
import com.willer.pickingapp.R;
import com.willer.pickingapp.entities.Price;
import com.willer.pickingapp.entities.Product;

import java.util.ArrayList;


public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> productArrayList;
    private ArrayList<Price> priceArrayList;

    public ProductRecyclerViewAdapter(Context context, ArrayList<Product> productList, ArrayList<Price> priceList) {

        this.context = context;
        this.productArrayList = productList;
        this.priceArrayList = priceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_data, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product product = productArrayList.get(position);
        Price price = priceArrayList.get(position);

        holder.productDescription.setText(product.getDescripcion());
        holder.productPrice.setText(price.getPrecio01());
        holder.productUnity.setText(product.getUnidades());
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView productDescription;
        private TextView productPrice;
        private TextView productUnity;

        private ViewHolder(@NonNull View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice01);
            productUnity = itemView.findViewById(R.id.productUnit);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition(); // Function that get the item position in the List
            Product product = productArrayList.get(position); // Get product object
            Price price = priceArrayList.get(position); // Get product object

            // Intent creation
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("id", product.getCodigo());
            intent.putExtra("descripcion", product.getDescripcion());
            intent.putExtra("unidades", product.getUnidades());
            intent.putExtra("price", price.getPrecio01());

            // Sending the intent
            context.startActivity(intent);
        }
    }
}
