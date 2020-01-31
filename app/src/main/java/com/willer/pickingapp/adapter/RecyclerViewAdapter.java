package com.willer.pickingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.willer.pickingapp.ClientDetailActivity;
import com.willer.pickingapp.R;
import com.willer.pickingapp.data.DatabaseHandler;
import com.willer.pickingapp.model.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Client> clientArrayList;

    public RecyclerViewAdapter(Context context, List<Client> clientList) {
        this.context = context;
        this.clientArrayList = clientList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_data, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Client client = clientArrayList.get(position);
        holder.clientName.setText(client.getName());
        holder.clientMainPhone.setText(client.getMainPhoneNumber());
    }

    @Override
    public int getItemCount() {

        return clientArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView clientName;
        public TextView clientMainPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            clientName = itemView.findViewById(R.id.clientName);
            clientMainPhone = itemView.findViewById(R.id.clientMainPhone);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition(); // Function that get the item position in the List
            Client client = clientArrayList.get(position); // Get client object
            // Intent creation
            Intent intent = new Intent(context, ClientDetailActivity.class);
            intent.putExtra("id", client.getId());
            intent.putExtra("name", client.getName());
            intent.putExtra("dni", client.getDni());
            intent.putExtra("city", client.getCity());
            intent.putExtra("mainPhone", client.getMainPhoneNumber());
            intent.putExtra("secondPhone", client.getSecondPhoneNumber());
            intent.putExtra("company", client.getCompany());
            intent.putExtra("email", client.getEmail());
            intent.putExtra("status", client.getStatus());

            // Sending the intent
            context.startActivity(intent);
        }
    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            DatabaseHandler db = new DatabaseHandler(context.getApplicationContext());
            List<Client> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredList = db.getAllClients();
            } else {
                for (Client client: clientArrayList) {

                    if (client.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {

                        filteredList.add(client);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            clientArrayList.clear();
            clientArrayList.addAll((Collection<? extends Client>) results.values);
            notifyDataSetChanged();
        }
    };
}
