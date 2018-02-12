/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import spring.model.Clientes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.model.Clientes;

/**
 *
 * @author jcpm0
 */
@Repository
public class ClientesDAOImpl implements ClientesDao {
    
   
  
    private SessionFactory sessionFactory;
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Clientes.
     */
    @Override
    public void addClientes(Clientes c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla carta.
     * @param c Recibe un objeto de tipo carta.
     */
    @Override
    public void updateClientes(Clientes c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Clientes
     * @return Devuelve una lista con los objetos Clientes.
     */
    @Override
    public List<Clientes> listClientes() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Clientes> query =sessionFactory.getCurrentSession().createQuery("from carta");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla carta por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Clientes.
     */
    @Override
    public Clientes getClientesById(int id) {
            
       return sessionFactory.getCurrentSession().load(Clientes.class, id);
    }
    /**
     * Método para borrar un registro de la tabla carta por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeClientes(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Clientes c = sesion.load(Clientes.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
}
