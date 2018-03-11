/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import org.jboss.logging.Logger;

/**
 *
 * @author jcpm0
 */
@Embeddable
public class Platos_has_cartaId implements Serializable {
    
    private static final Logger LOG = 
          Logger.getLogger(Platos_has_cartaId.class.getName()); 
    private Platos plato;
    
    
    private Carta carta;


    @ManyToOne (cascade=CascadeType.ALL)
    public Platos getPlato() {
//       LOG.info("phcId getPlato");
               
        return plato;
    }

    public void setPlato(Platos plato) {
//      LOG.info("phcId setPlato");
        this.plato = plato;
    }
    @ManyToOne (cascade=CascadeType.ALL)
    public Carta getCarta() {
//      LOG.info("phcId getCarta");  
      return carta;
    }

    public void setCarta(Carta carta) {
//      LOG.info("phcId setCarta");  
        this.carta = carta;
    }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 29 * hash + Objects.hashCode(this.plato);
    hash = 29 * hash + Objects.hashCode(this.carta);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Platos_has_cartaId other = (Platos_has_cartaId) obj;
    if (!Objects.equals(this.plato, other.plato)) {
      return false;
    }
    if (!Objects.equals(this.carta, other.carta)) {
      return false;
    }
    return true;
  }
    
    
}
