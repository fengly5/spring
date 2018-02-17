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
@Table(name="platos_has_carta")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.carta",
        joinColumns = @JoinColumn(name = "carta_idcarta")),
    @AssociationOverride(name = "primaryKey.plato",
        joinColumns = @JoinColumn(name = "platos_idPlato ")) })
public class Platos_has_carta implements Serializable {
    
 
    
   
    private Platos_has_cartaId primaryKey = new Platos_has_cartaId();
       private int aparece;

     @EmbeddedId
    public Platos_has_cartaId getPrimaryKey(){
        return primaryKey;
    }
    
    public void setPrimaryKey(Platos_has_cartaId primaryKey){
        this.primaryKey=primaryKey;
    }
    @Transient
    public Platos getPlato(){
        return getPrimaryKey().getPlato();
    }
    public void setPlato(Platos plato){
        getPrimaryKey().setPlato(plato);
    }
    @Transient 
    public Carta getCarta(){
        return getPrimaryKey().getCarta();
    }
    
    public void setCarta(Carta carta){
        getPrimaryKey().setCarta(carta);
    }

    public Platos_has_carta(int aparece) {
        this.aparece = aparece;
    }

    public Platos_has_carta() {
    }
 @Column(name="aparece")
    public int getAparece() {
        return aparece;
    }

    public void setAparece(int aparece) {
        this.aparece = aparece;
    }
    
}
