package com.willer.pickingapp.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.willer.pickingapp.entities.Price;

import java.util.List;

@Dao
public interface PriceDao {

    @Query("SELECT codigo, precio01, precio02, precio03, precio04 FROM " + Price.TABLE_NAME)
    List<Price> getAllPrices();

    @Query("SELECT codigo, precio01, precio02, precio03, precio04 FROM " + Price.TABLE_NAME +
    " WHERE " + Price.COLUMN_ID + " = :id")
    Price getPriceById(String id);

    @Query("SELECT COUNT(*) FROM " + Price.TABLE_NAME )
    int getCount();

    @Insert
    void insertAll(Price price);

    @Query("DELETE FROM " + Price.TABLE_NAME + " WHERE " + Price.COLUMN_ID + " = :id")
    int deletePriceById(String id);

    @Update
    int updatePrice(Price price);
}
