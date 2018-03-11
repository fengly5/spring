/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.ArrayList;
import java.util.HashSet;
import spring.DAO.PlatosDao;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.Platos;
import org.springframework.transaction.annotation.Transactional;
import spring.model.Tipo_plato;

/**
 *
 * @author jcpm0
 */
@Service
public class PlatosServiceImpl implements PlatosService {
        
    @Autowired
    private PlatosDao plato;
    
    private void setPlatosDao(PlatosDao plato){
        this.plato=plato;
    }
    @Override
    @Transactional
    public void addPlatos(Platos c) {
        this.plato.addPlatos(c);
    }

    @Override
    @Transactional
    public void updatePlatos(Platos c) {
        this.plato.updatePlatos(c);
    }

    @Override
    @Transactional
    public List<Platos> listPlatos() {
        return this.plato.listPlatos();
    }

    @Override
    @Transactional
    public Platos getPlatosById(int id) {
        return this.plato.getPlatosById(id);
    }

    @Override
    @Transactional
    public void removePlatos(int id) {
        this.plato.removePlatos(id);
    }

  @Transactional
    @Override
  public List<Platos> listaPlatosCarta(Integer offset, Integer maxResults) {
    return this.plato.listarPlatosCarta(offset, maxResults);
  }
  @Transactional
    @Override
    public List<Platos> listaPlatosCarta() {
    return this.plato.listarPlatosCarta();
  }

  @Override
  public List<Platos> listarPlatosMenu() {
    return this.plato.listarPlatosMenu();
  }
}
