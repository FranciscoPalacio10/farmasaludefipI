/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.AProveedor;
import farmasalud.atributosclases.ATipoProveedor;

/**
 *
 * @author Francisco Palacio
 */
public interface ITipoProveedor extends IOperacionesGenerales<ATipoProveedor,Integer> {
    int obtenerID(String descripcion);
}
