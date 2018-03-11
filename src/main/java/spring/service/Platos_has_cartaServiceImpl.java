/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import spring.DAO.Platos_has_cartaDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.Platos_has_carta;
import org.springframework.transaction.annotation.Transactional;
import spring.model.Platos_has_cartaId;

/**
 *
 * @author jcpm0
 */
@Service
@Transactional
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
    public List<Platos_has_carta> listPlatos_has_cartas(int id) {
      
      List<Platos_has_carta> lst = this.carta.listPlatos_has_cartas();
      List<Platos_has_carta> result= new ArrayList<>();
      for (Platos_has_carta phc:lst){
        if(phc.getCarta().getIdcarta() == id){
          result.add(phc);
        }
      }
      
      return result;
    }

    @Override
    @Transactional
    public Platos_has_carta getPlatos_has_cartaById(Platos_has_cartaId  id) {
        return this.carta.getPlatos_has_cartaById(id);
    }

    @Override
    @Transactional
    public void removePlatos_has_carta(Platos_has_cartaId  id) {
        this.carta.removePlatos_has_carta(id);
    }
    
    @Override
    public Map<String,Object> convierteMap(List<Platos_has_carta> lista){
      Map<String,Object> map = new HashMap<String, Object>();
      
      for (Platos_has_carta p:lista){
        if(p.getAparece() == 1){
          map.put("id", p.getPrimaryKey().getPlato().getIdPlato());
          map.put("nombre", p.getPrimaryKey().getPlato().getNombre());
          map.put("precioTapa", p.getPrimaryKey().getPlato().getPrecioTapa());
          map.put("precioMedia", p.getPrimaryKey().getPlato().getPrecioMedia());
          map.put("precioRacion", p.getPrimaryKey().getPlato().getPrecioRacion());
        }
      }
        return map;
    }
}
