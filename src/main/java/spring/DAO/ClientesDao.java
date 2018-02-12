/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
import spring.model.Clientes;

/**
 *
 * @author jcpm0
 */
public interface ClientesDao {
        public void addClientes(Clientes c);
	public void updateClientes(Clientes c);
	public List<Clientes> listClientes();
	public Clientes getClientesById(int id);
	public void removeClientes(int id);
}
