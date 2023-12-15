/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maquinadehelados;

/**
 *
 * @author dev
 */
public class MaquinaHelado {

    private double monedero;
    private double ingresos;
    private Helado[][] helados = new Helado[3][3];

    public MaquinaHelado() {
        this.helados[0][0] = new Helado("ChocoBomba", 1.1, "Tarrina");
        this.helados[0][1] = new Helado("FresaGuapi", 0.8, "Palo");
        this.helados[0][2] = new Helado("LimónCanela", 1.5, "Cucurucho");
        this.helados[1][0] = new Helado("FrigLemon", 1.8, "Tarrina");
        this.helados[1][1] = new Helado("PiñaHelada", 1.0, "Palo");
        this.helados[1][2] = new Helado("MoraGuay", 1.2, "Cucurucho");
        this.helados[2][0] = new Helado("ChocoLoco", 1.8, "Tarrina");
        this.helados[2][1] = new Helado("TuttiFrutti", 1.3, "Palo");
        this.helados[2][2] = new Helado("MentaAzul", 1.2, "Cucurucho");
    }

    public Helado pedirHelado(String posicion) {
        
        int i = Character.getNumericValue(posicion.charAt(0));
        int j = Character.getNumericValue(posicion.charAt(1));
        
        if (this.helados[i][j].getCantidad() > 0 && this.helados[i][j].getPrecio() <= this.monedero) {
            this.helados[i][j].setCantidad(this.helados[i][j].getCantidad() - 1);
            this.ingresos += this.helados[i][j].getPrecio();
            this.monedero = 0.0;
            return this.helados[i][j];
        } else {
            return null;
        }
    }

     public Helado hayHelado(String posicion) {
        
        int i = Character.getNumericValue(posicion.charAt(0));
        int j = Character.getNumericValue(posicion.charAt(1));
        
        if (this.helados[i][j].getCantidad() > 0 && this.helados[i][j].getPrecio() <= this.monedero) {
            return this.helados[i][j];
        } else {
            return null;
        }
    }
     
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.helados.length; i++) {
            for (int j = 0; j < this.helados[i].length; j++) {
                str += "\n" + this.helados[i][j].toString();
            }
            str += "\n";
        }

        return str;
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

    public Helado[][] getHelados() {
        return helados;
    }

    public void setHelados(Helado[][] helados) {
        this.helados = helados;
    }

}
