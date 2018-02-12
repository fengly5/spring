/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import spring.model.Tipo_plato;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.model.Tipo_plato;

/**
 *
 * @author jcpm0
 */
@Repository
public class Tipo_platoDAOImpl implements Tipo_platoDao {
    
   
  
    private SessionFactory sessionFactory;
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Tipo_plato.
     */
    @Override
    public void addTipo_plato(Tipo_plato c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla carta.
     * @param c Recibe un objeto de tipo carta.
     */
    @Override
    public void updateTipo_plato(Tipo_plato c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Tipo_plato
     * @return Devuelve una lista con los objetos Tipo_plato.
     */
    @Override
    public List<Tipo_plato> listTipo_platos() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Tipo_plato> query =
             sessionFactory.getCurrentSession().createQuery("from tipo_plato");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla carta por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Tipo_plato.
     */
    @Override
    public Tipo_plato getTipo_platoById(int id) {
            
       return sessionFactory.getCurrentSession().load(Tipo_plato.class, id);
    }
    /**
     * Método para borrar un registro de la tabla carta por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeTipo_plato(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Tipo_plato c = sesion.load(Tipo_plato.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
}
