/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.domingoycosta.mh.dao.interfaces;

import com.domingoycosta.mh.dao.pojo.Venta;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
public interface VentaDAO {

    public int insertVenta(Venta v) throws Exception;
    public ArrayList<Venta> getVenta() throws Exception;
    
}
