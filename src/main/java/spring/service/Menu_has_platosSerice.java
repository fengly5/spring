/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.List;
import spring.model.Menu_has_platos;

/**
 *
 * @author jcpm0
 */
public interface Menu_has_platosSerice {
            public void addMenu_has_platos(Menu_has_platos c);
	public void updateMenu_has_platos(Menu_has_platos c);
	public List<Menu_has_platos> listMenu_has_platos();
	public Menu_has_platos getMenu_has_platosById(int id);
	public void removeMenu_has_platos(int id);
}
