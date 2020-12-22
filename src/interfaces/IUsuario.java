/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.AUsuario;
import java.util.ArrayList;


/**
 *
 * @author Francisco Palacio
 */
public interface IUsuario extends IOperacionesGenerales<AUsuario,String>{
    ArrayList<String> retornarPermisosXRol();
    ArrayList<AUsuario> obtenerUsuarioXId(int id);
    
    
}
