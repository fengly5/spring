/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;


import spring.DAO.Notif_sistemaDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.Notif_sistema;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
public class Notif_sistemaServiceImpl implements Notif_sistemaService{
       
    
   @Autowired 
   private Notif_sistemaDao notif_sistema;
    
    private void setNotif_sistemaDao(Notif_sistemaDao notif_sistema){
        this.notif_sistema=notif_sistema;
    }
    @Override
    @Transactional
    public void addNotif_sistema(Notif_sistema c) {
        this.notif_sistema.addNotif_sistema(c);
    }

    @Override
    @Transactional
    public void updateNotif_sistema(Notif_sistema c) {
        this.notif_sistema.updateNotif_sistema(c);
    }

    @Override
    @Transactional
    public List<Notif_sistema> listNotif_sistemas() {
        return this.notif_sistema.listNotif_sistemas();
    }

    @Override
    @Transactional
    public Notif_sistema getNotif_sistemaById(int id) {
        return this.notif_sistema.getNotif_sistemaById(id);
    }

    @Override
    @Transactional
    public void removeNotif_sistema(int id) {
        this.notif_sistema.removeNotif_sistema(id);
    } 
}
