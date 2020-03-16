package com.willer.pickingapp.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.willer.pickingapp.entities.Client;
import com.willer.pickingapp.Interfaces.ClientDao;

public class ClientViewModel extends ViewModel {

    public LiveData<PagedList<Client>> clientList;
    public MutableLiveData<String> filterTextAll = new MutableLiveData<>();

    public void initAllClients(ClientDao clientDao) {

        PagedList.Config config = (new PagedList.Config.Builder()).setPageSize(10).build();
        clientList = Transformations.switchMap(filterTextAll, input -> {

            return null;
        });
    }
}
