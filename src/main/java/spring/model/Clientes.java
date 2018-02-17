/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="clientes")
public class Clientes implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idclientes")
    private int idclientes;
    
   // @Pattern(regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\ @([\\w]+\\.)+[\\w]+[\\w]$")
    @Column(name="email")
    private String email;
    
    
    @Column(name="paswd")
    private String paswd;
    
    @OneToMany(mappedBy="cliente",cascade=CascadeType.ALL)
    private Set<Dispositivos> dispositivos;
    
    @OneToMany(mappedBy="cliente",cascade=CascadeType.ALL)
    private Set<Reservas> reservas;
    
    @ManyToOne
    @JoinColumn (name="usuarios_idusuarios")
    private Usuarios usuarios;

    public Clientes() {
    }

    public Clientes(String email, String paswd, Set<Dispositivos> dispositivos, Set<Reservas> reservas, Usuarios usuario) {
        this.email = email;
        this.paswd = paswd;
        this.dispositivos = dispositivos;
        this.reservas = reservas;
        this.usuarios = usuario;
    }

    public Set<Reservas> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reservas> reservas) {
        this.reservas = reservas;
    }



    public long getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(int idclientes) {
        this.idclientes = idclientes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaswd() {
        return paswd;
    }

    public void setPaswd(String paswd) {
        this.paswd = paswd;
    }

    public Usuarios getUsuario() {
        return usuarios;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuarios = usuario;
    }

    public Set<Dispositivos> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(Set<Dispositivos> dispositivos) {
        this.dispositivos = dispositivos;
    }

    @Override
    public String toString() {
        return "Clientes{" + "idclientes=" + idclientes + ", email=" + email + ", paswd=" + paswd + ", usuario=" + usuarios + '}';
    }
    
    
}
