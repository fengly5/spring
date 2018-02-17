/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.CartaDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.Carta;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
public class CartaServiceImpl implements CartaService{
    
    @Autowired
    private CartaDao carta;
    
    private void setCartaDao(CartaDao carta){
        this.carta=carta;
    }
    @Override
    @Transactional
    public void addCarta(Carta c) {
        this.carta.addCarta(c);
    }

    @Override
    @Transactional
    public void updateCarta(Carta c) {
        this.carta.updateCarta(c);
    }

    @Override
    @Transactional
    public List<Carta> listCartas() {
        return this.carta.listCartas();
    }

    @Override
    @Transactional
    public Carta getCartaById(int id) {
        return this.carta.getCartaById(id);
    }

    @Override
    @Transactional
    public void removeCarta(int id) {
        this.carta.removeCarta(id);
    }
    
}
