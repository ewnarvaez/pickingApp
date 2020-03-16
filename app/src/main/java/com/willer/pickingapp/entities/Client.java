package com.willer.pickingapp.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Client.TABLE_NAME)

public class Client {

    public static final String TABLE_NAME = "clientes";
    public static final String COLUMN_ID = "codigo";
    public static final String COLUMN_FILTER = "nombre";

    public Client() {}

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Client.COLUMN_ID)
    private String codigo;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @ColumnInfo(name = "ccnit")
    private String ccnit;

    @ColumnInfo(name = "dv")
    private String dv;

    @ColumnInfo(name = "tipodocid")
    private String tipodocid;

    @ColumnInfo(name = "empresa")
    private String empresa;

    @ColumnInfo(name = "regimen")
    private String regimen;

    @ColumnInfo(name = "naturaleza")
    private String naturaleza;

    @ColumnInfo(name = "direccion")
    private String direccion;

    /*@ColumnInfo(name = "codbarrio")
    private String codbarrio;*/

    @ColumnInfo(name = "codciudad")
    private String codciudad;

    /*
    @ColumnInfo(name = "codpais")
    private String codpais;*/

    @ColumnInfo(name = "telefono")
    private String telefono;

    @ColumnInfo(name = "celular")
    private String celular;

    @ColumnInfo(name = "email")
    private String email;

    /*@ColumnInfo(name = "codactiveco")
    private String codactiveco;

    @ColumnInfo(name = "codtipoterc")
    private String codtipoterc;

    @ColumnInfo(name = "codzona")
    private String codzona;

    @ColumnInfo(name = "contacto")
    private String contacto;

    @ColumnInfo(name = "grancontribuyente")
    private String grancontribuyente;

    @ColumnInfo(name = "entidadestatal")
    private String entidadestatal;

    @ColumnInfo(name = "nodomiciliado")
    private String nodomiciliado;

    @ColumnInfo(name = "autoretenedor")
    private String autoretenedor;

    @ColumnInfo(name = "agenteretrenta")
    private String agenteretrenta;

    @ColumnInfo(name = "agenteretiva")
    private String agenteretiva;*/

    @ColumnInfo(name = "estado")
    private String estado;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCcnit() {
        return ccnit;
    }

    public void setCcnit(String ccnit) {
        this.ccnit = ccnit;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getTipodocid() {
        return tipodocid;
    }

    public void setTipodocid(String tipodocid) {
        this.tipodocid = tipodocid;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /*
    public String getCodbarrio() {
        return codbarrio;
    }

    public void setCodbarrio(String codbarrio) {
        this.codbarrio = codbarrio;
    }

    */
    public String getCodciudad() {
        return codciudad;
    }

    public void setCodciudad(String codciudad) {
        this.codciudad = codciudad;
    }

    /*
    public String getCodpais() {
        return codpais;
    }

    public void setCodpais(String codpais) {
        this.codpais = codpais;
    }*/

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    public String getCodactiveco() {
        return codactiveco;
    }

    public void setCodactiveco(String codactiveco) {
        this.codactiveco = codactiveco;
    }

    public String getCodtipoterc() {
        return codtipoterc;
    }

    public void setCodtipoterc(String codtipoterc) {
        this.codtipoterc = codtipoterc;
    }

    public String getCodzona() {
        return codzona;
    }

    public void setCodzona(String codzona) {
        this.codzona = codzona;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getGrancontribuyente() {
        return grancontribuyente;
    }

    public void setGrancontribuyente(String grancontribuyente) {
        this.grancontribuyente = grancontribuyente;
    }

    public String getEntidadestatal() {
        return entidadestatal;
    }

    public void setEntidadestatal(String entidadestatal) {
        this.entidadestatal = entidadestatal;
    }

    public String getNodomiciliado() {
        return nodomiciliado;
    }

    public void setNodomiciliado(String nodomiciliado) {
        this.nodomiciliado = nodomiciliado;
    }

    public String getAutoretenedor() {
        return autoretenedor;
    }

    public void setAutoretenedor(String autoretenedor) {
        this.autoretenedor = autoretenedor;
    }

    public String getAgenteretrenta() {
        return agenteretrenta;
    }

    public void setAgenteretrenta(String agenteretrenta) {
        this.agenteretrenta = agenteretrenta;
    }

    public String getAgenteretiva() {
        return agenteretiva;
    }

    public void setAgenteretiva(String agenteretiva) {
        this.agenteretiva = agenteretiva;
    }

    */
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
