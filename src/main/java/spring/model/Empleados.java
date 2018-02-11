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

/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="empleados")
public class Empleados implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idempleados")
    private long idempleados;
    @Column(name="login")
    private String login;
    @Column(name="paswd")
    private String paswd;
    @Column(name="rol")
    private String rol;
    @ManyToOne
    @JoinColumn(name="usuarios_idusuarios")
    private Usuarios usuarios;
    
    @OneToMany(mappedBy="empleados",cascade=CascadeType.ALL)
    private Set<Platos> platos;
    
    public Empleados() {
    }

    public Empleados(String login, String paswd, String rol, Usuarios usuario, Set<Platos> platos) {
        this.login = login;
        this.paswd = paswd;
        this.rol = rol;
        this.usuarios = usuario;
        this.platos = platos;
    }

    public Set<Platos> getPlatos() {
        return platos;
    }

    public void setPlatos(Set<Platos> platos) {
        this.platos = platos;
    }

 

    public long getIdempleados() {
        return idempleados;
    }

    public void setIdempleados(long idempleados) {
        this.idempleados = idempleados;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPaswd() {
        return paswd;
    }

    public void setPaswd(String paswd) {
        this.paswd = paswd;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuarios getUsuario() {
        return usuarios;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuarios = usuario;
    }

    @Override
    public String toString() {
        return "Empleados{" + "idempleados=" + idempleados + ", login=" + login + ", paswd=" + paswd + ", rol=" + rol + ", usuario=" + usuarios + '}';
    }


 
    
    
}
