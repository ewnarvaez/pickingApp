package com.willer.pickingapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.willer.pickingapp.Interfaces.ClientDao;
import com.willer.pickingapp.data.AppDatabase;
import com.willer.pickingapp.entities.Client;

import java.util.List;

public class AppRepository {

    private ClientDao clientDao;
    private LiveData<List<Client>> allClients;

    public AppRepository(Application application) {

        AppDatabase database = AppDatabase.getInstance(application);
        clientDao = database.clientDao();
        allClients = clientDao.getAllClients();
    }
}
