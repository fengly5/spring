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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="menu")
public class Menu implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idmenu")
    private int idmenu;
    
    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @OneToMany(mappedBy="primaryKey.menu",
            cascade=CascadeType.ALL)
    private Set<Menu_has_platos> menu_has_platos = new HashSet<Menu_has_platos>();

    public Menu() {
    }

    public Menu(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Menu_has_platos> getMenu_has_platos() {
        return menu_has_platos;
    }

    public void setMenu_has_platos(Set<Menu_has_platos> menu_has_platos) {
        this.menu_has_platos = menu_has_platos;
    }
    
}
