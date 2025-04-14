package org.example.DAO;
import org.example.Entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteImplementsDAO implements DAO<Cliente> {
    private Connection conn = null;

    public ClienteImplementsDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public Cliente obtener(int id) {
        String query = "SELECT c.nombre, c.eMail" +
                "FROM cliente c " +
                "WHERE p.idPersona = ?";

        Cliente clienteById = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String eMail = rs.getString("eMail");


                clienteById = new Cliente(id, nombre, eMail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clienteById;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return null;
    }

    @Override
    public void insertar(Cliente cliente) {
        String query = "INSERT INTO Cliente (idCliente, nombre, eMail) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cliente.getIdCliente());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getEmail());
            pstmt.executeUpdate();

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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void actualizar(Cliente cliente, String[] params) {

    }

    @Override
    public void borrar(Cliente cliente) {

    }


    public List<Cliente> clientesListadosPorMayorFacturacion() {
        String query = """
        SELECT 
            c.idCliente,
            c.nombre,
            c.email,
            SUM(fp.cantidad * p.valor) AS total_facturado
        FROM 
            cliente c
        JOIN 
            factura f ON c.idCliente = f.idCliente
        JOIN 
            facturaProducto fp ON f.idFactura = fp.idFactura
        JOIN 
            producto p ON fp.idProducto = p.idProducto
        GROUP BY 
            c.idCliente, c.nombre, c.email
        ORDER BY 
            total_facturado DESC
    """;

        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");


                Cliente cliente = new Cliente(idCliente,nombre,email);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clientes;
    }

}
