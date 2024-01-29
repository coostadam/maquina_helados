/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maquinadeheladosv04;

import java.util.Map;
import java.util.*;
import maquinahelados.exceptions.*;

/**
 *
 * @author dev
 */
public class MaquinaHelado {

    private double monedero;
    private double ingresos;
    private Map<String, Helado> h;

    public MaquinaHelado() {
        h = new TreeMap<>();
        h.put("00", new Helado("ChocoBomba", 1.1, "Tarrina"));
        h.put("01", new Helado("FresaGuapi", 0.8, "Palo"));
        h.put("02", new Helado("LimónCanela", 1.5, "Cucurucho"));
        h.put("10", new Helado("FrigLemon", 1.8, "Tarrina"));
        h.put("11", new Helado("PiñaHelada", 1.0, "Palo"));
        h.put("12", new Helado("MoraGuay", 1.2, "Cucurucho"));
        h.put("20", new Helado("ChocoLoco", 1.8, "Tarrina"));
        h.put("21", new Helado("TuttiFrutti", 1.3, "Palo"));
        h.put("22", new Helado("MentaAzul", 1.2, "Cucurucho"));
    }

    public String pedirHelado(String posicion) throws Exception {

        if (!h.containsKey(posicion)) {
            throw new NotValidPositionException("La posición es incorrecta");
        } else if (this.monedero < h.get(posicion).getPrecio()) {
            throw new NotEnoughMoneyException("No hay dinero suficiente");
        } else if (h.get(posicion).getCantidad() > 0) {
            throw new QuantityExceededException("La cantidad de helados es insuficiente");
        } else {
            h.get(posicion).setCantidad(this.h.get(posicion).getCantidad() - 1);
            this.ingresos += h.get(posicion).getPrecio();
            this.monedero = 0.0;
            return this.h.get(posicion).toString();
        }

    }

    public String heladoComprado(String pos) {

        return this.h.get(pos).toString();

    }

    @Override
    public String toString() {

        String vacio = "";
        for (String n : this.h.keySet()) {
            vacio += n + " : " + this.h.get(n).toString() + "\n";
        }

        return vacio;
    }

    public double getMonedero() {
        return monedero;
    }

    public void setMonedero(double monedero) {
        this.monedero = monedero;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public Map<String, Helado> getH() {
        return h;
    }

    public void setH(Map<String, Helado> h) {
        this.h = h;
    }

}
