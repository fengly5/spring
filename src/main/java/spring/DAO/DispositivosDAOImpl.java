/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;


import java.util.List;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.model.Dispositivos;

/**
 *
 * @author jcpm0
 */
@Repository
public class DispositivosDAOImpl implements DispositivosDao {
    
   
   
    private SessionFactory sessionFactory;
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Dispositivos.
     */
    @Override
    public void addDispositivos(Dispositivos c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla dispositivos.
     * @param c Recibe un objeto de tipo dispositivos.
     */
    @Override
    public void updateDispositivos(Dispositivos c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Dispositivos
     * @return Devuelve una lista con los objetos Dispositivos.
     */
    @Override
    public List<Dispositivos> listDispositivos() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Dispositivos> query =
            sessionFactory.getCurrentSession().createQuery("from dispositivos");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla dispositivos por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Dispositivos.
     */
    @Override
    public Dispositivos getDispositivosById(int id) {
            
       return sessionFactory.getCurrentSession().load(Dispositivos.class, id);
    }
    /**
     * Método para borrar un registro de la tabla dispositivos por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeDispositivos(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Dispositivos c = sesion.load(Dispositivos.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
}
