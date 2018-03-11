/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.Tipo_platoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import spring.model.Tipo_plato;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
@Service
public class Tipo_platoServiceImpl implements Tipo_platoService {
       
    @Autowired
    private Tipo_platoDao tipo_plato;
    
    private void setTipo_platoDao(Tipo_platoDao tipo_plato){
        this.tipo_plato=tipo_plato;
    }
    @Override
    @Transactional
    public void addTipo_plato(Tipo_plato c) {
        this.tipo_plato.addTipo_plato(c);
    }

    @Override
    @Transactional
    public void updateTipo_plato(Tipo_plato c) {
        this.tipo_plato.updateTipo_plato(c);
    }

    @Override
    @Transactional
    public List<Tipo_plato> listTipo_platos() {
        return this.tipo_plato.listTipo_platos();
    }

    @Override
    @Transactional
    public Tipo_plato getTipo_platoById(int id) {
        return this.tipo_plato.getTipo_platoById(id);
    }

    @Override
    @Transactional
    public void removeTipo_plato(int id) {
        this.tipo_plato.removeTipo_plato(id);
    } 
}
