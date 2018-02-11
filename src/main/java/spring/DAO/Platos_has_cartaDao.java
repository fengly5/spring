/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
import spring.model.Platos_has_carta;

/**
 *
 * @author jcpm0
 */
public interface Platos_has_cartaDao {
  public void addPlatos_has_carta(Platos_has_carta c);
	public void updatePlatos_has_carta(Platos_has_carta c);
	public List<Platos_has_carta> listPlatos_has_cartas();
	public Platos_has_carta getPlatos_has_cartaById(int id);
	public void removePlatos_has_carta(int id);  
}
