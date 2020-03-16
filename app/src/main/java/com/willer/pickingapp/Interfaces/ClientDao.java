package com.willer.pickingapp.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.willer.pickingapp.entities.Client;

import java.util.List;

@Dao
public interface ClientDao {

    @Query("SELECT codigo, nombre, apellidos, ccnit, dv, tipodocid," +
            "empresa, regimen, naturaleza, direccion, codciudad, telefono, celular, email, estado FROM " + Client.TABLE_NAME)
    List<Client> getAllClients();

    @Query("SELECT codigo, nombre, apellidos, ccnit, dv, tipodocid," +
            "empresa, regimen, naturaleza, direccion, codciudad, telefono, celular, email, estado " +
            "FROM " + Client.TABLE_NAME + " WHERE " + Client.COLUMN_ID + " = :id")
    Client getClientById(String id);

    @Query("SELECT codigo, nombre, apellidos, ccnit, dv, tipodocid," +
            "empresa, regimen, naturaleza, direccion, codciudad, telefono, celular, email, estado " +
            "FROM " + Client.TABLE_NAME + " WHERE " + Client.COLUMN_FILTER + " = :name")
    Client getClientByName(String name);

    @Query("SELECT COUNT(*) FROM " + Client.TABLE_NAME )
    int getCount();

    @Insert
    void insertAll(Client client);

    @Query("DELETE FROM " + Client.TABLE_NAME + " WHERE " + Client.COLUMN_ID + " = :id")
    int deleteClientById(String id);

    @Update
    int updateClient(Client client);

}
