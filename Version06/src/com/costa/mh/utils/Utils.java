/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.costa.mh.utils;


/**
 *
 * @author dev
 */
public class Utils {
    private String resp;
    public boolean comprobarHelado(String resp) {
        if (!(this.resp.equalsIgnoreCase("00") || this.resp.equalsIgnoreCase("01") || this.resp.equalsIgnoreCase("02")
                || this.resp.equalsIgnoreCase("10") || this.resp.equalsIgnoreCase("11") || this.resp.equalsIgnoreCase("12")
                 || this.resp.equalsIgnoreCase("20") || this.resp.equalsIgnoreCase("21") || this.resp.equalsIgnoreCase("22")
                    || this.resp.equalsIgnoreCase("Volver"))) {
            return true;
        }
        return false;
    }

    public boolean comprobarDinero() {
        if (!(this.resp.equalsIgnoreCase("A") || this.resp.equalsIgnoreCase("B") || this.resp.equalsIgnoreCase("C")
                || this.resp.equalsIgnoreCase("D") || this.resp.equalsIgnoreCase("E") || this.resp.equalsIgnoreCase("Volver"))) {
            return true;
        }
        return false;
    }

    public boolean comprobarMenuIni() {
        if (!(this.resp.equalsIgnoreCase("Ver") || this.resp.equalsIgnoreCase("Dinero") || this.resp.equalsIgnoreCase("Comprar")
                || this.resp.equalsIgnoreCase("Apagar"))) {
            return true;
        }
        return false;
    }
    
    public double redondeoDosDecimales(double d) {
        return 1.0d * Math.round(d * 100.0d) / 100.0d;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }
}
