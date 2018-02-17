/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.ClientesDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.Clientes;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
public class ClientesServiceImpl implements ClientesService {
    
    @Autowired    
    private ClientesDao cliente;
    
    private void setClientesDao(ClientesDao cliente){
        this.cliente=cliente;
    }
    @Override
    @Transactional
    public void addClientes(Clientes c) {
        this.cliente.addClientes(c);
    }

    @Override
    @Transactional
    public void updateClientes(Clientes c) {
        this.cliente.updateClientes(c);
    }

    @Override
    @Transactional
    public List<Clientes> listClientes() {
        return this.cliente.listClientes();
    }

    @Override
    @Transactional
    public Clientes getClientesById(int id) {
        return this.cliente.getClientesById(id);
    }

    @Override
    @Transactional
    public void removeClientes(int id) {
        this.cliente.removeClientes(id);
    }
}
