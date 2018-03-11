/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.validacion;

import spring.model.Clientes;
import spring.model.Notificaciones;

/**
 *
 * @author jcpm0
 */
public class NotificacionCliente {
 Clientes cliente;
 Notificaciones notificacion;

  public NotificacionCliente() {
  }

  public Clientes getCliente() {
    return cliente;
  }

  public void setCliente(Clientes cliente) {
    this.cliente = cliente;
  }

  public Notificaciones getNotificacion() {
    return notificacion;
  }

  public void setNotificacion(Notificaciones notificacion) {
    this.notificacion = notificacion;
  }

 
}
