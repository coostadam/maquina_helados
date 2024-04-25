/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.costa.mh.gui;

import com.costa.mh.exceptions.NotEnoughMoneyException;
import com.costa.mh.exceptions.QuantityExceededException;
import com.costa.mh.exceptions.NotValidPositionException;
import java.util.Scanner;
import com.costa.mh.biz.MaquinaHelado;
import com.costa.mh.exceptions.NotUpdateValue;
import com.costa.mh.utils.Utils;
import com.costa.mh.dao.pojo.Helado;


/**
 *
 * @author dev
 */
public class Exec {

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
                    verHelados(mh);
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
        System.out.println("Se han gastado un total " + mh.getIngresos() + "€");

    }

    public static void verHelados(MaquinaHelado mh) {
        System.out.println(mh.toString());
        System.out.println("Ahora mismo tienes: " + mh.getMonedero() + "€ ");
        System.out.println();
    }

    public static void introducirDinero(Scanner sc, MaquinaHelado mh, Utils util) {
        String resp = "";
        do {

            System.out.println("Acabas de entrar en la sección de introdución de monedas. A continuación, podrás introducir monedas.");
            System.out.println("'A' para introducir: 0,10€");
            System.out.println("'B' para introducir: 0,20€");
            System.out.println("'C' para introducir: 0,50€");
            System.out.println("'D' para introducir: 1,00€");
            System.out.println("'E' para introducir: 2,00€");
            System.out.println("'Volver' para retroceder al menú principal.");
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
                if (resp.equalsIgnoreCase("A")) {
                    mh.setMonedero(mh.getMonedero() + 0.10);
                    System.out.println("Llevas introducido " + mh.getMonedero() + "€");
                } else if (resp.equalsIgnoreCase("B")) {
                    mh.setMonedero(mh.getMonedero() + 0.20);
                    System.out.println("Llevas introducido " + mh.getMonedero() + "€");
                } else if (resp.equalsIgnoreCase("C")) {
                    mh.setMonedero(mh.getMonedero() + 0.50);
                    System.out.println("Llevas introducido " + mh.getMonedero() + "€");
                } else if (resp.equalsIgnoreCase("D")) {
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
        Helado heladoComprado;
        System.out.println("Bienvenido, vas a realizar una compra. ");
        System.out.print("Introduce la posición del helado que deseas: ");
        resp = sc.nextLine();
        try {
            heladoComprado = mh.pedirHelado(resp);
            System.out.println("Enhorabuena has comprado el helado " + heladoComprado);
            System.out.println("Aquí tienes tu cambio " + mh.getMonedero() + "€");

        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (NotValidPositionException | NotUpdateValue | QuantityExceededException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
