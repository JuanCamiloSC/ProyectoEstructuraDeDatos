package Proceso.Archivos;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class ArchivoUtils {
    //SERIALIZACIÓN DE OBJETOS EN ARCHIVOS PLANOS

    public static void serializarObjeto(String ruta, Object objeto) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
        oos.writeObject(objeto);
        oos.close();
    }
    public static Object deserializarObjeto(String ruta) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
        Object objeto = ois.readObject();
        ois.close();
        return objeto;
    }
    //SERIALIZAR EN UN ARCHIVO XML
    public void serializarObjetoXML(String ruta, Object objeto) throws Exception{
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(ruta));
        encoder.writeObject(objeto);
        encoder.close();
    }
    public static Object deserializarObjetoXML(String ruta) throws Exception{
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(ruta));
        Object objeto = decoder.readObject();
        decoder.close();
        return objeto;
    }

}
