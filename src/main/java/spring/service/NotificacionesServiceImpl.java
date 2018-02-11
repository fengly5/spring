/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.NotificacionesDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.Notificaciones;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
public class NotificacionesServiceImpl implements NotificacionesService{
       
    
    @Autowired
    private NotificacionesDao notificacion;
    
    private void setNotificacionesDao(NotificacionesDao notificacion){
        this.notificacion=notificacion;
    }
    @Override
    @Transactional
    public void addNotificaciones(Notificaciones c) {
        this.notificacion.addNotificaciones(c);
    }

    @Override
    @Transactional
    public void updateNotificaciones(Notificaciones c) {
        this.notificacion.updateNotificaciones(c);
    }

    @Override
    @Transactional
    public List<Notificaciones> listNotificaciones() {
        return this.notificacion.listNotificaciones();
    }

    @Override
    @Transactional
    public Notificaciones getNotificacionesById(int id) {
        return this.notificacion.getNotificacionesById(id);
    }

    @Override
    @Transactional
    public void removeNotificaciones(int id) {
        this.notificacion.removeNotificaciones(id);
    } 
}
