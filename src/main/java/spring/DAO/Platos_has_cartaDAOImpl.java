/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import spring.model.Platos_has_carta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.model.Platos_has_carta;

/**
 *
 * @author jcpm0
 */
@Repository
public class Platos_has_cartaDAOImpl implements Platos_has_cartaDao {
    
   
   
    private SessionFactory sessionFactory;
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Platos_has_carta.
     */
    @Override
    public void addPlatos_has_carta(Platos_has_carta c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla carta.
     * @param c Recibe un objeto de tipo carta.
     */
    @Override
    public void updatePlatos_has_carta(Platos_has_carta c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Platos_has_carta
     * @return Devuelve una lista con los objetos Platos_has_carta.
     */
    @Override
    public List<Platos_has_carta> listPlatos_has_cartas() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Platos_has_carta> query =
        sessionFactory.getCurrentSession().createQuery("from platos_has_carta");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla carta por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Platos_has_carta.
     */
    @Override
    public Platos_has_carta getPlatos_has_cartaById(int id) {
            
       return sessionFactory.getCurrentSession().load(Platos_has_carta.class, id);
    }
    /**
     * Método para borrar un registro de la tabla carta por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removePlatos_has_carta(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Platos_has_carta c = sesion.load(Platos_has_carta.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
}
