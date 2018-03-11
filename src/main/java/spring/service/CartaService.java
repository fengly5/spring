/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.List;
import spring.model.Carta;

/**
 *
 * @author jcpm0
 */
public interface CartaService {
        public void addCarta(Carta c);
	public void updateCarta(Carta c);
	public List<Carta> listCartas();
        public List<Carta> listCartas(Integer offset,Integer maxResults);
	public Carta getCartaById(int id);
	public void removeCarta(int id);
}
