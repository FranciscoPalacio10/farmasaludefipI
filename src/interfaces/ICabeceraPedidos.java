/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import farmasalud.atributosclases.ACabeceraPedidos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francisco Palacio
 */
public interface ICabeceraPedidos extends IOperacionesGenerales<ACabeceraPedidos,Integer>  {
     ArrayList<ACabeceraPedidos> obtenerUltimosPedidos(String estado);
     void actulizarEstado(List<Integer> cabeceraEditarEstado,String estado);
     ArrayList<ACabeceraPedidos> obtenerPedidosXEstado(String estado);
}
