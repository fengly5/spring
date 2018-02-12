/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
import spring.model.Tipo_plato;

/**
 *
 * @author jcpm0
 */
public interface Tipo_platoDao {
    public void addTipo_plato(Tipo_plato c);
	public void updateTipo_plato(Tipo_plato c);
	public List<Tipo_plato> listTipo_platos();
	public Tipo_plato getTipo_platoById(int id);
	public void removeTipo_plato(int id);
}
