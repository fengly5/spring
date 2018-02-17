/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.List;
import spring.model.Menu;

/**
 *
 * @author jcpm0
 */
public interface MenuService {
           public void addMenu(Menu c);
	public void updateMenu(Menu c);
	public List<Menu> listMenus();
	public Menu getMenuById(int id);
	public void removeMenu(int id); 
}
