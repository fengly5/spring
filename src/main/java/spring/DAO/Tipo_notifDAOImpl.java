/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import spring.model.Tipo_notif;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.model.Tipo_notif;

/**
 *
 * @author jcpm0
 */
@Repository
public class Tipo_notifDAOImpl implements Tipo_notifDao {
    
   
 
    private SessionFactory sessionFactory;
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Tipo_notif.
     */
    @Override
    public void addTipo_notif(Tipo_notif c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla carta.
     * @param c Recibe un objeto de tipo carta.
     */
    @Override
    public void updateTipo_notif(Tipo_notif c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Tipo_notif
     * @return Devuelve una lista con los objetos Tipo_notif.
     */
    @Override
    public List<Tipo_notif> listTipo_notifs() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Tipo_notif> query =
             sessionFactory.getCurrentSession().createQuery("from tipo_notif");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla carta por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Tipo_notif.
     */
    @Override
    public Tipo_notif getTipo_notifById(int id) {
            
       return sessionFactory.getCurrentSession().load(Tipo_notif.class, id);
    }
    /**
     * Método para borrar un registro de la tabla carta por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeTipo_notif(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Tipo_notif c = sesion.load(Tipo_notif.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
}
