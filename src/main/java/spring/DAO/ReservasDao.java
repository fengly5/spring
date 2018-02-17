/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
import spring.model.Reservas;

/**
 *
 * @author jcpm0
 */
public interface ReservasDao {
   public void addReservas(Reservas c);
	public void updateReservas(Reservas c);
	public List<Reservas> listReservas();
	public Reservas getReservasById(int id);
	public void removeReservas(int id); 
}
