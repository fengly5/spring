/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.List;
import java.util.Map;
import spring.model.Platos_has_carta;
import spring.model.Platos_has_cartaId;

/**
 *
 * @author jcpm0
 */
public interface Platos_has_cartaService {
            public void addPlatos_has_carta(Platos_has_carta c);
	public void updatePlatos_has_carta(Platos_has_carta c);
	public List<Platos_has_carta> listPlatos_has_cartas(int id);
	public Platos_has_carta getPlatos_has_cartaById(Platos_has_cartaId  id);
	public void removePlatos_has_carta(Platos_has_cartaId  id);
        public Map<String,Object> convierteMap(List<Platos_has_carta> lista);
}
