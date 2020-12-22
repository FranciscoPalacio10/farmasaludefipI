/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.ADetalleConsolidado;
import java.util.ArrayList;

/**
 *
 * @author Francisco Palacio
 */
public interface IDetalleConsolidado {
    ArrayList<ADetalleConsolidado> obtenerDetallesConsolidados(int i);
}
