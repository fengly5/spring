/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="dispositivos")
public class Dispositivos implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="iddispositivos")
    private int iddispositivos;
    
    @Column(name="token")
    private String token;
    
    @ManyToOne
    @JoinColumn(name="clientes_idclientes")
    private Clientes cliente;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="dispositivos_has_notificaciones",joinColumns = {@JoinColumn(name="dispositivos_iddispositivos")},inverseJoinColumns = {@JoinColumn(name="notificaciones_idnotificaciones")})
    private Set<Notificaciones> notificacion=new HashSet();
    
    
    public Dispositivos() {
    }

    public Dispositivos(String token, Clientes cliente) {
        this.token = token;
        this.cliente = cliente;
    }

    public int getIddispositivos() {
        return iddispositivos;
    }

    public void setIddispositivos(int iddispositivos) {
        this.iddispositivos = iddispositivos;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Dispositivos{" + "iddispositivos=" + iddispositivos + ", token=" + token + ", cliente=" + cliente + '}';
    }
    
    
}
