/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.ADetalleParteDeRecepcion;
import java.util.ArrayList;

/**
 *
 * @author Francisco Palacio
 */
public interface IDetalleParteDeRecepcion extends IOperacionesGenerales<ADetalleParteDeRecepcion,Integer>{
    
    ArrayList<ADetalleParteDeRecepcion> obtenerPorIdCabecera(int idCabecera);
    
    
}
