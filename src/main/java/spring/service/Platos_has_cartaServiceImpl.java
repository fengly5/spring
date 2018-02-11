/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.Platos_has_cartaDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.Platos_has_carta;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
public class Platos_has_cartaServiceImpl implements Platos_has_cartaService{
        
    @Autowired
    private Platos_has_cartaDao carta;
    
    private void setPlatos_has_cartaDao(Platos_has_cartaDao carta){
        this.carta=carta;
    }
    @Override
    @Transactional
    public void addPlatos_has_carta(Platos_has_carta c) {
        this.carta.addPlatos_has_carta(c);
    }

    @Override
    @Transactional
    public void updatePlatos_has_carta(Platos_has_carta c) {
        this.carta.updatePlatos_has_carta(c);
    }

    @Override
    @Transactional
    public List<Platos_has_carta> listPlatos_has_cartas() {
        return this.carta.listPlatos_has_cartas();
    }

    @Override
    @Transactional
    public Platos_has_carta getPlatos_has_cartaById(int id) {
        return this.carta.getPlatos_has_cartaById(id);
    }

    @Override
    @Transactional
    public void removePlatos_has_carta(int id) {
        this.carta.removePlatos_has_carta(id);
    }
}
