/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
import spring.model.Tipo_notif;

/**
 *
 * @author jcpm0
 */
public interface Tipo_notifDao {
    public void addTipo_notif(Tipo_notif c);
	public void updateTipo_notif(Tipo_notif c);
	public List<Tipo_notif> listTipo_notifs();
	public Tipo_notif getTipo_notifById(int id);
	public void removeTipo_notif(int id);
}
