/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import spring.model.Notificaciones;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.model.Notificaciones;

/**
 *
 * @author jcpm0
 */
@Repository
public class NotificacionesDAOImpl implements NotificacionesDao {
    
   
    
    private SessionFactory sessionFactory;
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Notificaciones.
     */
    @Override
    public void addNotificaciones(Notificaciones c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla notificaciones.
     * @param c Recibe un objeto de tipo notificaciones.
     */
    @Override
    public void updateNotificaciones(Notificaciones c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Notificaciones
     * @return Devuelve una lista con los objetos Notificaciones.
     */
    @Override
    public List<Notificaciones> listNotificaciones() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Notificaciones> query =
         sessionFactory.getCurrentSession().createQuery("from notificaciones");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla notificaciones por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Notificaciones.
     */
    @Override
    public Notificaciones getNotificacionesById(int id) {
            
       return sessionFactory.getCurrentSession().load(Notificaciones.class, id);
    }
    /**
     * Método para borrar un registro de la tabla notificaciones por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeNotificaciones(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Notificaciones c = sesion.load(Notificaciones.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
}
