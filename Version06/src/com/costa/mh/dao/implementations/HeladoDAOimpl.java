/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.costa.mh.dao.implementations;

import com.costa.mh.dao.interfaces.HeladoDAO;
import com.costa.mh.dao.pojo.Helado;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.costa.mh.utils.Configuration;

/**
 *
 * @author dev
 */
public class HeladoDAOimpl implements HeladoDAO, AutoCloseable {

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

    public HeladoDAOimpl() throws Exception {
        con = DriverManager.getConnection(Configuration.URL);
    }

    @Override
    public List<Helado> getHelados() throws Exception {
        List<Helado> he = new ArrayList<>();
        String sentencia = "select posicion, nombre, precio, tipo, cantidad  from helado;";
        try (PreparedStatement pstm = con.prepareStatement(sentencia); ResultSet rs = pstm.executeQuery();) {
            while (rs.next()) {
                he.add(new Helado(rs.getString("posicion"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("tipo"),
                        rs.getInt("cantidad")));
            }
        } catch (Exception e) {
            throw e;
        }
        return he;
    }
    
    @Override
    public int updateHelado(Helado h) throws Exception {
        String sentencia = "UPDATE helado SET cantidad = ?, nombre = ?, tipo = ?, precio = ? WHERE posicion = ?";
        int r = 0;
        try (PreparedStatement pstm = con.prepareStatement(sentencia);) {
            pstm.setInt(1, h.getCantidad());
            pstm.setString(2, h.getNombre());
            pstm.setString(3, h.getTipo());
            pstm.setDouble(4, h.getPrecio());
            pstm.setString(5, h.getPosicion());
            r = pstm.executeUpdate();
        }  catch (Exception e) {
            throw e;
        }
        return r;
    }

    @Override
    public Helado getHeladoByPosition(String posicion) throws Exception {
        Helado he = null;
        String sentencia = "select posicion, nombre, precio, tipo, cantidad from helado where posicion = ?";
        ResultSet rs = null;
        try (PreparedStatement pstm = con.prepareStatement(sentencia);) {
            pstm.setString(1, posicion);
            rs = pstm.executeQuery();
            if (rs.next()) {
                he = new Helado(rs.getString("posicion"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("tipo"),
                        rs.getInt("cantidad"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return he;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
