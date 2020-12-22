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
public class AHeladera {
      private int idHeladera,	capacidad;
   private String descripcion;

    public AHeladera() {
    }

    public int getIdEstante() {
        return idHeladera;
    }

    public void setIdEstante(int idEstante) {
        this.idHeladera = idEstante;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
   
}
