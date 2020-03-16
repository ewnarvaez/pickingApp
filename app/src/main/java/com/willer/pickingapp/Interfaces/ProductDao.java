package com.willer.pickingapp.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.willer.pickingapp.entities.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT codigo, descripcion, unidades FROM " + Product.TABLE_NAME)
    List<Product> getAllProducts();

    @Query("SELECT codigo, descripcion, unidades FROM " +  Product.TABLE_NAME +
            " WHERE " + Product.COLUMN_ID + " = :id")
    Product getProductById(String id);

    @Query("SELECT codigo, descripcion, unidades FROM " + Product.TABLE_NAME +
            " WHERE " + Product.COLUMN_FILTER + " = :name")
    Product getProductByName(String name);

    @Query("SELECT COUNT(*) FROM " + Product.TABLE_NAME )
    int getCount();

    @Insert
    void insertAll(Product product);

    @Query("DELETE FROM " + Product.TABLE_NAME + " WHERE " + Product.COLUMN_ID + " = :id")
    int deleteProductById(String id);

    @Update
    int updateProduct(Product product);
}
