package com.willer.pickingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.willer.pickingapp.ClientDetailActivity;
import com.willer.pickingapp.R;
import com.willer.pickingapp.data.AppDatabase;
import com.willer.pickingapp.entities.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClientRecyclerViewAdapter extends RecyclerView.Adapter<ClientRecyclerViewAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Client> clientArrayList;

    public ClientRecyclerViewAdapter(Context context, ArrayList<Client> clientList) {
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
        holder.clientName.setText(client.getNombre());
        holder.clientMainPhone.setText(client.getTelefono());
    }

    @Override
    public int getItemCount() {

        return clientArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView clientName;
        private TextView clientMainPhone;

        private ViewHolder(@NonNull View itemView) {
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
            intent.putExtra("id", client.getCodigo());
            intent.putExtra("name", client.getNombre());
            intent.putExtra("dni", client.getCcnit());
            intent.putExtra("city", client.getCodciudad());
            intent.putExtra("mainPhone", client.getTelefono());
            intent.putExtra("status", client.getEstado());
            intent.putExtra("company", client.getEmpresa());
            intent.putExtra("email", client.getEmail());

            // Sending the intent
            context.startActivity(intent);
        }
    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            //DatabaseHandler db = new DatabaseHandler(context.getApplicationContext());
            List<Client> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {

                AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "mayorista")
                        .allowMainThreadQueries().build();
                filteredList = db.clientDao().getAllClients();
            } else {
                for (Client client: clientArrayList) {

                    if (client.getNombre().toLowerCase().contains(charSequence.toString().toLowerCase())) {

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
