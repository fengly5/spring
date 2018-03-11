/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.MenuDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.Menu;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired 
    private MenuDao menu;
    
    private void setMenuDao(MenuDao menu){
        this.menu=menu;
    }
    @Override
    @Transactional
    public void addMenu(Menu c) {
        this.menu.addMenu(c);
    }

    @Override
    @Transactional
    public void updateMenu(Menu c) {
        this.menu.updateMenu(c);
    }

    @Override
    @Transactional
    public List<Menu> listMenus() {
        return this.menu.listMenus();
    }

    @Override
    @Transactional
    public Menu getMenuById(int id) {
        return this.menu.getMenuById(id);
    }

    @Override
    @Transactional
    public void removeMenu(int id) {
        this.menu.removeMenu(id);
    }  

  @Override
  @Transactional
  public List<Menu> listMenus(Integer offset, Integer maxResults) {
    return this.menu.listMenus(offset, maxResults);
  }
}
