package com.willer.pickingapp.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.willer.pickingapp.Interfaces.PriceDao;
import com.willer.pickingapp.Interfaces.ProductDao;
import com.willer.pickingapp.Interfaces.ClientDao;
import com.willer.pickingapp.entities.Client;
import com.willer.pickingapp.entities.Price;
import com.willer.pickingapp.entities.Product;

@Database(entities = {Client.class, Product.class, Price.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ClientDao clientDao();
    public abstract ProductDao productDao();
    public abstract PriceDao priceDao();

}
