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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author jcpm0
 */

@Entity
@Table(name="tipo_notif")
public class Tipo_notif implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idtipo_notif")
    private int idtipo_notif;
    
    @Column(name="tipo")
    private String tipo;
    
    @OneToMany(mappedBy = "tipo_notif",cascade={CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Notif_sistema> notif_sistema = new HashSet<Notif_sistema>();

    public Tipo_notif() {
    }

  public Tipo_notif(String tipo) {
    this.tipo = tipo;
  }

  public Set<Notif_sistema> getNotif_sistema() {
    return notif_sistema;
  }

  public void setNotif_sistema(Set<Notif_sistema> notif_sistema) {
    this.notif_sistema = notif_sistema;
  }

    

    public int getIdtipo_notif() {
        return idtipo_notif;
    }

    public void setIdtipo_notif(int idtipo_notif) {
        this.idtipo_notif = idtipo_notif;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

  @Override
  public String toString() {
    return "Tipo_notif{" + "idtipo_notif=" + idtipo_notif + ", tipo=" + tipo + ", notif_sistema=" + notif_sistema + '}';
  }

}

