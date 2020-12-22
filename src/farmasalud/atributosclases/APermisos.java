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
public class APermisos {
    private int id_Permisos;
    private String operaciones,descripcion;

    public APermisos() {
    }

    public int getId_Permisos() {
        return id_Permisos;
    }

    public void setId_Permisos(int id_Permisos) {
        this.id_Permisos = id_Permisos;
    }

    public String getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(String operaciones) {
        this.operaciones = operaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
