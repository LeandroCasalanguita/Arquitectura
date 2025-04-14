package org.example.Factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.example.DAO.ClienteImplementsDAO;
import org.example.DAO.FacturaImplementsDAO;
import org.example.DAO.FacturaProductoImplementsDAO;
import org.example.DAO.ProductoImplementsDAO;

public class MySQLDAOfactory extends AbstractFactory {


    private static MySQLDAOfactory instance = null;

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String uri = "jdbc:mysql://localhost:3306/mi_basededatos?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "usuario";
    private static final String CONTRASENIA = "passUsuario";

    public static Connection conn;

    private MySQLDAOfactory() {

    }


    public static synchronized MySQLDAOfactory getInstance() {
        if (instance == null) {
            instance = new MySQLDAOfactory();
        }
        return instance;
    }

    public static Connection createConnection() {
        if (conn != null) {
            return conn;
        }

        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                 NoSuchMethodException | SecurityException | ClassNotFoundException |
                 InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, USUARIO, CONTRASENIA);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    @Override
    public ClienteImplementsDAO getCliente() {

        return new ClienteImplementsDAO(createConnection());

    }

    @Override
    public ProductoImplementsDAO getProducto() {
        return new ProductoImplementsDAO(createConnection());
    }

    @Override
    public FacturaImplementsDAO getFactura() {
        return new FacturaImplementsDAO(createConnection());
    }

    @Override
    public FacturaProductoImplementsDAO getFactura_Producto() {
        return new FacturaProductoImplementsDAO(createConnection());
    }
}
