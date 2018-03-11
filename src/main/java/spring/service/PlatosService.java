/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.List;
import spring.model.Platos;

/**
 *
 * @author jcpm0
 */
public interface PlatosService {
          public void addPlatos(Platos c);
	public void updatePlatos(Platos c);
	public List<Platos> listPlatos();
	public Platos getPlatosById(int id);
	public void removePlatos(int id);  
        public List<Platos> listaPlatosCarta();
        public List<Platos>listaPlatosCarta(Integer offset,Integer maxResults);
        public List<Platos> listarPlatosMenu();
}
