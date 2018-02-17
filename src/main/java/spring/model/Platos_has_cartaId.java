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
public class Platos_has_cartaId implements Serializable {
    
    private Platos plato;
    
    
    private Carta carta;


    @ManyToOne (cascade=CascadeType.ALL)
    public Platos getPlato() {
        return plato;
    }

    public void setPlato(Platos plato) {
        this.plato = plato;
    }
    @ManyToOne (cascade=CascadeType.ALL)
    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }
    
    
}
