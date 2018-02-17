/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.Menu_has_platosDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.Menu_has_platos;
import org.springframework.transaction.annotation.Transactional;
import spring.model.Menu_has_platosId;

/**
 *
 * @author jcpm0
 */
public class Menu_has_platosServiceImpl implements Menu_has_platosSerice{
       
    @Autowired
    private Menu_has_platosDao menu_has_plato;
    
    private void setMenu_has_platosDao(Menu_has_platosDao menu_has_plato){
        this.menu_has_plato=menu_has_plato;
    }
    @Override
    @Transactional
    public void addMenu_has_platos(Menu_has_platos c) {
        this.menu_has_plato.addMenu_has_platos(c);
    }

    @Override
    @Transactional
    public void updateMenu_has_platos(Menu_has_platos c) {
        this.menu_has_plato.updateMenu_has_platos(c);
    }

    @Override
    @Transactional
    public List<Menu_has_platos> listMenu_has_platos() {
        return this.menu_has_plato.listMenu_has_platos();
    }

    @Override
    @Transactional
    public Menu_has_platos getMenu_has_platosById(Menu_has_platosId id) {
        return this.menu_has_plato.getMenu_has_platosById(id);
    }

    @Override
    @Transactional
    public void removeMenu_has_platos(Menu_has_platosId id) {
        this.menu_has_plato.removeMenu_has_platos(id);
    }
}
