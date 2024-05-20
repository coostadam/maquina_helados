/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.domingoycosta.mh.biz;

import com.domingoycosta.mh.dao.implementations.HeladoDAOimpl;
import com.domingoycosta.mh.dao.implementations.VentaDAOimpl;
import com.domingoycosta.mh.exceptions.NotEnoughMoneyException;
import com.domingoycosta.mh.exceptions.QuantityExceededException;
import com.domingoycosta.mh.exceptions.NotValidPositionException;
import com.domingoycosta.mh.dao.pojo.Helado;
import com.domingoycosta.mh.dao.pojo.Venta;
import com.domingoycosta.mh.utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;

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
                throw new NotValidPositionException("La posición es incorrecta.");
            } else if (this.monedero < h.getPrecio()) {
                throw new NotEnoughMoneyException("No hay dinero suficiente.");
            } else if (h.getCantidad() <= 0) {
                throw new QuantityExceededException("La cantidad de helados es insuficiente.");
            } else {
                this.ingresos = this.ingresos + h.getPrecio();
                this.monedero = this.monedero - h.getPrecio();
                this.ingresos = this.util.redondeoDosDecimales(this.ingresos);
                this.monedero = this.util.redondeoDosDecimales(this.monedero);
                h.setCantidad(h.getCantidad() - 1);
                heladoImpl.updateHelado(h);
                v = new Venta(LocalDate.now().toString(), posicion, h.getNombre(), h.getPrecio(), h.getTipo(), 1);
                ventaImpl.insertVenta(v);
            }
        } catch (Exception e) {
            throw e;
        }
        return h;
    }

    public ArrayList<Helado> heladoToList() {
        ArrayList<Helado> helados = null;
        try (HeladoDAOimpl h = new HeladoDAOimpl()) {
            helados = h.getHelados();
        } catch (Exception e) {
        }
        return helados;
    }

    public ArrayList<Venta> ventaToList() {
        ArrayList<Venta> ventas = null;
        try (VentaDAOimpl v = new VentaDAOimpl()) {
            ventas = v.getVenta();
        } catch (Exception e) {
        }
        return ventas;
    }

    public double getMonedero() {
        return monedero;
    }

    public void setMonedero(double monedero) {
        this.monedero = util.redondeoDosDecimales(monedero);
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }
}
