/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.AProducto;
import farmasalud.operaciones.OManager;
import farmasalud.operaciones.ONombreComercial;
import java.util.ArrayList;

/**
 *
 * @author Francisco Palacio
 */
public interface IProducto extends IOperacionesGenerales<AProducto,Integer> {
    ArrayList<String> obtenerNombreProducto(int idProducto,OManager traer);
}
