/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="notif_sistema")
public class Notif_sistema implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idnotif_sistema")
    private int idnotif_sistema;
    
   
    
    @ManyToOne
    @JoinColumn(name="notificaciones_idnotificaciones")
    private Notificaciones notificacion;
    
    @ManyToOne
    @JoinColumn(name="tipo")
    private Tipo_notif tipo_notif;

    public Notif_sistema() {
    }

    public Notif_sistema(Notificaciones notificacion, Tipo_notif tipos) {
        this.notificacion = notificacion;
        this.tipo_notif = tipos;
    }



    public int getIdnotif_sistema() {
        return idnotif_sistema;
    }

    public void setIdnotif_sistema(int idnotif_sistema) {
        this.idnotif_sistema = idnotif_sistema;
    }

    public Tipo_notif getTipos() {
        return tipo_notif;
    }

    public void setTipos(Tipo_notif tipos) {
        this.tipo_notif = tipos;
    }



    public Notificaciones getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Notificaciones notificacion) {
        this.notificacion = notificacion;
    }


    
    
}
