/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import spring.model.Notificaciones;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.model.Notificaciones;

/**
 *
 * @author jcpm0
 */
@Repository
public class NotificacionesDAOImpl implements NotificacionesDao {
        private static final Logger LOG = Logger.
    getLogger(NotificacionesDAOImpl.class.getName());
   
    @Autowired
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
         sessionFactory.getCurrentSession().createQuery("from Notificaciones order by fecha asc");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla notificaciones por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Notificaciones.
     */
    @Override
    public Notificaciones getNotificacionesById(int id) {
           Notificaciones n=null;
           try {
             n = sessionFactory.getCurrentSession().load(Notificaciones.class, id);
      } catch (HibernateException e) {
        return null;
      }
           
       return n;
    }
    /**
     * Método para borrar un registro de la tabla notificaciones por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    @Transactional
    public void removeNotificaciones(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Notificaciones c = sesion.load(Notificaciones.class, id);
       LOG.info("c: "+c.getMensaje());
       if(c != null){
         LOG.info("entra if");
           sesion.remove(c);
          sesion.flush();
       }
    }
    @Override
    public List<Notificaciones> listNotificaciones(Integer offset,Integer maxResults){
         String hql1="from Notificaciones order by fecha desc";
              List<Notificaciones> result= new ArrayList<Notificaciones>();
      try {
        Session session = sessionFactory.getCurrentSession();
       
      
        Query query;
        query= session.createQuery(hql1);
        
        query.setFirstResult(offset!=null?offset:0);
        query.setMaxResults(maxResults!=null?maxResults:10);
        
        result = query.list();
        

          } catch (HibernateException e) {
              e.printStackTrace();
          }
        return result;
    }

}
