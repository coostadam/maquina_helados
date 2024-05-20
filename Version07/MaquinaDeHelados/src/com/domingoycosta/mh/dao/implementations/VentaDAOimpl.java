/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.domingoycosta.mh.dao.implementations;

import com.domingoycosta.mh.dao.interfaces.VentaDAO;
import com.domingoycosta.mh.dao.pojo.Venta;
import java.sql.*;
import com.domingoycosta.mh.utils.Configuration;
import java.util.ArrayList;

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

    public VentaDAOimpl() throws Exception {
        con = DriverManager.getConnection(Configuration.URL);
    }

    @Override
    public int insertVenta(Venta v) throws Exception {
        String sentencia = "INSERT INTO venta VALUES(datetime('now'),?,?,?,?,?)";
        int r = 0;
        try (PreparedStatement pstm = con.prepareStatement(sentencia);) {
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
    public ArrayList<Venta> getVenta() throws Exception {
        ArrayList al = new ArrayList();
        Venta v;
        ResultSet rs = null;
        String sql = "select fecha_hora, posicion, nombre, precio, tipo, cantidad from venta";
        try (PreparedStatement stm = con.prepareStatement(sql);) {
            rs = stm.executeQuery();
            while (rs.next()) {
                v = new Venta(rs.getString("fecha_hora"), rs.getString("posicion"), rs.getString("nombre"), rs.getDouble("precio"),
                        rs.getString("tipo"), rs.getInt("cantidad"));
                al.add(v);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return al;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
