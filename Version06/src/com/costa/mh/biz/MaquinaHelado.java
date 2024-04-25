/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.costa.mh.biz;

import com.costa.mh.dao.implementations.HeladoDAOimpl;
import com.costa.mh.dao.implementations.VentaDAOimpl;
import com.costa.mh.exceptions.NotEnoughMoneyException;
import com.costa.mh.exceptions.QuantityExceededException;
import com.costa.mh.exceptions.NotValidPositionException;
import com.costa.mh.dao.pojo.Helado;
import com.costa.mh.dao.pojo.Venta;
import com.costa.mh.utils.Utils;
import java.util.List;

/**
 *
 * @author dev
 */
public class MaquinaHelado extends Helado {

    private double monedero;
    private double ingresos;
    private final Utils util = new Utils();

    public Helado pedirHelado(String posicion) throws Exception {
        Helado h;
        Venta v;
        try (HeladoDAOimpl heladoImpl = new HeladoDAOimpl(); VentaDAOimpl ventaImpl = new VentaDAOimpl();) {
            h = heladoImpl.getHeladoByPosition(posicion);
            if (h == null) {
                throw new NotValidPositionException("La posición es incorrecta");
            } else if (this.monedero < h.getPrecio()) {
                throw new NotEnoughMoneyException("No hay dinero suficiente");
            } else if (h.getCantidad() <= 0) {
                throw new QuantityExceededException("La cantidad de helados es insuficiente");
            } else {
                this.ingresos = this.ingresos + h.getPrecio();
                this.monedero = this.monedero - h.getPrecio();
                this.ingresos = this.util.redondeoDosDecimales(this.ingresos);
                this.monedero = this.util.redondeoDosDecimales(this.monedero);
                h.setCantidad(h.getCantidad() - 1);
                heladoImpl.updateHelado(h);
                v = new Venta(posicion, h.getNombre(), h.getPrecio(), h.getTipo());
                ventaImpl.insertVenta(v);
            }
        } catch (Exception e) {
            throw e;
        }
        return h;
    }

    @Override
    public String toString() {
        String s = "";
        List<Helado> helados;
        try (HeladoDAOimpl he = new HeladoDAOimpl()) {
            helados = he.getHelados();
            for (Helado value : helados) {
                s += value.toString() + "\n";
            }
        } catch (Exception e) {
        }
        return s;
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
}
