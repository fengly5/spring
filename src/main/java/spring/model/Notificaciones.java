/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="notificaciones")
public class Notificaciones implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idnotificaciones")
    private int idnotificaciones;
    
    @Column
    private String mensaje;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy="notificacion")
    private Set<Dispositivos> dispositivo=new HashSet();

    @OneToMany(mappedBy="notificacion", cascade=CascadeType.ALL)
    private Set<Notif_sistema> notif_sistema;
    
    public Notificaciones() {
    }

    public Notificaciones(String mensaje, Date fecha) {
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public Notificaciones(String mensaje, Date fecha, Set<Notif_sistema> notif_sistema) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.notif_sistema = notif_sistema;
    }

    public int getIdnotificaciones() {
        return idnotificaciones;
    }

    public void setIdnotificaciones(int idnorificaciones) {
        this.idnotificaciones = idnorificaciones;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Dispositivos> getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Set<Dispositivos> dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Set<Notif_sistema> getNotif_sistema() {
        return notif_sistema;
    }

    public void setNotif_sistema(Set<Notif_sistema> notif_sistema) {
        this.notif_sistema = notif_sistema;
    }
    
    
}
