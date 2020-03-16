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

        // ClientDao table creation
        String CREATE_CLIENT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_CODIGO + " TEXT PRIMARY KEY, " + Util.KEY_NOMBRE +
                " TEXT, " + Util.KEY_APELLIDOS + " TEXT, " + Util.KEY_CCNIT + " TEXT, "
                + Util.KEY_DV + " TEXT, " + Util.KEY_TIPODOCID + " TEXT, "
                + Util.KEY_EMPRESA + " TEXT, " + Util.KEY_REGIMEN + " TEXT, "
                + Util.KEY_NATURALEZA + " TEXT, " + Util.KEY_DIRECCION + " TEXT, "
                + Util.KEY_CODBARRIO + " TEXT, " + Util.KEY_CODCIUDAD + " TEXT, "
                + Util.KEY_CODPAIS + " TEXT, " + Util.KEY_TELEFONO + " TEXT, "
                + Util.KEY_CELULAR + " TEXT, " + Util.KEY_EMAIL + " TEXT, "
                + Util.KEY_CODACTIVECO + " TEXT, " + Util.KEY_CODTIPOTERC + " TEXT, "
                + Util.KEY_CODZONA + " TEXT, " + Util.KEY_CONTACTO + " TEXT, "
                + Util.KEY_GRANCONTRIBUYENTE + " TEXT, " + Util.KEY_ENTIDADESTATAL + " TEXT, "
                + Util.KEY_NODOMICILIADO + " TEXT, " + Util.KEY_AUTORETENEDOR + " TEXT, "
                + Util.KEY_AGENTERETRENTA + " TEXT, " + Util.KEY_AGENTERETIVA + " TEXT, "
                + Util.KEY_ESTADO +
                ")";

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
        values.put(Util.KEY_CODIGO, client.getCodigo());
        values.put(Util.KEY_NOMBRE, client.getNombre());
        values.put(Util.KEY_APELLIDOS, client.getApellidos());
        values.put(Util.KEY_CCNIT, client.getCcnit());
        values.put(Util.KEY_DV, client.getDv());
        values.put(Util.KEY_TIPODOCID, client.getTipodocid());
        values.put(Util.KEY_EMPRESA, client.getEmpresa());
        values.put(Util.KEY_REGIMEN, client.getRegimen());
        values.put(Util.KEY_NATURALEZA, client.getNaturaleza());
        values.put(Util.KEY_DIRECCION, client.getDireccion());
        values.put(Util.KEY_CODBARRIO, client.getCodbarrio());
        values.put(Util.KEY_CODCIUDAD, client.getCodciudad());
        values.put(Util.KEY_CODPAIS, client.getCodpais());
        values.put(Util.KEY_TELEFONO, client.getTelefono());
        values.put(Util.KEY_CELULAR, client.getCelular());
        values.put(Util.KEY_EMAIL, client.getEmail());
        values.put(Util.KEY_CODACTIVECO, client.getCodactiveco());
        values.put(Util.KEY_CODTIPOTERC, client.getCodtipoterc());
        values.put(Util.KEY_CODZONA, client.getCodzona());
        values.put(Util.KEY_CONTACTO, client.getContacto());
        values.put(Util.KEY_GRANCONTRIBUYENTE, client.getGrancontribuyente());
        values.put(Util.KEY_ENTIDADESTATAL, client.getEntidadestatal());
        values.put(Util.KEY_NODOMICILIADO, client.getNodomiciliado());
        values.put(Util.KEY_AUTORETENEDOR, client.getAutoretenedor());
        values.put(Util.KEY_AGENTERETRENTA, client.getAgenteretrenta());
        values.put(Util.KEY_AGENTERETIVA, client.getAgenteretiva());
        values.put(Util.KEY_ESTADO, client.getEstado());

        // Insert a row
        db.insert(Util.TABLE_NAME, null, values);
        db.close(); // Closing db connection
    }

    // Get a client - READ
    public Client getClient(String codigo) {

        SQLiteDatabase db = this.getReadableDatabase(); // Allow us use database in readable mode
        // Creating cursor to move into database rows
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_CODIGO, Util.KEY_NOMBRE, Util.KEY_APELLIDOS,
        Util.KEY_CCNIT, Util.KEY_DV, Util.KEY_TIPODOCID, Util.KEY_EMPRESA, Util.KEY_REGIMEN, Util.KEY_NATURALEZA,
                        Util.KEY_DIRECCION, Util.KEY_CODBARRIO, Util.KEY_CODCIUDAD, Util.KEY_CODPAIS, Util.KEY_TELEFONO,
                        Util.KEY_CELULAR, Util.KEY_EMAIL, Util.KEY_CODACTIVECO, Util.KEY_CODTIPOTERC, Util.KEY_CODZONA,
                        Util.KEY_CONTACTO, Util.KEY_GRANCONTRIBUYENTE, Util.KEY_ENTIDADESTATAL, Util.KEY_NODOMICILIADO,
                        Util.KEY_AUTORETENEDOR, Util.KEY_AGENTERETRENTA, Util.KEY_AGENTERETIVA, Util.KEY_ESTADO
                },
                Util.KEY_CODIGO + "=?", new String[]{codigo}, null, null,
                null, null);

        if (cursor != null) cursor.moveToFirst();

        Client client = new Client();
        client.setCodigo((cursor.getString(0)));
        client.setNombre(cursor.getString(1));
        client.setApellidos(cursor.getString(2));
        client.setCcnit(cursor.getString(3));
        client.setDv(cursor.getString(4));
        client.setTipodocid(cursor.getString(5));
        client.setEmpresa(cursor.getString(6));
        client.setRegimen(cursor.getString(7));
        client.setNaturaleza(cursor.getString(8));
        client.setDireccion(cursor.getString(9));
        client.setCodbarrio(cursor.getString(10));
        client.setCodciudad(cursor.getString(11));
        client.setCodpais(cursor.getString(12));
        client.setTelefono(cursor.getString(13));
        client.setCelular(cursor.getString(14));
        client.setEmail(cursor.getString(15));
        client.setCodactiveco(cursor.getString(16));
        client.setCodtipoterc(cursor.getString(17));
        client.setCodzona(cursor.getString(18));
        client.setContacto(cursor.getString(19));
        client.setGrancontribuyente(cursor.getString(20));
        client.setEntidadestatal(cursor.getString(21));
        client.setNodomiciliado(cursor.getString(22));
        client.setAutoretenedor(cursor.getString(23));
        client.setAgenteretrenta(cursor.getString(24));
        client.setAgenteretiva(cursor.getString(25));
        client.setEstado(cursor.getString(26));

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
                client.setCodigo((cursor.getString(0)));
                client.setNombre(cursor.getString(1));
                client.setApellidos(cursor.getString(2));
                client.setCcnit(cursor.getString(3));
                client.setDv(cursor.getString(4));
                client.setTipodocid(cursor.getString(5));
                client.setEmpresa(cursor.getString(6));
                client.setRegimen(cursor.getString(7));
                client.setNaturaleza(cursor.getString(8));
                client.setDireccion(cursor.getString(9));
                client.setCodbarrio(cursor.getString(10));
                client.setCodciudad(cursor.getString(11));
                client.setCodpais(cursor.getString(12));
                client.setTelefono(cursor.getString(13));
                client.setCelular(cursor.getString(14));
                client.setEmail(cursor.getString(15));
                client.setCodactiveco(cursor.getString(16));
                client.setCodtipoterc(cursor.getString(17));
                client.setCodzona(cursor.getString(18));
                client.setContacto(cursor.getString(19));
                client.setGrancontribuyente(cursor.getString(20));
                client.setEntidadestatal(cursor.getString(21));
                client.setNodomiciliado(cursor.getString(22));
                client.setAutoretenedor(cursor.getString(23));
                client.setAgenteretrenta(cursor.getString(24));
                client.setAgenteretiva(cursor.getString(25));
                client.setEstado(cursor.getString(26));

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

        values.put(Util.KEY_CODIGO, client.getCodigo());
        values.put(Util.KEY_NOMBRE, client.getNombre());
        values.put(Util.KEY_APELLIDOS, client.getApellidos());
        values.put(Util.KEY_CCNIT, client.getCcnit());
        values.put(Util.KEY_DV, client.getDv());
        values.put(Util.KEY_TIPODOCID, client.getTipodocid());
        values.put(Util.KEY_EMPRESA, client.getEmpresa());
        values.put(Util.KEY_REGIMEN, client.getRegimen());
        values.put(Util.KEY_NATURALEZA, client.getNaturaleza());
        values.put(Util.KEY_DIRECCION, client.getDireccion());
        values.put(Util.KEY_CODBARRIO, client.getCodbarrio());
        values.put(Util.KEY_CODCIUDAD, client.getCodciudad());
        values.put(Util.KEY_CODPAIS, client.getCodpais());
        values.put(Util.KEY_TELEFONO, client.getTelefono());
        values.put(Util.KEY_CELULAR, client.getCelular());
        values.put(Util.KEY_EMAIL, client.getEmail());
        values.put(Util.KEY_CODACTIVECO, client.getCodactiveco());
        values.put(Util.KEY_CODTIPOTERC, client.getCodtipoterc());
        values.put(Util.KEY_CODZONA, client.getCodzona());
        values.put(Util.KEY_CONTACTO, client.getContacto());
        values.put(Util.KEY_GRANCONTRIBUYENTE, client.getGrancontribuyente());
        values.put(Util.KEY_ENTIDADESTATAL, client.getEntidadestatal());
        values.put(Util.KEY_NODOMICILIADO, client.getNodomiciliado());
        values.put(Util.KEY_AUTORETENEDOR, client.getAutoretenedor());
        values.put(Util.KEY_AGENTERETRENTA, client.getAgenteretrenta());
        values.put(Util.KEY_AGENTERETIVA, client.getAgenteretiva());
        values.put(Util.KEY_ESTADO, client.getEstado());

        return db.update(Util.TABLE_NAME, values, Util.KEY_CODIGO + "=?", new String[]{String.valueOf(client.getCodigo())});
    }

    // DELETE
    public void deleteClient(Client client){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.KEY_CODIGO + "=?",
                new String[]{(client.getCodigo())});
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
