/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.atributosclases;

/**
 *
 * @author Francisco Palacio
 */
public class ACondicionReceta {
    private String descripcion;
    private int idCondcionReceta;

    public int getIdCondcionReceta() {
        return idCondcionReceta;
    }

    public void setIdCondcionReceta(int idCondcionReceta) {
        this.idCondcionReceta = idCondcionReceta;
    }
    
    public ACondicionReceta() {
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
}
