/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import spring.model.Reservas;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import spring.model.Empleados;
import spring.model.Reservas;

/**
 *
 * @author jcpm0
 */
@Repository
public class ReservasDAOImpl implements ReservasDao {
    
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public ReservasDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Reservas.
     */
    @Override
    public void addReservas(Reservas c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla reservas.
     * @param c Recibe un objeto de tipo reservas.
     */
    @Override
    public void updateReservas(Reservas c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Reservas
     * @return Devuelve una lista con los objetos Reservas.
     */
    @Override
    public List<Reservas> listReservas() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Reservas> query;
      query = sessionFactory.getCurrentSession().createQuery("from Reservas order by fechaHora desc");
       return query.getResultList();
       
    }
    /**
     * Método para obtener un registro de la tabla reservas por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Reservas.
     */
    @Override
    public Reservas getReservasById(int id) {
            
       return sessionFactory.getCurrentSession().load(Reservas.class, id);
    }
    /**
     * Método para borrar un registro de la tabla reservas por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeReservas(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Reservas c = sesion.load(Reservas.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
}
