/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.Tipo_notifDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.Tipo_notif;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
public class Tipo_notifServiceImpl implements Tipo_notifService {
        
    @Autowired
    private Tipo_notifDao tipo_notif;
    
    private void setTipo_notifDao(Tipo_notifDao tipo_notif){
        this.tipo_notif=tipo_notif;
    }
    @Override
    @Transactional
    public void addTipo_notif(Tipo_notif c) {
        this.tipo_notif.addTipo_notif(c);
    }

    @Override
    @Transactional
    public void updateTipo_notif(Tipo_notif c) {
        this.tipo_notif.updateTipo_notif(c);
    }

    @Override
    @Transactional
    public List<Tipo_notif> listTipo_notifs() {
        return this.tipo_notif.listTipo_notifs();
    }

    @Override
    @Transactional
    public Tipo_notif getTipo_notifById(int id) {
        return this.tipo_notif.getTipo_notifById(id);
    }

    @Override
    @Transactional
    public void removeTipo_notif(int id) {
        this.tipo_notif.removeTipo_notif(id);
    }
}
