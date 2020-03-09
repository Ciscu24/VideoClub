package io.VideoClub.View;

import com.sun.glass.ui.SystemClipboard;
import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Client;
import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Film; //NNoo
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
        //MenuEmpleados();
        principal();
    }

    public static void logo() {
        System.out.println(" _______            _           _______            _       _______ ");
        System.out.println("|   _   |          | |         |   _   |       ___| |___  |   _   |");
        System.out.println("|  |_|  |          | |         |  |_|  |      |___   ___|_|  |_|  |");
        System.out.println("|  _____|_____ ____| |___ _____|_____ /_   _ _____| |  ___|   ___/ ");
        System.out.println("| |     |  _  |  __|  _  |  _  |  _   | | | |  ___| | |___|  /____ ");
        System.out.println("| |     | |_| | |__| | | | |_| | |_|  | |_| | |___| |  ___|  ___  |");
        System.out.println("| |     |_____|____|_| |_|_____|______|_____|___  | | |___|_|   | |");
        System.out.println("|_|                            |__________________|_|_______|   |_|");
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
                InicioEmpleados();
                break;
        }
    }

    public static boolean Iniciar_sesion() {
        boolean result = false;
        String usuario;
        String contrasena;
        Scanner teclado = new Scanner(System.in);

        System.out.println("\n|-------------------|");
        System.out.println("|   Iniciar Sesion  |");
        System.out.println("|-------------------|");
        System.out.println(" Introduce tu usuario");
        usuario = teclado.next();
        System.out.println(" Introduce tu Contraseña");
        contrasena = teclado.next();
        if (usuario != null && contrasena != null) {
            if (Controller.clients.searchUser(usuario) && Controller.clients.searchpassword(contrasena)) {
                lista_sesion();
            }else{
                System.out.println("Usuario o contraseña incorrecta");
            }
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
                    Client user = Controller.clients.devolverCliente(Usuario);
                    user.toString();

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
        String correo = devolverString("Introduzca un correo: ");
        String contrasena = devolverString("Introdce una contraseña: ");
        if (correo != null && contrasena != null) {
            String nombre = devolverString("Introduce tu nombre: ");
            String usuario = devolverString("Introduce tu usuario: ");
            String telefono = devolverString("Introduce tu teléfono: ");
            
            Controller.clients.addClient(nombre, telefono, usuario, contrasena);
            System.out.println("Usuario creado");
        }else{
            System.out.println("No se ha podido realizar el registro");
        }
    }

    public static void InicioEmpleados() {
        System.out.println("\n|-------------------------------|");
        System.out.println("|    Inicio sesion empleados    |");
        System.out.println("|-------------------------------|");
        String usuario = devolverString("Introduzca su Usuario: ");
        String contrasena = devolverString("Introduzca su Contraseña: ");
        //Buscar por usuario y por contraseña con un if
        if(Controller.clients.searchUser(usuario) && Controller.clients.searchpassword(contrasena)){
            System.out.println("Usuario logeado correctamente");
            MenuEmpleados();
        }else{
            System.out.println("Usuario o Contraseña incorrecto");
        }
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
            System.out.println("| 7) Reservas         |");
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
                    MenuAddProductos();
                    break;

                case 3:
                    String nombre = devolverString("Introduce el nombre del producto para buscar: ");
                    Set<Product> ListaNombre = Controller.products.listAllByName(nombre);
                    if (ListaNombre.isEmpty()) {
                        System.out.println("No hay coincidencias");
                        pulsarEnter();
                    } else {
                        for (Product producto : ListaNombre) {
                            System.out.println(producto);
                        }
                        String key = devolverString("Introduce la key del producto que quieras borrar: ");
                        Product productoBorrar = Controller.products.searchByKey(key);
                        if (Controller.products.removeProduct(productoBorrar.getName())) {
                            System.out.println("Producto eliminado exitosamente");
                        } else {
                            System.out.println("El producto no ha podido ser borrado");
                        }
                        pulsarEnter();
                    }
                    break;

                case 4:

                    break;

                case 5:
                    nombre = devolverString("Introduce el nombre del producto que quieras observar: "); //Buscarlo bien y no tener que tener que poner todo el nombre
                    ListaNombre = Controller.products.listAllByName(nombre);
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

        } while(opcion != 0);
    }
    
    public static void MenuAddProductos(){
        boolean resultado = false;
        do {
            System.out.println("\n|----------------------|");
            System.out.println("|   Añadir Productos   |");
            System.out.println("|----------------------|");
            System.out.println("| 1) Añadir Pelicula   |");
            System.out.println("| 2) Añadir Juego      |");
            System.out.println("| 3) Añadir Otro Tipo  |");
            System.out.println("|----------------------|");

            int opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    MovieCategory movie = MenuMovieCategory();
                    String name = devolverString("Introduce el nombre de la pelicula: ");
                    String description = devolverString("Introduce la descripcion de la pelicula: ");
                    double prize = devolverDouble("Introduce el precio de la pelicula: ");
                    int minAge = devolverInt("Introduce la edad minima recomendada: ");
                    if (Controller.products.addMovie(ProductsTypes.Peliculas, name, description, movie, minAge, prize)) {
                        System.out.println("Producto agregado exitosamente");
                    } else {
                        System.out.println("El producto no se ha podido agregar");
                    }
                    break;
                    
                case 2:
                    GameCategory game = MenuGameCategory();
                    name = devolverString("Introduce el nombre del juego: ");
                    description = devolverString("Introduce la descripcion del juego: ");
                    prize = devolverDouble("Introduce el precio del juego: ");
                    minAge = devolverInt("Introduce la edad minima recomendada: ");
                    if (Controller.products.addGame(ProductsTypes.Juegos, name, description, game, minAge, prize)) {
                        System.out.println("Producto agregado exitosamente");
                    } else {
                        System.out.println("El producto no se ha podido agregar");
                    }
                    break;
                    
                case 3:
                    name = devolverString("Introduce el nombre del producto: ");
                    description = devolverString("Introduce la descripcion del producto: ");
                    prize = devolverDouble("Introduce el precio del producto: ");
                    if (Controller.products.addOther(ProductsTypes.Otros, name, description, prize)) {
                        System.out.println("Producto agregado exitosamente");
                    } else {
                        System.out.println("El producto no se ha podido agregar");
                    }
                    break;
            }
            
            if(!devolverString("\n¿Quieres seguir añadiendo productos? (y/n): ").equals("y")){
                resultado = true;
            }
        }while(!resultado);
    }
    
    public static MovieCategory MenuMovieCategory(){
        MovieCategory resultado = MovieCategory.Horror;
        System.out.println("\n|-----------------------|");
        System.out.println("| Categoria de Pelicula |");
        System.out.println("|-----------------------|");
        System.out.println("| 1) Horror             |");
        System.out.println("| 2) Love               |");
        System.out.println("| 3) Action             |");
        System.out.println("| 4) SciFi              |");
        System.out.println("|-----------------------|");

        int opcion = devolverInt("Introduce una opcion: ");

        switch (opcion) {
            case 1:
                resultado = MovieCategory.Horror;
                break;
            case 2:
                resultado = MovieCategory.Love;
                break;
            case 3:
                resultado = MovieCategory.Action;
                break;
            case 4:
                resultado = MovieCategory.SciFi;
                break;
        }
        
        return resultado;
    }
    
    public static GameCategory MenuGameCategory(){
        GameCategory resultado = GameCategory.Shooter;
        System.out.println("\n|-----------------------|");
        System.out.println("| Categoria de Juegos   |");
        System.out.println("|-----------------------|");
        System.out.println("| 1) Adventure          |");
        System.out.println("| 2) Cars               |");
        System.out.println("| 3) Shooter            |");
        System.out.println("|-----------------------|");

        int opcion = devolverInt("Introduce una opcion: ");

        switch (opcion) {
            case 1:
                resultado = GameCategory.Adventures;
                break;
            case 2:
                resultado = GameCategory.Cars;
                break;
            case 3:
                resultado = GameCategory.Shooter;
                break;
        }
        
        return resultado;
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

    public static double devolverDouble(String texto) {
        double resultado = 0;
        boolean valid = false;
        Scanner teclado = new Scanner(System.in);
        do {
            try {
                System.out.print(texto);
                resultado = teclado.nextDouble();
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
