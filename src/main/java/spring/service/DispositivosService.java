/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.List;
import spring.model.Dispositivos;

/**
 *
 * @author jcpm0
 */
public interface DispositivosService {
           public void addDispositivos(Dispositivos c);
	public void updateDispositivos(Dispositivos c);
	public List<Dispositivos> listDispositivos();
	public Dispositivos getDispositivosById(int id);
	public void removeDispositivos(int id); 
}
