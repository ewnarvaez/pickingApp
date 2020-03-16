package com.willer.pickingapp.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Product.TABLE_NAME)

public class Product {

    public static final String TABLE_NAME = "productos";
    public static final String COLUMN_ID = "codigo";
    public static final String COLUMN_FILTER = "descripcion";

    public Product() {
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Product.COLUMN_ID)
    private String codigo;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    /*
    @ColumnInfo(name = "codgrupo")
    private String codgrupo;

    @ColumnInfo(name = "codproveedor")
    private String codproveedor;
     */

    @ColumnInfo(name = "unidades")
    private String unidades;

    /*
    @ColumnInfo(name = "codigobarras")
    private String codigobarras;

    @ColumnInfo(name = "referencia")
    private String referencia;

    @ColumnInfo(name = "peso")
    private String peso;

    @ColumnInfo(name = "codigoexterno")
    private String codigoexterno;

    @ColumnInfo(name = "ventascredito")
    private String ventascredito;

    @ColumnInfo(name = "permitedescuento")
    private String permitedescuento;

    @ColumnInfo(name = "servicio")
    private String servicio;

    @ColumnInfo(name = "esflete")
    private String esflete;

     */
    @NonNull
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(@NonNull String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }
}
