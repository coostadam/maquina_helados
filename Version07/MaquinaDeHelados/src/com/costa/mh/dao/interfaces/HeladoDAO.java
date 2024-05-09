/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.costa.mh.dao.interfaces;

import com.costa.mh.dao.pojo.Helado;
import java.util.List;

/**
 *
 * @author dev
 */
public interface HeladoDAO {
    
    public List <Helado> getHelados() throws Exception;
    public int updateHelado(Helado h) throws Exception;
    public Helado getHeladoByPosition(String posicion) throws Exception;
    
}
