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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="platos")
public class Platos implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPlato")
    private int idPlato;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="precioTapa")
    private double precioTapa;
    
    @Column(name="precioMedia")
    private double precioMedia;
    
    @Column(name="precioRacion")
    private double precioRacion;
    
    @ManyToOne
    @JoinColumn(name="empleados_idempleados")
    private Empleados empleados;
   
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="platos_has_tipo_plato",joinColumns = {@JoinColumn(name="platos_idPlato")},inverseJoinColumns = {@JoinColumn(name="tipo_plato_idTipo")})
    private Set<Tipo_plato> tipo_plato=new HashSet();
        
    @OneToMany(mappedBy="primaryKey.plato",cascade=CascadeType.ALL)
    private Set<Platos_has_carta> platos_has_carta = new HashSet<Platos_has_carta>();
    
    @OneToMany(mappedBy="primaryKey.plato",
            cascade=CascadeType.ALL)
    private Set<Menu_has_platos> menu_has_platos = new HashSet<Menu_has_platos>();

    public Platos() {
    }

    public Platos(String nombre, double precioTapa, double precioMedia, double precioRacion, Empleados empleado) {
        this.nombre = nombre;
        this.precioTapa = precioTapa;
        this.precioMedia = precioMedia;
        this.precioRacion = precioRacion;
        this.empleados = empleado;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioTapa() {
        return precioTapa;
    }

    public void setPrecioTapa(double precioTapa) {
        this.precioTapa = precioTapa;
    }

    public double getPrecioMedia() {
        return precioMedia;
    }

    public void setPrecioMedia(double precioMedia) {
        this.precioMedia = precioMedia;
    }

    public double getPrecioRacion() {
        return precioRacion;
    }

    public void setPrecioRacion(double precioRacion) {
        this.precioRacion = precioRacion;
    }

    public Empleados getEmpleado() {
        return empleados;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleados = empleado;
    }

    public Set<Tipo_plato> getTipo_plato() {
        return tipo_plato;
    }

    public void setTipo_plato(Set<Tipo_plato> tipo_plato) {
        this.tipo_plato = tipo_plato;
    }

    public Set<Platos_has_carta> getPlatos_has_carta() {
        return platos_has_carta;
    }

    public void setPlatos_has_carta(Set<Platos_has_carta> platos_has_carta) {
        this.platos_has_carta = platos_has_carta;
    }

    public Set<Menu_has_platos> getMenu_has_platos() {
        return menu_has_platos;
    }

    public void setMenu_has_platos(Set<Menu_has_platos> menu_has_platos) {
        this.menu_has_platos = menu_has_platos;
    }
    
    
}
