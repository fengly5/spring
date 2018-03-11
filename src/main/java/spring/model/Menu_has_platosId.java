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

/**
 *
 * @author jcpm0
 */
@Embeddable 
public class Menu_has_platosId implements Serializable {
    
    private Menu menu;
    private Platos plato;
    
    @ManyToOne(cascade= CascadeType.ALL)
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @ManyToOne(cascade= CascadeType.ALL)
    public Platos getPlato() {
        return plato;
    }

    public void setPlato(Platos plato) {
        this.plato = plato;
    }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 41 * hash + Objects.hashCode(this.menu);
    hash = 41 * hash + Objects.hashCode(this.plato);
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
    final Menu_has_platosId other = (Menu_has_platosId) obj;
    if (!Objects.equals(this.menu, other.menu)) {
      return false;
    }
    if (!Objects.equals(this.plato, other.plato)) {
      return false;
    }
    return true;
  }
    
    
    
}
