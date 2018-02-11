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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="tipo_plato")
public class Tipo_plato implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTipo")
    private int idTipo;
    
    @Column(name="tipo")
    private String tipo;
    
        @ManyToMany(cascade = {CascadeType.ALL},mappedBy="tipo_plato")
    private Set<Platos> plato=new HashSet();

    public Tipo_plato(String tipo) {
        this.tipo = tipo;
    }

    public Tipo_plato() {
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Platos> getPlato() {
        return plato;
    }

    public void setPlato(Set<Platos> plato) {
        this.plato = plato;
    }
        
        
}
