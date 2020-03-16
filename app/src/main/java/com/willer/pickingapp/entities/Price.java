package com.willer.pickingapp.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Price.TABLE_NAME)

public class Price {

    public static final String TABLE_NAME = "precios";
    public static final String COLUMN_ID = "codigo";

    public Price() {
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Price.COLUMN_ID)
    private String codigo;

    @ColumnInfo(name = "precio01")
    private String precio01;

    @ColumnInfo(name = "precio02")
    private String precio02;

    @ColumnInfo(name = "precio03")
    private String precio03;

    @ColumnInfo(name = "precio04")
    private String precio04;

    @NonNull
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(@NonNull String codigo) {
        this.codigo = codigo;
    }

    public String getPrecio01() {
        return precio01;
    }

    public void setPrecio01(String precio01) {
        this.precio01 = precio01;
    }

    public String getPrecio02() {
        return precio02;
    }

    public void setPrecio02(String precio02) {
        this.precio02 = precio02;
    }

    public String getPrecio03() {
        return precio03;
    }

    public void setPrecio03(String precio03) {
        this.precio03 = precio03;
    }

    public String getPrecio04() {
        return precio04;
    }

    public void setPrecio04(String precio04) {
        this.precio04 = precio04;
    }
}
