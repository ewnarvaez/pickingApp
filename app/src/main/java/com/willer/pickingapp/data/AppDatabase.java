package com.willer.pickingapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.willer.pickingapp.Interfaces.PriceDao;
import com.willer.pickingapp.Interfaces.ProductDao;
import com.willer.pickingapp.Interfaces.ClientDao;
import com.willer.pickingapp.entities.Client;
import com.willer.pickingapp.entities.Price;
import com.willer.pickingapp.entities.Product;

@Database(entities = {Client.class, Product.class, Price.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract ClientDao clientDao();
    public abstract ProductDao productDao();
    public abstract PriceDao priceDao();

    public static synchronized AppDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
