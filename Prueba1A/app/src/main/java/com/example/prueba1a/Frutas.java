package com.example.prueba1a;

import java.io.Serializable;

public class Frutas implements Serializable {

    private String codigo;
    private String fruta;
    private String precio;

    public Frutas(){

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFruta() {
        return fruta;
    }

    public void setFruta(String fruta) {
        this.fruta = fruta;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return fruta;
    }
}
