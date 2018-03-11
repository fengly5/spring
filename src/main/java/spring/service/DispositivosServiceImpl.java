/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.ArrayList;
import spring.DAO.DispositivosDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.Dispositivos;
import org.springframework.transaction.annotation.Transactional;
import spring.model.Clientes;

/**
 *
 * @author jcpm0
 */
@Service
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
    @Override
    @Transactional
    public List<Dispositivos> listaDispositivosCliente(int idCliente){
      List<Dispositivos> lst = this.listDispositivos();
      List<Dispositivos> dClientes = new ArrayList<>();
      for (Dispositivos d:lst){
        if(d.getCliente().getIdclientes() == idCliente){
          dClientes.add(d);
        }
      }
      return dClientes;
    }

  @Override
  public List<Clientes> clientesDispositivoNotificado() {
    return this.dispositivo.clientesDispositivoNotificado();
  }
}
