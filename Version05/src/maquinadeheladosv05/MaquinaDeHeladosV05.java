/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maquinadeheladosv05;

import java.util.Scanner;
import maquinahelados.exceptions.*;

/**
 *
 * @author dev
 */
public class MaquinaDeHeladosV05 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String resp = "";
        MaquinaHelado mh = new MaquinaHelado();
        Utils util = new Utils();
        do {

            System.out.println("'Ver' para ver los helados.");
            System.out.println("'Dinero' para introducir dinero.");
            System.out.println("'Comprar' para comprar un helado.");
            System.out.println("'Apagar' para terminar.");
            System.out.print("¿Qué deseas? --> ");
            resp = sc.nextLine();
            util.setResp(resp);

            do {

                if (util.comprobarMenuIni()) {
                    System.out.println("Texto incorrecto.");
                    System.out.print("Introduce otra vez: ");
                    resp = sc.nextLine();
                    util.setResp(resp);
                }

            } while (util.comprobarMenuIni());

            if (!resp.equalsIgnoreCase("Apagar")) {

                if (resp.equalsIgnoreCase("Ver")) {
                    System.out.println("""
                                       ------------------------------------------------------------------
                                       """);
                    System.out.println(mh.toString());
                    System.out.println("------------------------------------------------------------------");
                } else if (resp.equalsIgnoreCase("Dinero")) {
                    System.out.println("------------------------------------------------------------------");
                    introducirDinero(sc, mh, util);
                } else if (resp.equalsIgnoreCase("Comprar")) {
                    System.out.println("------------------------------------------------------------------");
                    comprarHelado(sc, mh, util);
                }

            }

        } while (!resp.equalsIgnoreCase("Apagar"));
        mh.escribirDatos();
        System.out.println("Se a gastado un total " + mh.getIngresos() + "€");
        
    }

    public static void introducirDinero(Scanner sc, MaquinaHelado mh, Utils util) {
        String resp = "";
        do {

            System.out.println("Acaba de entrar en la sección de introdución de monedas. A continuación, podrá introducir monedas.");
            System.out.println("'1' para introducir: 0,10€");
            System.out.println("'2' para introducir: 0,20€");
            System.out.println("'3' para introducir: 0,50€");
            System.out.println("'4' para introducir: 1,00€");
            System.out.println("'5' para introducir: 2,00€");
            System.out.println("'Volver' retrocederá al menu principal.");
            System.out.print("¿Qué deseas? --> ");
            resp = sc.nextLine();
            util.setResp(resp);

            do {

                if (util.comprobarDinero()) {
                    System.out.println("Texto incorrecto.");
                    System.out.print("Introduce otra vez: ");
                    resp = sc.nextLine();
                    util.setResp(resp);
                }

            } while (util.comprobarDinero());

            if (!resp.equalsIgnoreCase("Volver")) {
                if (resp.equalsIgnoreCase("1")) {
                    mh.setMonedero(mh.getMonedero() + 0.10);
                    System.out.println("Llevas introducido " + mh.getMonedero() + "€");
                } else if (resp.equalsIgnoreCase("2")) {
                    mh.setMonedero(mh.getMonedero() + 0.20);
                    System.out.println("Llevas introducido " + mh.getMonedero() + "€");
                } else if (resp.equalsIgnoreCase("3")) {
                    mh.setMonedero(mh.getMonedero() + 0.50);
                    System.out.println("Llevas introducido " + mh.getMonedero() + "€");
                } else if (resp.equalsIgnoreCase("4")) {
                    mh.setMonedero(mh.getMonedero() + 1);
                    System.out.println("Llevas introducido " + mh.getMonedero() + "€");
                } else {
                    mh.setMonedero(mh.getMonedero() + 2);
                    System.out.println("Llevas introducido " + mh.getMonedero() + "€");
                }
            }
            System.out.println("------------------------------------------------------------------");

        } while (!resp.equalsIgnoreCase("Volver"));

        System.out.println("Has introducido " + mh.getMonedero() + "€");
        System.out.println("------------------------------------------------------------------");
    }

    public static void comprarHelado(Scanner sc, MaquinaHelado mh, Utils util) throws Exception {
        String resp;
        System.out.println("Bienvenido, usted va a realizar una compra. ");
        System.out.print("Introduce la posición del helado que desea: ");
        resp = sc.nextLine();
        try {
            mh.pedirHelado(resp);
            System.out.println("Enhorabuena has comprado el helado " + mh.heladoComprado(resp));
            System.out.println("Aqui tienes tu cambio " + mh.getMonedero() + "€");
        } catch (NotEnoughMoneyException e) {
            System.out.println("No hay dinero suficiente.");
        } catch (NotValidPositionException e) {
            System.out.println("La posición introducida no es válida.");
        } catch (QuantityExceededException e) {
            System.out.println("No hay suficientes helados.");
        } catch (Exception e) {
            System.out.println("Error no esperado.");
        }
    }
}
