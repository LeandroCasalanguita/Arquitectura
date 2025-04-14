package org.example.DAO;

import java.util.List;

public interface DAO<T> {

    T obtener(int id);

    List<T> obtenerTodos();

    void insertar(T t);

    void actualizar(T t, String[] params);

    void borrar(T t);

}
