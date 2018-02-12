/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.List;
import spring.model.Usuarios;

/**
 *
 * @author jcpm0
 */
public interface UsuariosService {
            public void addUsuarios(Usuarios c);
	public void updateUsuarios(Usuarios c);
	public List<Usuarios> listUsuarios();
	public Usuarios getUsuariosById(int id);
	public void removeUsuarios(int id);
}
