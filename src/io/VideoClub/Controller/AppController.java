package io.VideoClub.Controller;

import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Film;
import io.VideoClub.Model.Game;
import io.VideoClub.Model.Other;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Product.Status;
import io.VideoClub.Model.Repositories.RepositoryClient;
import io.VideoClub.Model.Repositories.RepositoryItems;
import io.VideoClub.Model.Repositories.RepositoryProducts;
import io.VideoClub.Model.Repositories.RepositoryReservations;

import java.io.File;
import java.util.List;
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

public class AppController {

    public RepositoryProducts products = new RepositoryProducts();
    public RepositoryItems items = new RepositoryItems();
    public RepositoryClient clients = new RepositoryClient();
    public RepositoryReservations reservations = new RepositoryReservations();

    public AppController() {}

    public void toXML(String file) {
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
            StreamResult result = new StreamResult(new File(file));
            trans.transform(source, result);

        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerException ex) {
            System.out.println(ex);
        }

    }

    public void cargaBBDD() {
        File file = new File("productos.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Pelicula");
            System.out.println("Número de Peliculas: " + nList.getLength());
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
            System.out.println("Número de Juegos: " + nList.getLength());
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
            System.out.println("Número de Otros: " + nList.getLength());
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
