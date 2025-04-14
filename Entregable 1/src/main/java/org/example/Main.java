package org.example;

import org.example.DAO.ClienteImplementsDAO;
import org.example.DAO.ProductoImplementsDAO;
import org.example.Entidades.Cliente;
import org.example.Entidades.Producto;
import org.example.Factory.AbstractFactory;
import org.example.Factory.MySQLDAOfactory;
import org.example.Helpers.HelperMysql;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {

        HelperMysql helper = new HelperMysql();
        helper.dropTables();
        helper.createTables();
        helper.cargarBBDD();
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ProductoImplementsDAO producto = chosenFactory.getProducto();
        ClienteImplementsDAO cliente = chosenFactory.getCliente();


        System.out.println("Ejercicio 3- Busca producto con mayor recaudacion:");
        Producto productoMayorRec= producto.findProductoDTO();
        System.out.println(productoMayorRec);


        System.out.println("Ejercicio 4-Lista de Clientes que mas facturaron:");


        List<Cliente> ListadoClientes= cliente.clientesListadosPorMayorFacturacion();
        for (Cliente dir : ListadoClientes) {
            System.out.println(dir);
        }

        ClienteImplementsDAO x = MySQLDAOfactory.getInstance().getCliente();
        

    }

}


