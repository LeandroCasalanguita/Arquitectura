package org.example.Entidades;


public class Producto {


    private int idProducto;
    private String nombre;
    private Float valor;


    public Producto(int id, String nombre, float valor) {
        this.idProducto= id;
        this.nombre=nombre;
        this.valor=valor;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                '}';
    }
}
