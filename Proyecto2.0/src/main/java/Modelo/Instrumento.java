package Modelo;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Instrumento {
    private int id;
    private String nombre;
    private String clasificacion;
    private String tipo;
    private String famoso;
    private String url;

    public Instrumento() {
    }

    public Instrumento(int id, String nombre, String clasificacion, String tipo, String famoso, String url) {
        this.id = id;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.tipo = tipo;
        this.famoso = famoso;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getFamoso() {
        return famoso;
    }

    public void setFamoso(String famoso) {
        this.famoso = famoso;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public ImageIcon getImagen() throws MalformedURLException {
        URL urlImagen=new URL(this.url);
        return new ImageIcon(urlImagen);
    }
}