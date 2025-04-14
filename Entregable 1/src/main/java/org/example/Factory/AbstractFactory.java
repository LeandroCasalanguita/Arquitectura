package org.example.Factory;

import org.example.DAO.ClienteImplementsDAO;
import org.example.DAO.FacturaImplementsDAO;
import org.example.DAO.FacturaProductoImplementsDAO;
import org.example.DAO.ProductoImplementsDAO;

public abstract class AbstractFactory {


    public abstract ClienteImplementsDAO getCliente();

    public abstract ProductoImplementsDAO getProducto();

    public abstract FacturaImplementsDAO getFactura();

    public abstract FacturaProductoImplementsDAO getFactura_Producto();

    public static AbstractFactory getDAOFactory(int whichFactory) {

        return MySQLDAOfactory.getInstance();

    }
}
