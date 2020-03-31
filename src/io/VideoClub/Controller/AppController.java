package io.VideoClub.Controller;

import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Film;
import io.VideoClub.Model.Game;
import io.VideoClub.Model.Other;
import io.VideoClub.Model.Client;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Product.Status;
import io.VideoClub.Model.Repositories.RepositoryClient;
import io.VideoClub.Model.Repositories.RepositoryItems;
import io.VideoClub.Model.Repositories.RepositoryProducts;
import io.VideoClub.Model.Repositories.RepositoryReservations;
import io.VideoClub.Model.Reservation;
import io.VideoClub.Model.Reservation.StatusReserve;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AppController implements IAppController{

    public RepositoryProducts products = new RepositoryProducts();
    public RepositoryItems items = new RepositoryItems();
    public RepositoryClient clients = new RepositoryClient();
    public RepositoryReservations reservations = new RepositoryReservations();

    public AppController() {}

    @Override
    public boolean saveCatalogFromDDBB() {
        boolean resultado = false;
        //validar si el archivo es .xml
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();

            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();
            //Tenemos el contenedor del documento, hay que crear nodos.
            Element raiz = doc.createElement("ListaProductos");
            // <--- Insertar todos los contactos
            doc.appendChild(raiz);
            //Ya está creado el XML
            List<Product> ListaProductos = products.listAllProductsNoDuplicates();
            
            for (Product producto : ListaProductos) {

                if (producto.getType() == ProductsTypes.Peliculas) {
                    Film pelicula = (Film) producto;

                    Element p = doc.createElement("Pelicula");

                    Element nombre = doc.createElement("Nombre");
                    nombre.appendChild(doc.createTextNode(pelicula.getName()));

                    Element descripcion = doc.createElement("Descripcion");
                    descripcion.appendChild(doc.createTextNode(pelicula.getDescription()));

                    Element precio = doc.createElement("Precio");
                    precio.appendChild(doc.createTextNode("" + pelicula.getPrize()));

                    Element edad = doc.createElement("Edad");
                    edad.appendChild(doc.createTextNode("" + pelicula.getMinAge()));

                    Element key = doc.createElement("Key");
                    key.appendChild(doc.createTextNode(pelicula.getKey()));

                    Element status = doc.createElement("Status");
                    status.appendChild(doc.createTextNode("" + pelicula.getStatus()));

                    Element tipo = doc.createElement("Tipo");
                    tipo.appendChild(doc.createTextNode("" + pelicula.getTypeMovie()));

                    p.appendChild(nombre);
                    p.appendChild(descripcion);
                    p.appendChild(precio);
                    p.appendChild(edad);
                    p.appendChild(key);
                    p.appendChild(status);
                    p.appendChild(tipo);

                    // Pelicula -> Raiz
                    raiz.appendChild(p);
                }

                if (producto.getType() == ProductsTypes.Juegos) {
                    Game juego = (Game) producto;

                    Element j = doc.createElement("Juego");

                    Element nombre = doc.createElement("Nombre");
                    nombre.appendChild(doc.createTextNode(juego.getName()));

                    Element descripcion = doc.createElement("Descripcion");
                    descripcion.appendChild(doc.createTextNode(juego.getDescription()));

                    Element precio = doc.createElement("Precio");
                    precio.appendChild(doc.createTextNode("" + juego.getPrize()));

                    Element edad = doc.createElement("Edad");
                    edad.appendChild(doc.createTextNode("" + juego.getMinAge()));

                    Element key = doc.createElement("Key");
                    key.appendChild(doc.createTextNode(juego.getKey()));

                    Element status = doc.createElement("Status");
                    status.appendChild(doc.createTextNode("" + juego.getStatus()));

                    Element tipo = doc.createElement("Tipo");
                    tipo.appendChild(doc.createTextNode("" + juego.getTypeGame()));

                    j.appendChild(nombre);
                    j.appendChild(descripcion);
                    j.appendChild(precio);
                    j.appendChild(edad);
                    j.appendChild(key);
                    j.appendChild(status);
                    j.appendChild(tipo);

                    // Juego -> Raiz
                    raiz.appendChild(j);
                }

                if (producto.getType() == ProductsTypes.Otros) {
                    Other otro = (Other) producto;

                    Element o = doc.createElement("Otro");

                    Element nombre = doc.createElement("Nombre");
                    nombre.appendChild(doc.createTextNode(otro.getName()));

                    Element descripcion = doc.createElement("Descripcion");
                    descripcion.appendChild(doc.createTextNode(otro.getDescription()));

                    Element precio = doc.createElement("Precio");
                    precio.appendChild(doc.createTextNode("" + otro.getPrize()));

                    Element key = doc.createElement("Key");
                    key.appendChild(doc.createTextNode(otro.getKey()));

                    Element status = doc.createElement("Status");
                    status.appendChild(doc.createTextNode("" + otro.getStatus()));

                    o.appendChild(nombre);
                    o.appendChild(descripcion);
                    o.appendChild(precio);
                    o.appendChild(key);
                    o.appendChild(status);

                    // Otro -> Raiz
                    raiz.appendChild(o);
                }
            }

            //Guardar XML en disco duro.
            TransformerFactory tFact = TransformerFactory.newInstance();

            Transformer trans = tFact.newTransformer();
            // <---- OPCIONES DEL ARCHIVO
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty("{http://xml.apache.org/xlst}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("productos.xml"));
            resultado = true;
            trans.transform(source, result);

        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerException ex) {
            System.out.println(ex);
        }
        
        return resultado;

    }

    @Override
    public boolean loadCatalogFromDDBB() {
        boolean resultado = false;
        File file = new File("productos.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Pelicula");
            //System.out.println("Número de Peliculas: " + nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    String descripcion = eElement.getElementsByTagName("Descripcion").item(0).getTextContent();
                    double precio = Double.parseDouble(eElement.getElementsByTagName("Precio").item(0).getTextContent());
                    int edad = Integer.parseInt(eElement.getElementsByTagName("Edad").item(0).getTextContent());
                    String key = eElement.getElementsByTagName("Key").item(0).getTextContent();

                    Status status = Status.RESERVED;
                    if (eElement.getElementsByTagName("Status").item(0).getTextContent().equals("AVAILABLE")) {
                        status = Status.AVAILABLE;
                    }

                    MovieCategory type = MovieCategory.SciFi;
                    if (eElement.getElementsByTagName("Tipo").item(0).getTextContent().equals("Horror")) {
                        type = MovieCategory.Horror;
                    } else if (eElement.getElementsByTagName("Tipo").item(0).getTextContent().equals("Love")) {
                        type = MovieCategory.Love;
                    } else if (eElement.getElementsByTagName("Tipo").item(0).getTextContent().equals("Action")) {
                        type = MovieCategory.Action;
                    }

                    products.absoluteAddFilm(type, edad, nombre, descripcion, precio, key, status);
                }
            }

            nList = doc.getElementsByTagName("Juego");
            //System.out.println("Número de Juegos: " + nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    String descripcion = eElement.getElementsByTagName("Descripcion").item(0).getTextContent();
                    double precio = Double.parseDouble(eElement.getElementsByTagName("Precio").item(0).getTextContent());
                    int edad = Integer.parseInt(eElement.getElementsByTagName("Edad").item(0).getTextContent());
                    String key = eElement.getElementsByTagName("Key").item(0).getTextContent();

                    Status status = Status.RESERVED;
                    if (eElement.getElementsByTagName("Status").item(0).getTextContent().equals("AVAILABLE")) {
                        status = Status.AVAILABLE;
                    }

                    GameCategory type = GameCategory.Shooter;
                    if (eElement.getElementsByTagName("Tipo").item(0).getTextContent().equals("Adventures")) {
                        type = GameCategory.Adventures;
                    } else if (eElement.getElementsByTagName("Tipo").item(0).getTextContent().equals("Cars")) {
                        type = GameCategory.Cars;
                    }

                    products.absoluteAddGame(type, edad, nombre, descripcion, precio, key, status);
                }
            }

            nList = doc.getElementsByTagName("Otro");
            //System.out.println("Número de Otros: " + nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    String descripcion = eElement.getElementsByTagName("Descripcion").item(0).getTextContent();
                    double precio = Double.parseDouble(eElement.getElementsByTagName("Precio").item(0).getTextContent());
                    String key = eElement.getElementsByTagName("Key").item(0).getTextContent();

                    Status status = Status.RESERVED;
                    if (eElement.getElementsByTagName("Status").item(0).getTextContent().equals("AVAILABLE")) {
                        status = Status.AVAILABLE;
                    }

                    products.absoluteAddOther(nombre, descripcion, precio, key, status);
                }
            }
            
            resultado = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return resultado;
    }
    
    @Override
    public boolean saveClientsFromDDBB() {
        boolean resultado = false;
        //validar si el archivo es .xml
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();

            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();
            //Tenemos el contenedor del documento, hay que crear nodos.
            Element raiz = doc.createElement("ListaClientes");
            // <--- Insertar todos los contactos
            doc.appendChild(raiz);
            //Ya está creado el XML
            Set<Client> ListaClientes = clients.listAllClientsTrue();
            
            for (Client Cliente:ListaClientes){
                Element c=doc.createElement("Cliente");
                
                Element id=doc.createElement("ID");
                id.appendChild(doc.createTextNode(Cliente.getID()));
                
                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(Cliente.getName()));
                
                Element time = doc.createElement("Time");
                time.appendChild(doc.createTextNode(Cliente.getTime().toString()));
                
                Element telefono = doc.createElement("Telefono");
                telefono.appendChild(doc.createTextNode(Cliente.getPhone()));
                
                Element usuario = doc.createElement("Usuario");
                usuario.appendChild(doc.createTextNode(Cliente.getUser()));
                
                Element contrasena = doc.createElement("Contrasena");
                contrasena.appendChild(doc.createTextNode(Cliente.getPassword()));
                
                c.appendChild(id);
                c.appendChild(nombre);
                c.appendChild(time);
                c.appendChild(telefono);
                c.appendChild(usuario);
                c.appendChild(contrasena);
                
                raiz.appendChild(c);
            }

            //Guardar XML en disco duro.
            TransformerFactory tFact = TransformerFactory.newInstance();

            Transformer trans = tFact.newTransformer();
            // <---- OPCIONES DEL ARCHIVO
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty("{http://xml.apache.org/xlst}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("clientes.xml"));
            resultado = true;
            trans.transform(source, result);

        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerException ex) {
            System.out.println(ex);
        }
        
        return resultado;
    }


    @Override
    public boolean loadClientsFromDDBB() {
        boolean resultado = false;
        File file = new File("clientes.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("ListaClientes");
            //System.out.println("Número de Clientes: " + nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String id = eElement.getElementsByTagName("ID").item(0).getTextContent();
                    String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    String tiempo = eElement.getElementsByTagName("Time").item(0).getTextContent();
                    LocalDateTime time = LocalDateTime.parse(tiempo);
                    String telefono = eElement.getElementsByTagName("Telefono").item(0).getTextContent();
                    String usuario = eElement.getElementsByTagName("Usuario").item(0).getTextContent();
                    String contrasena = eElement.getElementsByTagName("Contrasena").item(0).getTextContent();
                    
                    clients.addClient(id, nombre, time, telefono, usuario, contrasena);
                }
            }
            resultado = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return resultado;
    }
    
    @Override
    public boolean loadReservationsFromDDBB() {
        boolean resultado = false;
        File file = new File("reservas.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("ListaReservas");
            //System.out.println("Número de Reservas: " + nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String keyProduct = eElement.getElementsByTagName("keyproduct").item(0).getTextContent();
                    if(products.searchByKey(keyProduct) != null){
                        Product producto = products.searchByKey(keyProduct);
                        String usuarioCliente = eElement.getElementsByTagName("usuariocliente").item(0).getTextContent();
                        if(clients.devolverCliente(usuarioCliente) != null){
                            Client cliente = clients.devolverCliente(usuarioCliente);
                            String fecha = eElement.getElementsByTagName("fechaini").item(0).getTextContent();
                            LocalDate fechaIni = LocalDate.parse(fecha);
                            fecha = eElement.getElementsByTagName("fechafin").item(0).getTextContent();
                            LocalDate fechaFin = LocalDate.parse(fecha);
                            fecha = eElement.getElementsByTagName("finalizado").item(0).getTextContent();
                            LocalDate finalizado = LocalDate.parse(fecha);

                            StatusReserve status = StatusReserve.ACTIVE;
                            if (eElement.getElementsByTagName("status").item(0).getTextContent().equals("FINISHED")) {
                                status = StatusReserve.FINISHED;
                            } else if (eElement.getElementsByTagName("status").item(0).getTextContent().equals("PENDING")) {
                                status = StatusReserve.PENDING;
                            }

                            reservations.reservations.add(new Reservation(producto, cliente, fechaIni, fechaFin, finalizado, status));
                        }else{
                            Client cliente = new Client("Desconocido", "Desconocido", LocalDateTime.now());
                            String fecha = eElement.getElementsByTagName("fechaini").item(0).getTextContent();
                            LocalDate fechaIni = LocalDate.parse(fecha);
                            fecha = eElement.getElementsByTagName("fechafin").item(0).getTextContent();
                            LocalDate fechaFin = LocalDate.parse(fecha);
                            fecha = eElement.getElementsByTagName("finalizado").item(0).getTextContent();
                            LocalDate finalizado = LocalDate.parse(fecha);

                            StatusReserve status = StatusReserve.ACTIVE;
                            if (eElement.getElementsByTagName("status").item(0).getTextContent().equals("FINISHED")) {
                                status = StatusReserve.FINISHED;
                            } else if (eElement.getElementsByTagName("status").item(0).getTextContent().equals("PENDING")) {
                                status = StatusReserve.PENDING;
                            }

                            reservations.reservations.add(new Reservation(producto, cliente, fechaIni, fechaFin, finalizado, status));
                        }
                        
                        
                    }else{
                        Product producto = new Product("Desconocido", "Desconocida", 0, ProductsTypes.Otros);
                        String usuarioCliente = eElement.getElementsByTagName("usuariocliente").item(0).getTextContent();
                        if(clients.devolverCliente(usuarioCliente) != null){
                            Client cliente = clients.devolverCliente(usuarioCliente);
                            String fecha = eElement.getElementsByTagName("fechaini").item(0).getTextContent();
                            LocalDate fechaIni = LocalDate.parse(fecha);
                            fecha = eElement.getElementsByTagName("fechafin").item(0).getTextContent();
                            LocalDate fechaFin = LocalDate.parse(fecha);
                            fecha = eElement.getElementsByTagName("finalizado").item(0).getTextContent();
                            LocalDate finalizado = LocalDate.parse(fecha);

                            StatusReserve status = StatusReserve.ACTIVE;
                            if (eElement.getElementsByTagName("status").item(0).getTextContent().equals("FINISHED")) {
                                status = StatusReserve.FINISHED;
                            } else if (eElement.getElementsByTagName("status").item(0).getTextContent().equals("PENDING")) {
                                status = StatusReserve.PENDING;
                            }

                            reservations.reservations.add(new Reservation(producto, cliente, fechaIni, fechaFin, finalizado, status));
                        }else{
                            Client cliente = new Client("Desconocido", "Desconocido", LocalDateTime.now());
                            String fecha = eElement.getElementsByTagName("fechaini").item(0).getTextContent();
                            LocalDate fechaIni = LocalDate.parse(fecha);
                            fecha = eElement.getElementsByTagName("fechafin").item(0).getTextContent();
                            LocalDate fechaFin = LocalDate.parse(fecha);
                            fecha = eElement.getElementsByTagName("finalizado").item(0).getTextContent();
                            LocalDate finalizado = LocalDate.parse(fecha);

                            StatusReserve status = StatusReserve.ACTIVE;
                            if (eElement.getElementsByTagName("status").item(0).getTextContent().equals("FINISHED")) {
                                status = StatusReserve.FINISHED;
                            } else if (eElement.getElementsByTagName("status").item(0).getTextContent().equals("PENDING")) {
                                status = StatusReserve.PENDING;
                            }

                            reservations.reservations.add(new Reservation(producto, cliente, fechaIni, fechaFin, finalizado, status));
                        }
                    
                    }
                }
            }
                resultado = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return resultado;
    }

    @Override 
    public boolean saveReservationsFromDDBB() {
        boolean resultado = false;
        //validar si el archivo es .xml
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();

            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();
            //Tenemos el contenedor del documento, hay que crear nodos.
            Element raiz = doc.createElement("ListaReservas");
            // <--- Insertar todos los contactos
            doc.appendChild(raiz);
            //Ya está creado el XML
            Set<Reservation> ListaReservas = reservations.listAllReservations();
            
            for (Reservation reserva:ListaReservas){
                Element r=doc.createElement("Reserva");
                
                Element keyProduct=doc.createElement("keyproduct");
                keyProduct.appendChild(doc.createTextNode(reserva.pro.getKey()));
                
                Element usuarioCliente = doc.createElement("usuariocliente");
                Client cliente = (Client)reserva.cli;
                usuarioCliente.appendChild(doc.createTextNode(cliente.getUser()));
                
                Element fechaIni = doc.createElement("fechaini");
                fechaIni.appendChild(doc.createTextNode(reserva.ini.toString()));
                
                Element fechaFin = doc.createElement("fechafin");
                fechaFin.appendChild(doc.createTextNode(reserva.end.toString()));
                
                Element finalizado = doc.createElement("finalizado");
                finalizado.appendChild(doc.createTextNode(reserva.finished.toString()));
                
                Element status = doc.createElement("status");
                status.appendChild(doc.createTextNode(reserva.status.toString()));
                
                r.appendChild(keyProduct);
                r.appendChild(usuarioCliente);
                r.appendChild(fechaIni);
                r.appendChild(fechaFin);
                r.appendChild(finalizado);
                r.appendChild(status);
                
                raiz.appendChild(r);
            }

            //Guardar XML en disco duro.
            TransformerFactory tFact = TransformerFactory.newInstance();

            Transformer trans = tFact.newTransformer();
            // <---- OPCIONES DEL ARCHIVO
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty("{http://xml.apache.org/xlst}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("reservas.xml"));
            resultado = true;
            trans.transform(source, result);

        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerException ex) {
            System.out.println(ex);
        }
        
        return resultado;
    }

    @Override
    public boolean saveAllDDBB() {
        boolean resultado = false;
        
        if(saveCatalogFromDDBB() && saveClientsFromDDBB() && saveReservationsFromDDBB()){
            resultado = true;
        }
        return resultado;
    }
    
    @Override
    public boolean loadAllDDBB() {
        boolean resultado = false;
        
        if(loadCatalogFromDDBB() && loadClientsFromDDBB() && loadReservationsFromDDBB()){
            resultado = true;
        }
        return resultado;
    }
}
