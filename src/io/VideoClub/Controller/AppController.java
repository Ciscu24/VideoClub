package io.VideoClub.Controller;

import io.VideoClub.Model.Repositories.RepositoryClient;
import io.VideoClub.Model.Repositories.RepositoryItems;
import io.VideoClub.Model.Repositories.RepositoryProducts;

import java.io.File;
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
            Element raiz = doc.createElement("Productos");
            // <--- Insertar todos los contactos
            doc.appendChild(raiz);
            //Ya está creado el XML
            for(Contacto c:contactos){
                Element e = doc.createElement("Contacto");
            //e.setAttribute("Nombre", c.getNombre());
                Element n = doc.createElement("Nombre");
                n.appendChild(doc.createTextNode(c.getNombre()));
                
                Element em = doc.createElement("Email");
                em.appendChild(doc.createTextNode(c.getEmail()));
                // Nombre ---> Contacto
                e.appendChild(n);
                // Email ----> Contacto
                e.appendChild(em);
                // Contacto -> Raiz
                raiz.appendChild(e);
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
}
