package io.VideoClub.View;

import com.sun.glass.ui.SystemClipboard;
import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Client;
import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Film; //NNoo
import io.VideoClub.Model.Game;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Reservation;
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
            System.out.println("\n+-------------------+");
            System.out.println("|        Menu       |");
            System.out.println("+-------------------+");
            System.out.println("| 1) Iniciar sesion |");
            System.out.println("| 2) Registrarse    |");
            System.out.println("| 3) Información.   |");
            System.out.println("| 4) Empleados      |");
            System.out.println("| 0) Salir          |");
            System.out.println("+-------------------+");

            numero = devolverInt("Introduce una opción: ");
            opciones_principal(numero);
        } while (numero != 0);
    }

    static void opciones_principal(int numero) {
        switch (numero) {
            case 1:
                Iniciar_sesion();
                break;

            case 2:
                registrarse();
                break;

            case 3:
                System.out.println("Bienvenido al  Videoclub 'PochoBuster' ....");
                break;

            case 4:
                InicioEmpleados();
                break;
        }
    }

    public static boolean Iniciar_sesion() {
        boolean result = false;

        System.out.println("\n+-------------------+");
        System.out.println("|   Iniciar Sesion  |");
        System.out.println("+-------------------+");
        String usuario = devolverString("Introduce tu usuario: ");
        String contrasena = devolverString("Introduce tu Contraseña: ");
        if (usuario != null && contrasena != null) {
            if (Controller.clients.searchUser(usuario) && Controller.clients.searchpassword(contrasena)) {
                System.out.println("Inicio de sesion correcto");
                pulsarEnter();
                lista_sesion(usuario);
            } else {
                System.out.println("Usuario o contraseña incorrecta");
                pulsarEnter();
            }
        }
        return result;
    }

    public static void lista_sesion(String usuario) {
        int opcion = 0;
        do {
            System.out.println("\n+---------------------+");
            System.out.println("|    Menu principal   |");
            System.out.println("+---------------------+");
            System.out.println("| 1) Listar           |");
            System.out.println("| 2) Cuenta           |");
            System.out.println("| 3) Reservar.        |");
            System.out.println("| 4) Cancelar reserva |");
            System.out.println("| 0) Salir            |");
            System.out.println("+---------------------+");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    menu_lista();
                    break;

                case 2:
                    Client user = Controller.clients.devolverCliente(usuario);
                    Client NuevoCliente = MenuEditarCliente(user);
                    if (Controller.clients.editClient(NuevoCliente)) {
                        System.out.println("El cliente a sido modificado con exito");
                        pulsarEnter();
                    } else {
                        System.out.println("El cliente no se ha podido modificar");
                        pulsarEnter();
                    }
                    break;

                case 3:
                    user = Controller.clients.devolverCliente(usuario);
                    menu_reservas(user);

                    break;

                case 4:

                    break;
            }
        } while (opcion != 0);

    }

    public static void registrarse() {
        System.out.println("\n+-------------------+");
        System.out.println("|    Registrarse    |");
        System.out.println("+-------------------+");
        String correo = devolverString("Introduzca un correo: ");
        String contrasena = devolverString("Introdce una contraseña: ");
        if (correo != null && contrasena != null) {
            String nombre = devolverString("Introduce tu nombre: ");
            String usuario = devolverString("Introduce tu usuario: ");
            String telefono = devolverString("Introduce tu teléfono: ");

            Controller.clients.addClient(nombre, telefono, usuario, contrasena);
            System.out.println("Usuario creado");
        } else {
            System.out.println("No se ha podido realizar el registro");
        }

        pulsarEnter();
    }

    public static void InicioEmpleados() {
        System.out.println("\n+-------------------------------+");
        System.out.println("|    Inicio sesion empleados    |");
        System.out.println("+-------------------------------+");
        String usuario = devolverString("Introduzca su Usuario: ");
        String contrasena = devolverString("Introduzca su Contraseña: ");
        if (usuario.equals("programacion24") && contrasena.equals("12345")) {
            System.out.println("Usuario logeado correctamente");
            pulsarEnter();
            MenuEmpleados();
        } else {
            System.out.println("Usuario o Contraseña incorrecto");
            pulsarEnter();
        }
    }

    public static void MenuEmpleados() {
        int opcion = 0;
        do {
            System.out.println("\n+---------------------+");
            System.out.println("|     Menu empleado   |");
            System.out.println("+---------------------+");
            System.out.println("| 1) Listar Productos |");
            System.out.println("| 2) Añadir Producto  |");
            System.out.println("| 3) Quitar Producto  |");
            System.out.println("| 4) Editar Productos |");
            System.out.println("| 5) Disponibilidad   |");
            System.out.println("| 6) Reservas         |");
            System.out.println("| 0) Salir            |");
            System.out.println("+---------------------+");

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
                        Product otro = MenuEditar(productoCambio);
                        if (Controller.products.editProduct(key, otro)) {
                            System.out.println("Producto editado con exito");
                        } else {
                            System.out.println("El producto no se ha podido editar");
                        }
                    }
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
                case 6:
                    Set<Reservation> reservas = Controller.reservations.listAllReservations();
                    for(Reservation reserva : reservas){
                        System.out.println(reserva);
                    }
                    break;
                    
            }

        } while (opcion != 0);
    }

    public static void MenuAddProductos() {
        boolean resultado = false;
        do {
            System.out.println("\n+----------------------+");
            System.out.println("|   Añadir Productos   |");
            System.out.println("+----------------------+");
            System.out.println("| 1) Añadir Pelicula   |");
            System.out.println("| 2) Añadir Juego      |");
            System.out.println("| 3) Añadir Otro Tipo  |");
            System.out.println("+----------------------+");

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

            if (!devolverString("\n¿Quieres seguir añadiendo productos? (y/n): ").equals("y")) {
                resultado = true;
            }
        } while (!resultado);
    }

    public static MovieCategory MenuMovieCategory() {
        MovieCategory resultado = MovieCategory.Horror;
        System.out.println("\n+-----------------------+");
        System.out.println("| Categoria de Pelicula |");
        System.out.println("+-----------------------+");
        System.out.println("| 1) Horror             |");
        System.out.println("| 2) Love               |");
        System.out.println("| 3) Action             |");
        System.out.println("| 4) SciFi              |");
        System.out.println("+-----------------------+");

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

    public static GameCategory MenuGameCategory() {
        GameCategory resultado = GameCategory.Shooter;
        System.out.println("\n+-----------------------+");
        System.out.println("| Categoria de Juegos   |");
        System.out.println("+-----------------------+");
        System.out.println("| 1) Adventure          |");
        System.out.println("| 2) Cars               |");
        System.out.println("| 3) Shooter            |");
        System.out.println("+-----------------------+");

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

    public static Client MenuEditarCliente(Client cliente) {
        int opcion = 0;
        do {
            System.out.println("\n+--------------------------------+");
            System.out.println("|   Cliente: " + cliente.getID() + "    |");
            System.out.println("+--------------------------------+");
            System.out.println(" 1) Editar nombre: " + cliente.getName());
            System.out.println(" 2) Editar telefono: " + cliente.getPhone());
            System.out.println(" 3) Editar contraseña: " + cliente.getPassword());
            System.out.println(" 0) Guardar producto");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    String nombre = devolverString("Introduce el nuevo nombre: ");
                    cliente.setName(nombre);
                    break;
                case 2:
                    String telefono = devolverString("Introduce el nuevo telefono: ");
                    cliente.setPhone(telefono);
                    break;
                case 3:
                    String contrasena = devolverString("Introduce la nueva contraseña: ");
                    cliente.setPassword(contrasena);
                    break;
            }
        } while (opcion != 0);

        return cliente;
    }

    public static Product MenuEditar(Product producto) {
        int opcion = 0;
        do {
            System.out.println("\n+--------------------------------+");
            System.out.println("|   Producto: " + producto.getKey() + "   |");
            System.out.println("+--------------------------------+");
            System.out.println(" 1) Editar nombre: " + producto.getName());
            System.out.println(" 2) Editar descripcion: " + producto.getDescription());
            System.out.println(" 3) Editar precio: " + producto.getPrize());

            if (producto.getType() == ProductsTypes.Peliculas) {
                Film pelicula = (Film) producto;
                System.out.println(" 4) Editar Categoria: " + pelicula.getTypeMovie());
                System.out.println(" 5) Editar Edad Minima: " + pelicula.getMinAge());
            } else if (producto.getType() == ProductsTypes.Juegos) {
                Game juego = (Game) producto;
                System.out.println(" 4) Editar categoria:" + juego.getTypeGame());
                System.out.println(" 5) Editar Edad minima" + juego.getMinAge());
            }

            System.out.println(" 0) Guardar producto");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    String nombre = devolverString("Introduce el nuevo nombre: ");
                    producto.setName(nombre);
                    break;
                case 2:
                    String descripcion = devolverString("Introduce la nueva descripcion: ");
                    producto.setDescription(descripcion);
                    break;
                case 3:
                    double precio = devolverDouble("Introduce el nuevo precio: ");
                    producto.setPrize(precio);
                    break;

                case 4:
                    if (producto.getType() == ProductsTypes.Peliculas) {
                        MovieCategory categoria = MenuMovieCategory();
                        Film other = (Film) producto;
                        other.setTypeMovie(categoria);

                    } else if (producto.getType() == ProductsTypes.Juegos) {
                        GameCategory categoria = MenuGameCategory();
                        Game other = (Game) producto;
                        other.setTypeGame(categoria);
                    }
                    break;

                case 5:
                    if (producto.getType() == ProductsTypes.Peliculas) {
                        int MinAge = devolverInt("Introduce una nueva edad: ");
                        Film other = (Film) producto;
                        other.setMinAge(MinAge);
                    } else if (producto.getType() == ProductsTypes.Juegos) {
                        int MinAge = devolverInt("Introduce una nueva edad: ");
                        Game other = (Game) producto;
                        other.setMinAge(MinAge);
                    }
                    break;
            }

        } while (opcion != 0);

        return producto;
    }

    public static void menu_lista() {
        int opcion = 0;
        do {
            System.out.println("\n+-------------------+");
            System.out.println("|    Menu Listar      |");
            System.out.println("+---------------------+");
            System.out.println("| 1) Listar productos |");
            System.out.println("| 2) Listar peliculas |");
            System.out.println("| 3) Listar juegos    |");
            System.out.println("| 0) Salir            |");
            System.out.println("+---------------------+");

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

    public static void menu_reservas(Client cliente) {
        int opcion = 0;
        do {
            System.out.println("\n+---------------------+");
            System.out.println("|     Menu Reservas     |");
            System.out.println("+-----------------------+");
            System.out.println("| 1) Listar productos   |");
            System.out.println("| 2) Reservar producto  |");
            System.out.println("| 0) Salir              |");
            System.out.println("+-----------------------+");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    Set<Product> ListaProductos = Controller.products.listAllByStatus(Product.Status.AVAILABLE);
                    for (Product producto : ListaProductos) {
                        System.out.println(producto);
                    }
                    pulsarEnter();
                    break;

                case 2:
                    int contador = 0;
                    Product prod = null;
                    String nombre = devolverString("Introduce el nombre de la pelicula a reservar: ");
                    //Ciscu y los Cisquitos
                    Set<Product> ListaPeliculas = Controller.products.listAllByName(nombre);
                    
                    if (ListaPeliculas != null) {
                        for (Product peliculas : ListaPeliculas) {
                            if(peliculas.getStatus()== Product.Status.AVAILABLE){
                                System.out.println(peliculas);
                                prod = peliculas;
                                contador++;
                            }
                        }
                        if(contador == 0){
                            System.out.println("Producto/s no disponibles");
                            pulsarEnter();
                        }else{
                            String resultado = devolverString("¿Desea reservar?(y/n): ");
                            if(resultado.equals("y")){
                                Controller.reservations.reserveProduct(prod, cliente);
                                prod.setStatus(Product.Status.RESERVED);
                                Controller.products.editProduct(prod.getKey(), prod);
                                
                                System.out.println("Reserva realizada correctamente");
                                pulsarEnter();
                            }
                        }
                    }else {
                        System.out.println("No hay coincidencias");
                        pulsarEnter();
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
     *
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

}
