/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.AProveedor;

/**
 *
 * @author Francisco Palacio
 */
public interface IProveedor extends IOperacionesGenerales<AProveedor,Integer> {
    int obtenerIdProveedorXNombre(String nombre);
}
