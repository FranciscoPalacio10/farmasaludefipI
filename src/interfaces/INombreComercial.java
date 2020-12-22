/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.ANombreComercial;
import java.util.ArrayList;

/**
 *
 * @author Francisco Palacio
 */
public interface INombreComercial extends IOperacionesGenerales<ANombreComercial,Integer> {
    ArrayList<ANombreComercial> obtenerNombreComercialProductosFarmace(int idProducto);
    ArrayList<ANombreComercial> obtenerNombreComercialProductosNoFarmace(int idProducto);
}
