/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.jboss.logging.Logger;

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
       private static final Logger LOG = 
          Logger.getLogger(Platos_has_carta.class.getName()); 
 
    
   @JsonIgnore
    private Platos_has_cartaId primaryKey = new Platos_has_cartaId();
    
    private int aparece;

     @EmbeddedId
    public Platos_has_cartaId getPrimaryKey(){
//      LOG.info("PHC: getPrimaryKey");
        return primaryKey;
    }
   
    public void setPrimaryKey(Platos_has_cartaId primaryKey){
//      LOG.info("PHC: setPrimaryKey");
      this.primaryKey=primaryKey;
    }
    @Transient
    public Platos getPlato(){
//        LOG.info("PHC: getPlato");
        return getPrimaryKey().getPlato();
    }
    public void setPlato(Platos plato){
//      LOG.info("PHC: setPlato");
        getPrimaryKey().setPlato(plato);
    }
    @Transient 
    public Carta getCarta(){
//      LOG.info("PHC: getCarta");
        return getPrimaryKey().getCarta();
    }
    
    public void setCarta(Carta carta){
//      LOG.info("PHC: setCarta");
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
