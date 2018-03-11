/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.validacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import spring.model.Menu;

/**
 *
 * @author jcpm0
 */
public class MenuDetalle {
 
  private List<Plato> primeros = new ArrayList<>();
  private List<Plato> segundos= new ArrayList<>();
  private List<Plato> postres= new ArrayList<>();
  private Menu menu;



  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  @Override
  public String toString() {
    return "MenuDetalle{" + "primeros=" + primeros + ", segundos=" + segundos + ", postres=" + postres + ", menu=" + menu + '}';
  }


  public List<Plato> getPrimeros() {
    return primeros;
  }

  public void setPrimeros(List<Plato> primeros) {
    this.primeros = primeros;
  }

  public List<Plato> getSegundos() {
    return segundos;
  }

  public void setSegundos(List<Plato> segundos) {
    this.segundos = segundos;
  }

  public List<Plato> getPostres() {
    return postres;
  }

  public void setPostres(List<Plato> postres) {
    this.postres = postres;
  }

  
  


  public static class Plato {
    private String plato;
    private int idplato;

    public int getIdplato() {
      return idplato;
    }

    public void setIdplato(int idplato) {
      this.idplato = idplato;
    }

    @Override
    public String toString() {
      return "Plato{" + "plato=" + plato + ", idplato=" + idplato + '}';
    }

   
    
    public Plato() {
    }

    public String getPlato() {
      return plato;
    }

    public void setPlato(String plato) {
      this.plato = plato;
    }
    
  }


  
  
}
