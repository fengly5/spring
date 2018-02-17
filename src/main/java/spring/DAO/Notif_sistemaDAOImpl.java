/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import spring.model.Notif_sistema;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.model.Notif_sistema;

/**
 *
 * @author jcpm0
 */
@Repository
public class Notif_sistemaDAOImpl implements Notif_sistemaDao {
    
   
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Notif_sistema.
     */
    @Override
    public void addNotif_sistema(Notif_sistema c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla Notif_sistema.
     * @param c Recibe un objeto de tipo Notif_sistema.
     */
    @Override
    public void updateNotif_sistema(Notif_sistema c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Notif_sistema
     * @return Devuelve una lista con los objetos Notif_sistema.
     */
    @Override
    public List<Notif_sistema> listNotif_sistemas() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Notif_sistema> query =
           sessionFactory.getCurrentSession().createQuery("from Notif_sistema");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla Notif_sistema por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Notif_sistema.
     */
    @Override
    public Notif_sistema getNotif_sistemaById(int id) {
            
       return sessionFactory.getCurrentSession().load(Notif_sistema.class, id);
    }
    /**
     * Método para borrar un registro de la tabla Notif_sistema por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeNotif_sistema(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Notif_sistema c = sesion.load(Notif_sistema.class, id);
       if(c != null){
           sesion.delete(c);
           sesion.flush();
       }
    }
}
