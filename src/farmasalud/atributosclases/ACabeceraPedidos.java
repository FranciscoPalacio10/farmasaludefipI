/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.atributosclases;

import java.util.Date;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Francisco Palacio
 */
public class ACabeceraPedidos {
      private int numeroPedido, plazodeEntrega,id_usuario;
     private Date fechaPedido;
    private String lugarDeEntrega,estado, id_Sucursal,usuario;
    private CheckBox selected;
    public ACabeceraPedidos(){
        
    }

    public CheckBox getSelected() {
        return selected;
    }

    public void setSelected(CheckBox selected) {
        this.selected = selected;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getId_Sucursal() {
        return id_Sucursal;
    }

    public void setId_Sucursal(String id_Sucursal) {
        this.id_Sucursal = id_Sucursal;
    }

    public int getPlazodeEntrega() {
        return plazodeEntrega;
    }

    public void setPlazodeEntrega(int plazodeEntrega) {
        this.plazodeEntrega = plazodeEntrega;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getLugarDeEntrega() {
        return lugarDeEntrega;
    }

    public void setLugarDeEntrega(String lugarDeEntrega) {
        this.lugarDeEntrega = lugarDeEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
