package io.VideoClub.View;

import com.sun.glass.ui.SystemClipboard;
import java.util.Scanner;

public class GUI {

    public static void main(String[] args) {
        principal();
    }

    public static void principal() {
        int numero;

        do {
            Scanner teclado = new Scanner(System.in);

            System.out.println("\n------Menu------");
            System.out.println("1) Iniciar sesion");
            System.out.println("2) Registrarse");
            System.out.println("3) Información.");
            System.out.println("0) Salir");
            System.out.println("------------------");

            System.out.println("Introduce una opción");
            numero = teclado.nextInt();
            opciones_principal(numero);
        } while (numero != 0);
    }

    static void opciones_principal(int numero) {
        switch (numero) {
            case 1: //Caso 1 para 
                Iniciar_sesion();
                break;

            case 2: //Caso 2 para 
                registrarse();
                break;

            case 3: //Caso 3 para 
                System.out.println("Bienvenido al nuestro videoclub xd ....");
                break;
        }
    }

    public static boolean Iniciar_sesion() {
        boolean result = false;
        String usuario;
        String contraseña;
        Scanner teclado = new Scanner(System.in);

        System.out.println("\n------Inicio de sesión------");
        System.out.println(" Introduce tu usuario");
        usuario = teclado.next();
        System.out.println(" Introduce tu Contraseña");
        contraseña = teclado.next();
        if (usuario != null && contraseña != null) {
            /*if (usuario.equals(io.VideoClub.Model.Client.getNombre)) {
            result = true;
            }
            
            if (result = true) {
            lista_sesion();
            }*/

        }
        System.out.println("0) Salir");
        System.out.println("----------------");

        return result;
    }

    public String devolverString() {
        String resultado = "";
        Scanner teclado = new Scanner(System.in);

        System.out.println("introduce usuario");
        resultado = teclado.next();

        if (resultado != null) {

        }

        return resultado;

    }

    public static void lista_sesion() {
        System.out.println("\n------Menu Pesonal------");
        System.out.println("1) Listar peliculas");
        System.out.println("2) Cuenta");
        System.out.println("3) Reservar.");
        System.out.println("4) Cancelar reserva");
        System.out.println("0) Salir");
        System.out.println("------------------");

    }

    public static void registrarse() {

    }
}
