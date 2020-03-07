package io.VideoClub.Controller;

import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Film;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Repositories.RepositoryClient;
import io.VideoClub.Model.Repositories.RepositoryItems;
import io.VideoClub.Model.Repositories.RepositoryProducts;

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

public class AppController{
    public RepositoryProducts products = new RepositoryProducts();
    public RepositoryItems items = new RepositoryItems();
    public RepositoryClient clients = new RepositoryClient();
    
    public AppController(){}
    
    public void toXML(String file){
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
            for(Product producto : ListaProductos){
                if(producto.getType() == ProductsTypes.Peliculas){
                    Film pelicula = (Film)producto;
                    //MovieCategory type, int minAge, String name, String description, double prize, String key, Status status, ProductsTypes ptype;
                    Element p = doc.createElement("Pelicula");
                
                    Element nombre = doc.createElement("Nombre");
                    nombre.appendChild(doc.createTextNode(pelicula.getName()));

                    Element descripcion = doc.createElement("Descripcion");
                    descripcion.appendChild(doc.createTextNode(pelicula.getDescription()));

                    Element precio = doc.createElement("Precio");
                    precio.appendChild(doc.createTextNode(""+pelicula.getPrize()));
                    
                    Element minAge = doc.createElement("minAge");
                    minAge.appendChild(doc.createTextNode(""+pelicula.getMinAge()));

                    Element key = doc.createElement("Key");
                    key.appendChild(doc.createTextNode(pelicula.getKey()));

                    Element status = doc.createElement("Status");
                    status.appendChild(doc.createTextNode(""+pelicula.getStatus()));

                    Element tipo = doc.createElement("Tipo");
                    tipo.appendChild(doc.createTextNode(""+pelicula.getType()));

                    p.appendChild(nombre);
                    p.appendChild(descripcion);
                    p.appendChild(precio);
                    p.appendChild(key);
                    p.appendChild(status);
                    p.appendChild(tipo);
                }
                Element p = doc.createElement("Producto");
                
                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(producto.getName()));
                
                Element descripcion = doc.createElement("Descripcion");
                descripcion.appendChild(doc.createTextNode(producto.getDescription()));
                
                Element precio = doc.createElement("Precio");
                precio.appendChild(doc.createTextNode(""+producto.getPrize()));
                
                Element key = doc.createElement("Key");
                key.appendChild(doc.createTextNode(producto.getKey()));
                
                Element status = doc.createElement("Status");
                status.appendChild(doc.createTextNode(""+producto.getStatus()));
                
                Element tipo = doc.createElement("Tipo");
                tipo.appendChild(doc.createTextNode(""+producto.getType()));
                
                p.appendChild(nombre);
                p.appendChild(descripcion);
                p.appendChild(precio);
                p.appendChild(key);
                p.appendChild(status);
                p.appendChild(tipo);
                
                // Productos -> Raiz
                raiz.appendChild(p);
                raiz.appendChild(j);
                raiz.appendChild(o);
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
    
    public void cargaBBDD(){
        File file = new File("productos.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("Producto");
            System.out.println("Número de Peliculas: " + nList.getLength());
            for(int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
                String descripcion = eElement.getElementsByTagName("Descripcion").item(0).getTextContent();
                double precio = Double.parseDouble(eElement.getElementsByTagName("Precio").item(0).getTextContent());

                products.createProduct(nombre, descripcion, precio);
            }
          }
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
