/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.DispositivosDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.Dispositivos;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
public class DispositivosServiceImpl implements DispositivosService {
    
    @Autowired    
    private DispositivosDao dispositivo;
    
    private void setDispositivosDao(DispositivosDao dispositivo){
        this.dispositivo=dispositivo;
    }
    @Override
    @Transactional
    public void addDispositivos(Dispositivos c) {
        this.dispositivo.addDispositivos(c);
    }

    @Override
    @Transactional
    public void updateDispositivos(Dispositivos c) {
        this.dispositivo.updateDispositivos(c);
    }

    @Override
    @Transactional
    public List<Dispositivos> listDispositivos() {
        return this.dispositivo.listDispositivos();
    }

    @Override
    @Transactional
    public Dispositivos getDispositivosById(int id) {
        return this.dispositivo.getDispositivosById(id);
    }

    @Override
    @Transactional
    public void removeDispositivos(int id) {
        this.dispositivo.removeDispositivos(id);
    }
}
