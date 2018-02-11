/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.List;
import spring.model.Notificaciones;

/**
 *
 * @author jcpm0
 */
public interface NotificacionesService {
            public void addNotificaciones(Notificaciones c);
	public void updateNotificaciones(Notificaciones c);
	public List<Notificaciones> listNotificaciones();
	public Notificaciones getNotificacionesById(int id);
	public void removeNotificaciones(int id);
}
