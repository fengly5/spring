/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import spring.model.Platos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.model.Platos;

/**
 *
 * @author jcpm0
 */
@Repository
public class PlatosDAOImpl implements PlatosDao {
    
   
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Platos.
     */
    @Override
    public void addPlatos(Platos c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla platos.
     * @param c Recibe un objeto de tipo platos.
     */
    @Override
    public void updatePlatos(Platos c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Platos
     * @return Devuelve una lista con los objetos Platos.
     */
    @Override
    public List<Platos> listPlatos() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Platos> query =
               sessionFactory.getCurrentSession().createQuery("from Platos");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla platos por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Platos.
     */
    @Override
    public Platos getPlatosById(int id) {
            
       return sessionFactory.getCurrentSession().load(Platos.class, id);
    }
    /**
     * Método para borrar un registro de la tabla platos por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removePlatos(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Platos c = sesion.load(Platos.class, id);
       if(c != null){
           sesion.delete(c);
           sesion.flush();
       }
    }
}
