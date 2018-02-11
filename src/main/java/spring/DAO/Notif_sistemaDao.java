/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
import spring.model.Notif_sistema;

/**
 *
 * @author jcpm0
 */
public interface Notif_sistemaDao {
   public void addNotif_sistema(Notif_sistema c);
	public void updateNotif_sistema(Notif_sistema c);
	public List<Notif_sistema> listNotif_sistemas();
	public Notif_sistema getNotif_sistemaById(int id);
	public void removeNotif_sistema(int id); 
}
