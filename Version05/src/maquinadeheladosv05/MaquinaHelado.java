/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maquinadeheladosv05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import maquinahelados.exceptions.*;

/**
 *
 * @author dev
 */
public class MaquinaHelado extends Helado{

    private double monedero;
    private double ingresos;
    private Map<String, Helado> h;

    public MaquinaHelado() {
        h = new TreeMap<>();
        String[] helados;
        try ( Scanner scFile = new Scanner(new File("./Helados.csv"));
                ){
            while(scFile.hasNext()){
               helados = scFile.nextLine().split(",");
               h.put(helados[0], new Helado(helados[1], Double.parseDouble(helados[2]), helados[3], Integer.parseInt(helados[4])));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public String pedirHelado(String posicion) throws Exception {
        if (!h.containsKey(posicion)) {
            throw new NotValidPositionException("La posición es incorrecta");
        } else if (this.monedero < h.get(posicion).getPrecio()) {
            throw new NotEnoughMoneyException("No hay dinero suficiente");
        } else if (h.get(posicion).getCantidad() < 0) {
            throw new QuantityExceededException("La cantidad de helados es insuficiente");
        } else {
            h.get(posicion).setCantidad(this.h.get(posicion).getCantidad() - 1);
            this.ingresos += h.get(posicion).getPrecio();
            this.monedero = this.monedero - h.get(posicion).getPrecio();
            return this.h.get(posicion).toString();
        }
    }

    public String heladoComprado(String pos) {
        return this.h.get(pos).toString();
    }

    public void escribirDatos() throws FileNotFoundException{
        File f = new File("./Helados.csv");
        if ((!f.exists())) {
            throw new FileNotFoundException("El fichero no existe.");
        }else{
            try (FileWriter fw = new FileWriter(f, false);
                   ) {
                for (String key : this.h.keySet()) {
                    fw.write(key + "," + this.h.get(key).getNombre()+ "," + this.h.get(key).getPrecio()+ "," + this.h.get(key).getTipo() + "," + this.h.get(key).getCantidad() + "\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public String toString() {
        String helado = "";
        for (String n : this.h.keySet()) {
            helado += n + " : " + this.h.get(n).toString() + "\n";
        }
        return helado;
    }

     public String toCsv(){
        return this.h.keySet()+ "," + super.getNombre() + "," + super.getPrecio() + "," + super.getTipo() + "," + super.getCantidad();
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
