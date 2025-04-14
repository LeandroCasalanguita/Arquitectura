package org.example.Entidades;


import org.example.DAO.FacturaProductoImplementsDAO;

import java.util.List;

public class FacturaProducto {

    private int idFactura;
    private int idProducto;
    private int cantidad;

    public FacturaProducto(int idProducto, int cantidad, int idFactura) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idFactura = idFactura;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "FacturaProducto{" +
                "idFactura=" + idFactura +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                '}';
    }
}
