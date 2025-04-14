package org.example.DAO;

import org.example.Entidades.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FacturaImplementsDAO implements DAO<Factura> {

    private Connection conn = null;

    public FacturaImplementsDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Factura obtener(int id) {
        return null;
    }

    @Override
    public List<Factura> obtenerTodos() {
        return null;
    }

    @Override
    public void insertar(Factura factura) {

    }

    @Override
    public void actualizar(Factura factura, String[] params) {

    }

    @Override
    public void borrar(Factura factura) {

    }


}
