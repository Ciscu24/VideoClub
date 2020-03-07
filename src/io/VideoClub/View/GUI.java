package io.VideoClub.View;

import com.sun.glass.ui.SystemClipboard;
import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Product;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class GUI {
    static AppController Controller = new AppController();

    public static void main(String[] args) {
        logo();
        Controller.cargaBBDD();
        MenuEmpleados();
    }

    public static void logo() {
        System.out.println(" _______            _           _______            _       _______                     ");
        System.out.println("|   _   |          | |         |   _   |       ___| |___  |   _   |                   ");
        System.out.println("|  |_|  |          | |         |  |_|  |      |___   ___|_|  |_|  |                     ");
        System.out.println("|  _____|_____ ____| |___ _____|_____ /_   _ _____| |  ___|   ___/                    ");
        System.out.println("| |     |  _  |  __|  _  |  _  |  _   | | | |  ___| | |___|  /____                        ");
        System.out.println("| |     | |_| | |__| | | | |_| | |_|  | |_| | |___| |  ___|  ___  |                    ");
        System.out.println("| |     |_____|____|_| |_|_____|______|_____|___  | | |___|_|   | |                     ");
        System.out.println("|_|                            |__________________|_|_______|   |_|                     ");
        System.out.println("");
    }

    public static void principal() {

        int numero;

        do {
            Scanner teclado = new Scanner(System.in);
            System.out.println("\n|-------------------|");
            System.out.println("|        Menu       |");
            System.out.println("|-------------------|");
            System.out.println("| 1) Iniciar sesion |");
            System.out.println("| 2) Registrarse    |");
            System.out.println("| 3) Información.   |");
            System.out.println("| 4) Empleados      |");
            System.out.println("| 0) Salir          |");
            System.out.println("--------------------|");

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
                System.out.println("Bienvenido al  Videoclub 'PochoBuster' ....");
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

        System.out.println("\n|-------------------|");
        System.out.println("|   Iniciar Sesion  |");
        System.out.println("|-------------------|");
        System.out.println(" Introduce tu usuario");
        usuario = teclado.next();
        System.out.println(" Introduce tu Contraseña");
        contraseña = teclado.next();
        if (usuario != null && contraseña != null) {
            /*if (usuario.equals(Controller.clients.getNombre())) {
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

    public static void lista_sesion() {
        int opcion = 0;
        do {
            System.out.println("\n|---------------------|");
            System.out.println("|    Menu principal   |");
            System.out.println("|---------------------|");
            System.out.println("| 1) Listar           |");
            System.out.println("| 2) Cuenta           |");
            System.out.println("| 3) Reservar.        |");
            System.out.println("| 4) Cancelar reserva |");
            System.out.println("| 0) Salir            |");
            System.out.println("|---------------------|");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    menu_lista();

                    break;

                case 2:
                    String Usuario=devolverString("Nombre de usuario: ");
                    //Controller.clients.searchUser(Usuario);

                    break;

                case 3:

                    break;

                case 4:

                    break;
            }
        } while (opcion != 0);

    }

    public static void registrarse() {
        System.out.println("\n|-------------------|");
        System.out.println("|    Registrarse    |");
        System.out.println("|-------------------|");
        String correo = devolverString("Introduzca un usuario: ");
        String contraseña = devolverString("Introdce una contraseña: ");
        if (correo != null && contraseña != null) {
            String nombre = devolverString("Introduce tu nombre: ");
            String usuario = devolverString("Introduce tu usuario: ");
            String telefono = devolverString("Introduce tu teléfono: ");
        }

    }

    public static void InicioEmpleados() {
        System.out.println("\n|-------------------------------|");
        System.out.println("|    Inicio sesion empleados    |");
        System.out.println("|-------------------------------|");
        String usuario = devolverString("Introduzca su Usuario: ");
        String contrasena = devolverString("Introduzca su Contraseña: ");
        //Buscar por usuario y por contraseña con un if
        /*if(Controller.clients.searchUser(usuario) && Controller.clients.searchpassword(contrasena)){
            MenuEmpleados();
        }else{
            System.out.println("Usuario o Contraseña incorrecto");
        }*/
    }

    public static void MenuEmpleados() {
        int opcion = 0;
        do {
            System.out.println("\n|---------------------|");
            System.out.println("|     Menu empleado   |");
            System.out.println("|---------------------|");
            System.out.println("| 1) Listar Productos |");
            System.out.println("| 2) Añadir Producto  |");
            System.out.println("| 3) Quitar Producto  |");
            System.out.println("| 4) Editar Productos |");
            System.out.println("| 5) Disponibilidad   |");
            System.out.println("| 6) Reservas         |");
            System.out.println("| 0) Salir            |");
            System.out.println("|---------------------|");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 0:
                    System.out.println("Guardando base de datos");
                    Controller.toXML("productos.xml");
                    System.out.println("Base de datos guardada con exito");
                    break;
                case 1:
                    List<Product> ListaProductos = Controller.products.listAllProductsNoDuplicates();
                    for (Product producto : ListaProductos) {
                        System.out.println(producto);
                    }
                    pulsarEnter();
                    break;
                    
                case 2:
                    String name = devolverString("Introduce nombre del producto: ");
                    String description = devolverString("Introduce la descripcion del producto: ");
                    float prize = devolverFloat("Introduce el precio del producto: ");
                    if (Controller.products.createProduct(name, description, prize)) {
                        System.out.println("Producto agregado exitosamente");
                    } else {
                        System.out.println("El producto no se ha podido agregar");
                    }
                    pulsarEnter();
                    break;

                case 3:
                    name = devolverString("Introduce el nombre del producto para borrar: ");
                    if (Controller.products.removeProduct(name)) {
                        System.out.println("Producto eliminado exitosamente");
                    } else {
                        System.out.println("El producto no ha podido ser borrado");
                    }
                    pulsarEnter();
                    break;

                case 4:

                    break;

                case 5:
                    String nombre = devolverString("Introduce el nombre del producto que quieras observar: ");
                    Set<Product> ListaNombre = Controller.products.listAllByName(nombre);
                    if (ListaNombre.isEmpty()) {
                        System.out.println("No hay coincidencias");
                        pulsarEnter();
                    } else {
                        for (Product producto : ListaNombre) {
                            System.out.println(producto);
                        }

                        String key = devolverString("Introduce la key del producto que quieras cambiar: ");
                        Product productoCambio = Controller.products.searchByKey(key);
                        if (productoCambio != null) {
                            String confirmar = devolverString("Quieres cambiar la disponibilidad de " + productoCambio.getStatus() + " (y/n): ");
                            if (confirmar.equals("y")) {
                                if (productoCambio.getStatus() == Product.Status.AVAILABLE) {
                                    productoCambio.setStatus(Product.Status.RESERVED);
                                    System.out.println("Producto cambiado a RESERVED con exito");
                                } else if (productoCambio.getStatus() == Product.Status.RESERVED) {
                                    productoCambio.setStatus(Product.Status.AVAILABLE);
                                    System.out.println("Producto cambiado a AVAILABLE con exito");
                                }
                            }
                        } else {
                            System.out.println("No hay coincidencias en la key");
                            pulsarEnter();
                        }

                    }
                    break;
            }

        } while (opcion != 0);
    }

    /**
     * Metodo que escribe y devuelve un string que introduzca un usuario
     *
     * @param texto Texto que quieres que se escribe, ejemplo (Introduce
     * contraseña)
     * @return string que introduce el usuario
     */
    public static String devolverString(String texto) {
        String resultado;
        Scanner teclado = new Scanner(System.in);

        System.out.print(texto);
        resultado = teclado.nextLine();

        return resultado;
    }

    /**
     * Metodo que escribe y devuelve un int que introduzca un usuario
     * @param texto Texto que quieres que se escribe, ejemplo (Introduce numero)
     * @return int que introduce el usuario
     */
    public static int devolverInt(String texto) {
        int resultado = 0;
        boolean valid = false;
        Scanner teclado = new Scanner(System.in);
        do {
            try {
                System.out.print(texto);
                resultado = teclado.nextInt();
                valid = true;
            } catch (Exception e) {
                valid = false;
                System.out.println("\nIntroduce un numero correcto");
                teclado = new Scanner(System.in);
            }
        } while (!valid);
        return resultado;
    }

    public static float devolverFloat(String texto) {
        float resultado = 0;
        boolean valid = false;
        Scanner teclado = new Scanner(System.in);
        do {
            try {
                System.out.print(texto);
                resultado = teclado.nextFloat();
                valid = true;
            } catch (Exception e) {
                valid = false;
                System.out.println("\nIntroduce un numero correcto");
                teclado = new Scanner(System.in);
            }
        } while (!valid);
        return resultado;
    }

    /**
     * Metodo para que tengas que pulsar Enter para continuar
     */
    public static void pulsarEnter() {
        System.out.print("\nPulsa Enter para continuar...");
        Scanner teclado = new Scanner(System.in);
        teclado.nextLine();
    }

    public static void menu_lista() {
        int opcion = 0;
        do {
            System.out.println("\n|---------------------|");
            System.out.println("|    Menu Listar   |");
            System.out.println("|---------------------|");
            System.out.println("| 1) Listar productos |");
            System.out.println("| 2) Listar peliculas |");
            System.out.println("| 3) Listar juegos    |");
            System.out.println("| 0) Salir            |");
            System.out.println("|---------------------|");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    Set<Product> ListaProductos = Controller.products.listAllProducts();
                    for (Product producto : ListaProductos) {
                        System.out.println(producto);
                    }
                    break;

                case 2:
                    List<Product> ListaPeliculas = Controller.products.listAllDifferentMovies();
                    for (Product peliculas : ListaPeliculas) {
                        System.out.println(peliculas);
                    }

                    break;

                case 3:
                    List<Product> ListaJuegos = Controller.products.listAllDifferentGames();
                    for (Product juegos : ListaJuegos) {
                        System.out.println(juegos);
                    }
                    break;

                case 4:

                    break;
            }
        } while (opcion != 0);

    }
}
