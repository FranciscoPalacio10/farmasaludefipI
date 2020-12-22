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
public class AProveedor {
    private int idProveedor,idTipoProveedor;

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    private Long cuit,telefono;
    private String nombre,mail,	descripcion,idProvincia,calle;
    private int condicionesAfip;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }

   

    public int getIdTipoProveedor() {
        return idTipoProveedor;
    }

    public void setIdTipoProveedor(int idTipoProveedor) {
        this.idTipoProveedor = idTipoProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public int getCondicionesAfip() {
        return condicionesAfip;
    }

    public void setCondicionesAfip(int condicionesAfip) {
        this.condicionesAfip = condicionesAfip;
    }

  


    public AProveedor() {
    }
    
    
    
    
    
}
