package io.VideoClub.View;

import com.sun.glass.ui.SystemClipboard;
import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Product;
import java.util.Scanner;
import java.util.Set;

public class GUI {
    static AppController Controller = new AppController();
    
    public static void main(String[] args) {
        logo();
        principal();
    }
    public static void logo(){
        System.out.println(" _______            _           _______            _       _______                     ");
        System.out.println("|   _   |          | |         |   _   |       ___| |___  |   _   |                   ");
        System.out.println("|  |_|  |          | |         |  |_|  |      |___   ___|_|  |_|  |                     ");
        System.out.println("|  _____|_____ ____| |___ _____|_____ /_   _ _____| |  ___|   ___/                    ");
        System.out.println("| |     |  _  |  __|  _  |  _  |  _   | | | |  ___| | |___|  /____                        ");
        System.out.println("| |     | |_| | |__| | | | |_| | |_|  | |_| | |___| |  ___|  ___  |                    ");
        System.out.println("| |     |_____|____|_| |_|_____|______|_____|___  | | |___|_|   | |                     ");
        System.out.println("|_|                            |__________________|_|_______|   |_|                     ");
    }
    
    public static void principal() {
        
        int numero;

        do {
            Scanner teclado = new Scanner(System.in);

            System.out.println("\n------Menu------");
            System.out.println("1) Iniciar sesion");
            System.out.println("2) Registrarse");
            System.out.println("3) Información.");
            System.out.println("4) Empleados");
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
            
            case 4:
                MenuEmpleados();
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
            if (usuario.equals(io.VideoClub.Model.Client.getNombre)) {
                result = true;
            }

            if (result = true) {
                lista_sesion();
                
            }
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
    
    //Edu intenta usar este devolverString que es mas sencillo
    /**
     * Metodo que escribe y devuelve un string que introduzca un usuario
     * @param texto Texto que quieres que se escribe, ejemplo (Introduce contraseña)
     * @return string que introduce el usuario
     */
    public static String devolverString(String texto){
        String resultado = "";
        Scanner teclado = new Scanner(System.in);

        System.out.print(texto);
        resultado = teclado.next();

        return resultado;
    }
    
    public static int devolverInt(String texto){
        int resultado = 0;
        Scanner teclado = new Scanner(System.in);

        System.out.print(texto);
        resultado = teclado.nextInt();

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
    
    public static void InicioEmpleados(){
        System.out.println("\n------Inicio Sesion Empleado------");
        String usuario = devolverString("Introduzca su Usuario: ");
        String contraseña = devolverString("Introduzca su Contraseña: ");
        //Buscar por usuario y por contraseña con un if
    }
    
    public static void MenuEmpleados(){
        int opcion = 0;
        do{
            System.out.println("\n------Menu Empleado------");
            System.out.println("1) Listar peliculas");
            System.out.println("2) Añadir peliculas");
            System.out.println("3) Quitar peliculas");
            System.out.println("4) Reservas");
            System.out.println("0) Salir");
            System.out.println("------------------");

            opcion = devolverInt("Introduce una opcion: ");

            switch(opcion){
                case 1:
                    Set<Product> ListaProductos = Controller.products.listAllProducts();
                    for(Product producto: ListaProductos){
                        System.out.println(producto);
                    }
                    break;

                case 2:
                    String name = devolverString("Introduce nombre del producto para agregar");
                    if(Controller.products.addProduct(name)){
                        System.out.println("Producto agregado exitosamente");
                    }else{
                        System.out.println("El producto no se ha podido agregar");
                    }
                    break;

                case 3:
                    
                    break;

                case 4:
                    
                    break;
            }
        
        }while(opcion!=0);
    }
}
