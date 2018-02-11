/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jcpm0
 */
@Entity
@Table(name = "menu_has_platos")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.menu", 
		joinColumns = @JoinColumn(name = "menu_idmenu")),
	@AssociationOverride(name = "primaryKey.plato", 
		joinColumns = @JoinColumn(name = "platos_idPlato")) })
public class Menu_has_platos implements Serializable {
    
   
    private Menu_has_platosId primaryKey = new Menu_has_platosId();
    private String tipo;
    
    @EmbeddedId
    public Menu_has_platosId getPrimaryKey(){
        return primaryKey;
    }
    public void setPrimaryKey(Menu_has_platosId primaryKey){
        this.primaryKey=primaryKey;
    }
    
    @Transient
    public Menu getMenu(){
        return getPrimaryKey().getMenu();
        
    }    
    public void setMenu(Menu menu){
        getPrimaryKey().setMenu(menu);
    }
    
    @Transient
    public Platos getPlato(){
        return getPrimaryKey().getPlato();
    }
    public void setPlato(Platos plato){
        getPrimaryKey().setPlato(plato);
    }

    public Menu_has_platos() {
    }

    public Menu_has_platos(String tipo) {
        this.tipo = tipo;
    }

    @Column(name="tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
