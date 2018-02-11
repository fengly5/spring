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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="carta")
public class Carta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcarta")
    private int idcarta;
    
    @Column(name="noombre")
    private String noombre;
    
    @OneToMany(mappedBy="primaryKey.carta",cascade=CascadeType.ALL)
    private Set<Platos_has_carta> platos_has_carta;

    public Carta() {
        this.platos_has_carta = new HashSet<>();
    }
}
