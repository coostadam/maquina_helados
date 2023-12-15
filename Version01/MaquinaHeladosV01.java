/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maquinadehelados;

import java.util.Scanner;

/**
 *
 * @author dev
 */
public class MaquinaDeHelados {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] tipos = {
            {"tarrina", "palo", "cucurucho"},
            {"tarrina", "palo", "cucurucho"},
            {"tarrina", "palo", "cucurucho"}
        };
        String[][] nombresHelados = {
            {"ChocoBomba", "FresaGuapi", "LimónCanela"},
            {"FrigLemon", "PiñaHelada", "MoraGuay"},
            {"ChocoLoco", "TuttiFrutti", "Mentazul"}
        };
        double[][] precios = {
            {1.1, 0.8, 1.5},
            {1.8, 1, 1.2},
            {1.8, 1.3, 1.2}
        };
        int[][] cantidad = {
            {5, 5, 5},
            {5, 5, 5},
            {5, 5, 5}

        };

        menuInicio(sc, tipos, nombresHelados, precios, cantidad);
    }

    public static void menuInicio(Scanner sc, String[][] tipos, String[][] nombresHelados, double[][] precios, int[][] cantidad) {
        String respUsu = "";
        double monedas = 0, totalGastado = 0, cambio = 0;
        do {

            System.out.println("Pon 'ver' para ver los helados");
            System.out.println("Pon 'dinero' para introducir monedas");
            System.out.println("Pon 'comprar' para comprar helados");
            System.out.println("Pon 'apagar' para terminar.");
            System.out.print("¿Que quieres hacer? ");

            do {
                respUsu = sc.nextLine();
                if (!(respUsu.equalsIgnoreCase("ver") || respUsu.equalsIgnoreCase("dinero")
                        || respUsu.equalsIgnoreCase("comprar") || respUsu.equalsIgnoreCase("apagar"))) {
                    System.out.print("Repuesta incorrecta, introducelo de nuevo: ");
                }
            } while (!(respUsu.equalsIgnoreCase("ver") || respUsu.equalsIgnoreCase("dinero")
                    || respUsu.equalsIgnoreCase("comprar") || respUsu.equalsIgnoreCase("apagar")));

            if (!respUsu.equalsIgnoreCase("exit")) {
                if (respUsu.equalsIgnoreCase("ver")) {
                    System.out.println("---------------------------------------");
                    mostrarHelados(tipos, nombresHelados, precios, cantidad);
                } else if (respUsu.equalsIgnoreCase("dinero")) {
                    System.out.println("---------------------------------------");
                    monedas = meterMonedas(sc);
                    cambio = monedas;
                } else if (respUsu.equalsIgnoreCase("comprar")) {
                    System.out.println("---------------------------------------");
                    totalGastado = comprarHelados(sc, monedas, tipos, nombresHelados, precios, cantidad, totalGastado);
                    cambio = 0;
                    monedas = 0;
                }
            }

        } while (!respUsu.equalsIgnoreCase("apagar"));
        if (totalGastado == 0) {
            System.out.println("No se ha gastado nada en la máquina.");
        } else {
            System.out.println("Se ha gastado en total: " + totalGastado + "€");
        }
        if (cambio == 0) {
            System.out.println("No tienes cambio que recoger.");
        } else {
            System.out.println("El cambio que tienes es: " + cambio + "€");
        }
        System.out.println("Gracias por utilizar la máquina de helados.");
    }

    public static void mostrarHelados(String[][] tipos, String[][] nombresHelados, double[][] precios, int[][] cantidad) {
        for (int i = 0; i < nombresHelados.length; i++) {
            for (int j = 0; j < nombresHelados[i].length; j++) {
                System.out.print(nombresHelados[i][j] + " Posición: " + i + j + " Precio: " + precios[i][j] + "€"
                        + " Tipo: " + tipos[i][j] + " Cantidad restante: " + cantidad[i][j] + "\n");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------");

    }

    public static double meterMonedas(Scanner sc) {
        String resp = "";
        double total = 0;

        do {
            System.out.println("Pon 1 para introducir: 0,10€");
            System.out.println("Pon 2 para introducir: 0,20€");
            System.out.println("Pon 3 para introducir: 0,50€");
            System.out.println("Pon 4 para introducir: 1€");
            System.out.println("Pon 5 para introducir: 2€");
            System.out.println("Pon 'salir' para abandonar");
            System.out.print("¿Que deseas hacer? ");
            do {
                resp = sc.nextLine();
                if (!(resp.equalsIgnoreCase("1") || resp.equalsIgnoreCase("2") || resp.equalsIgnoreCase("3")
                        || resp.equalsIgnoreCase("4") || resp.equalsIgnoreCase("5")
                        || resp.equalsIgnoreCase("salir"))) {
                    System.out.print("Repuesta incorrecta, introducelo de nuevo: ");
                }
            } while (!(resp.equalsIgnoreCase("1") || resp.equalsIgnoreCase("2") || resp.equalsIgnoreCase("3")
                    || resp.equalsIgnoreCase("4") || resp.equalsIgnoreCase("5")
                    || resp.equalsIgnoreCase("salir")));

            if (resp.equals("1")) {
                System.out.println("Has introducido 10 céntimos");
                total += 0.10;
                System.out.println("---------------------------------------");
            } else if (resp.equals("2")) {
                System.out.println("Has introducido 20 céntimos");
                total += 0.20;
                System.out.println("---------------------------------------");
            } else if (resp.equals("3")) {
                System.out.println("Has introducido 50 céntimos");
                total += 0.50;
                System.out.println("---------------------------------------");
            } else if (resp.equals("4")) {
                System.out.println("Has introducido 1 euro");
                total += 1;
                System.out.println("---------------------------------------");
            } else if (resp.equals("5")) {
                System.out.println("Has introducido 2 euros");
                total += 2;
                System.out.println("---------------------------------------");
            } else {
                System.out.println("En total has introducido: " + total + "€");
                System.out.println("---------------------------------------");
            }

        } while (!resp.equalsIgnoreCase("salir"));
        return total;
    }

    public static double comprarHelados(Scanner sc, double monedas, String[][] tipos, String[][] nombresHelados, double[][] precios, int[][] cantidad, double totalGastado) {
        int fila, columna;
        double devolver = 0;
        String rep = "";
        System.out.println("Usted tiene " + monedas + "€");
        do {

            System.out.print("Dime la fila del helado que quieres: ");
            fila = sc.nextInt();
            sc.nextLine();
            System.out.print("Dime la columna del helado que quieres: ");
            columna = sc.nextInt();
            sc.nextLine();
            System.out.println("Has elegido el helado " + nombresHelados[fila][columna]);
            System.out.print("¿Quieres ese helado? (SI/NO) ");
            do {
                rep = sc.nextLine();
                if (!(rep.equalsIgnoreCase("si") || rep.equalsIgnoreCase("no"))) {
                    System.out.print("Repuesta incorrecta vuelve a introducirla: ");
                }
            } while (!(rep.equalsIgnoreCase("si") || rep.equalsIgnoreCase("no")));
        } while (rep.equalsIgnoreCase("no"));

        System.out.println("Comprando...");
        System.out.println("---------------------------------------");
        if (cantidad[fila][columna] <= 0) {
            System.out.println("Lo siento no quedan helados de ese tipo");
            return 0;
        }
        if (monedas >= precios[fila][columna]) {
            devolver = monedas - precios[fila][columna];
            System.out.println("Enhorabuena acabas de comprar un helado sabor " + nombresHelados[fila][columna]
                    + " por un precio de " + precios[fila][columna] + "€");
            totalGastado += precios[fila][columna];
            cantidad[fila][columna] -= 1;

            if (devolver > 0) {
                System.out.println("Aquí tiene sus vueltas " + devolver + "€");
            }
        } else {
            System.out.println("Fondos insuficientes.");
        }

        System.out.println("---------------------------------------");

        return totalGastado;
    }
}
