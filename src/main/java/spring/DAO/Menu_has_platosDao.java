/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
import spring.model.Menu_has_platos;
import spring.model.Menu_has_platosId;

/**
 *
 * @author jcpm0
 */
public interface Menu_has_platosDao {
  public void addMenu_has_platos(Menu_has_platos c);
	public void updateMenu_has_platos(Menu_has_platos c);
	public List<Menu_has_platos> listMenu_has_platos();
	public Menu_has_platos getMenu_has_platosById(Menu_has_platosId id);
	public void removeMenu_has_platos(Menu_has_platosId id);  
}
