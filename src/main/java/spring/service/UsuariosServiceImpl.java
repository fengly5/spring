/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.UsuariosDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.Usuarios;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
@Service
public class UsuariosServiceImpl implements UsuariosService{
        
    @Autowired
    private UsuariosDao usuario;
    
    private void setUsuariosDao(UsuariosDao usuario){
        this.usuario=usuario;
    }
    @Override
    @Transactional
    public void addUsuarios(Usuarios c) {
        this.usuario.addUsuarios(c);
    }

    @Override
    @Transactional
    public void updateUsuarios(Usuarios c) {
        this.usuario.updateUsuarios(c);
    }

    @Override
    @Transactional
    public List<Usuarios> listUsuarios() {
        return this.usuario.listUsuarios();
    }

    @Override
    @Transactional
    public Usuarios getUsuariosById(int id) {
        return this.usuario.getUsuariosById(id);
    }

    @Override
    @Transactional
    public void removeUsuarios(int id) {
        this.usuario.removeUsuarios(id);
    }
}
