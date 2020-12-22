package interfaces;


import java.util.ArrayList;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francisco Palacio
 * @param <T>
 * @param <I>
 */
public interface IOperacionesGenerales<T,I> {
    ArrayList<T> obtenerTodos();
    ArrayList<T> obtenerUno(I id);
    int insertar(T a);
    void modificar(T a);
    void actualizar(ArrayList<T> lista);
    ArrayList<T> ejecutarProcedimientoAlmacenado(String procedimiento);
    
}
