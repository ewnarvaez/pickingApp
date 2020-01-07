package com.willer.pickingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.willer.pickingapp.model.Client;
import com.willer.pickingapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Client table creation
        String CREATE_CLIENT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, " + Util.KEY_NAME +
                " TEXT, " + Util.KEY_DNI + " TEXT, " + Util.KEY_CITY + " TEXT, "
                + Util.KEY_MAIN_PN + " TEXT, " + Util.KEY_SECOND_PN + " TEXT, "
                + Util.KEY_COMPANY + " TEXT, " + Util.KEY_EMAIL + " TEXT" + ")";

        db.execSQL(CREATE_CLIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;
        db.execSQL(DROP_TABLE);
        
        // Create table again
        onCreate(db);
    }

    // CRUD OPERATIONS

    // CREATE
    public void addClient(Client client) {

        SQLiteDatabase db = this.getWritableDatabase(); // Allow us use database in writable mode

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, client.getName());
        values.put(Util.KEY_DNI, client.getDni());
        values.put(Util.KEY_CITY, client.getCity());
        values.put(Util.KEY_MAIN_PN, client.getMainPhoneNumber());
        values.put(Util.KEY_SECOND_PN, client.getSecondPhoneNumber());
        values.put(Util.KEY_COMPANY, client.getCompany());
        values.put(Util.KEY_EMAIL, client.getEmail());

        // Insert a row
        db.insert(Util.TABLE_NAME, null, values);
        db.close(); // Closing db connection
    }

    // Get a client - READ
    public Client getClient(int id) {

        SQLiteDatabase db = this.getReadableDatabase(); // Allow us use database in readable mode
        // Creating cursor to move into database rows
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_DNI,
        Util.KEY_CITY, Util.KEY_MAIN_PN, Util.KEY_SECOND_PN, Util.KEY_COMPANY, Util.KEY_EMAIL},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null,
                null, null);

        if (cursor != null) cursor.moveToFirst();

        Client client = new Client();
        client.setId(Integer.parseInt(cursor.getString(0)));
        client.setName(cursor.getString(1));
        client.setDni(cursor.getString(2));
        client.setCity(cursor.getString(3));
        client.setMainPhoneNumber(cursor.getString(4));
        client.setSecondPhoneNumber(cursor.getString(5));
        client.setCompany(cursor.getString(6));
        client.setEmail(cursor.getString(7));

        return client;
    }

    // Get all clients - READ
    public List<Client> getAllClients() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Client> clientList = new ArrayList<>();

        String getAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(getAll, null);

        if (cursor.moveToFirst()) {
            do {

                Client client = new Client();
                client.setId(Integer.parseInt(cursor.getString(0)));
                client.setName(cursor.getString(1));
                client.setDni(cursor.getString(2));
                client.setCity(cursor.getString(3));
                client.setMainPhoneNumber(cursor.getString(4));
                client.setSecondPhoneNumber(cursor.getString(5));
                client.setCompany(cursor.getString(6));
                client.setEmail(cursor.getString(7));

                // Add client object to the list
                clientList.add(client);
            }while (cursor.moveToNext());
        }

        return clientList;
    }

    // UPDATE
    public int updateClient(Client client) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME, client.getName());
        values.put(Util.KEY_DNI, client.getDni());
        values.put(Util.KEY_CITY, client.getCity());
        values.put(Util.KEY_MAIN_PN, client.getMainPhoneNumber());
        values.put(Util.KEY_SECOND_PN, client.getSecondPhoneNumber());
        values.put(Util.KEY_COMPANY, client.getCompany());
        values.put(Util.KEY_EMAIL, client.getEmail());

        return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?", new String[]{String.valueOf(client.getId())});
    }

    // DELETE
    public void deleteClient(Client client){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(client.getId())});
        db.close();
    }

    // Get the total items of our table
    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }
}
