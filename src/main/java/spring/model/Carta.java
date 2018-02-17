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
    
    @OneToMany(mappedBy="primaryKey.carta",cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Platos_has_carta> platos_has_carta;

    public Carta() {
        this.platos_has_carta = new HashSet<>();
    }

  public Carta(String noombre, Set<Platos_has_carta> platos_has_carta) {
    this.noombre = noombre;
    this.platos_has_carta = platos_has_carta;
  }

  @Override
  public String toString() {
    return "Carta{" + "idcarta=" + idcarta + ", noombre=" + noombre + ", platos_has_carta=" + platos_has_carta + '}';
  }
    

  public int getIdcarta() {
    return idcarta;
  }

  public void setIdcarta(int idcarta) {
    this.idcarta = idcarta;
  }

  public String getNoombre() {
    return noombre;
  }

  public void setNoombre(String noombre) {
    this.noombre = noombre;
  }

  public Set<Platos_has_carta> getPlatos_has_carta() {
    return platos_has_carta;
  }

  public void setPlatos_has_carta(Set<Platos_has_carta> platos_has_carta) {
    this.platos_has_carta = platos_has_carta;
  }
    
}
