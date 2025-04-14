package org.example.DAO;

import org.example.Entidades.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductoImplementsDAO implements DAO<Producto> {

    private Connection conn = null;

    public ProductoImplementsDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Producto obtener(int id) {

        String query = "SELECT p.nombre, p.valor" +
                "FROM producto p " +
                "WHERE p.idProducto = ?";
        Producto productoById = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                float valor = rs.getFloat("valor");

                productoById = new Producto(id, nombre, valor);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                conn.commit();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return productoById;
    }

    @Override
    public List<Producto> obtenerTodos() {
        return List.of();
    }

    @Override
    public void insertar(Producto producto) {
        String query = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
            ps.executeUpdate();
            System.out.println("Cliente insertada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actualizar(Producto producto, String[] params) {

    }

    @Override
    public void borrar(Producto producto) {

    }

    public Producto findProductoDTO() {
        String query = """
        SELECT 
            p.idProducto,
            p.nombre,
            SUM(fp.cantidad * p.valor) AS total_recaudado
        FROM 
            producto p
        JOIN 
            facturaProducto fp ON p.idProducto = fp.idProducto
        GROUP BY 
            p.idProducto, p.nombre
        ORDER BY 
            total_recaudado DESC
        LIMIT 1
    """;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                float totalRecaudado = rs.getFloat("total_recaudado");

                return new Producto(idProducto, nombre, totalRecaudado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
