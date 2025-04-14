package org.example.DAO;

import org.example.Entidades.FacturaProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FacturaProductoImplementsDAO implements DAO<FacturaProducto> {

    private Connection conn = null;

    public FacturaProductoImplementsDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public FacturaProducto obtener(int id) {
        return null;
    }

    @Override
    public List<FacturaProducto> obtenerTodos() {
        return List.of();
    }

    @Override
    public void insertar(FacturaProducto facturaProducto) {

    }

    @Override
    public void actualizar(FacturaProducto facturaProducto, String[] params) {

    }

    @Override
    public void borrar(FacturaProducto facturaProducto) {

    }


}
