/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.domingoycosta.mh.dao.pojo;


/**
 *
 * @author dev
 */
public class Helado {
    private String posicion;
    private String nombre;
    private double precio;
    private String tipo;
    private int cantidad;

    public Helado(String posicion, String nombre, double precio, String tipo, int cantidad) {
        this.posicion = posicion;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public Helado(){
    }

    @Override
    public String toString() {
        return posicion + " :: " + nombre + " :: " + precio + "€ :: " + tipo + " :: " + cantidad + " unidades";
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
}
