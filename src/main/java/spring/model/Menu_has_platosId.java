/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.io.Serializable;
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
    
    
    
}
