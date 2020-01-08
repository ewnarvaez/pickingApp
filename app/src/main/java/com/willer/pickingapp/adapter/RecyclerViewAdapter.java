package com.willer.pickingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.willer.pickingapp.ClientDetailActivity;
import com.willer.pickingapp.R;
import com.willer.pickingapp.model.Client;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Client> clientList;

    public RecyclerViewAdapter(Context context, List<Client> clientList) {
        this.context = context;
        this.clientList = clientList;
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

        Client client = clientList.get(position);
        holder.clientName.setText(client.getName());
        holder.clientMainPhone.setText(client.getMainPhoneNumber());
    }

    @Override
    public int getItemCount() {

        return clientList.size();
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

            int position = getAdapterPosition(); // Function that get the item position in the arrayList
            Client client = clientList.get(position); // Get every one client object
            // Intent creation
            Intent intent = new Intent(context, ClientDetailActivity.class);
            intent.putExtra("name", client.getName());
            intent.putExtra("dni", client.getDni());
            intent.putExtra("city", client.getCity());
            intent.putExtra("mainPhone", client.getMainPhoneNumber());
            intent.putExtra("secondPhone", client.getSecondPhoneNumber());
            intent.putExtra("company", client.getCompany());
            intent.putExtra("email", client.getEmail());

            // Sending the intent
            context.startActivity(intent);
        }
    }
}
