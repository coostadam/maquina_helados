/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.costa.mh.dao.implementations;

import com.costa.mh.dao.interfaces.VentaDAO;
import com.costa.mh.dao.pojo.Venta;
import java.sql.*;
import com.costa.mh.utils.Configuration;

/**
 *
 * @author dev
 */
public class VentaDAOimpl implements VentaDAO, AutoCloseable {


    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (ClassNotFoundException ex) {
            System.exit(1);
        } catch (Exception e) {
            System.exit(1);
        }
    }
    
    Connection con = null;
    
    public VentaDAOimpl() throws Exception{
        con = DriverManager.getConnection(Configuration.URL);
    }
    
    @Override
    public int insertVenta(Venta v) throws Exception {
        String sentencia = "INSERT INTO venta VALUES(datetime('now'),?,?,?,?,?)";
        int r = 0;
        try (PreparedStatement pstm = con.prepareStatement(sentencia);
                ) {
            pstm.setString(1, v.getPosicion());
            pstm.setString(2, v.getNombre());
            pstm.setDouble(3, v.getPrecio());
            pstm.setString(4, v.getTipo());
            pstm.setInt(5, v.getCantidad());
            r = pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return r;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }

}
