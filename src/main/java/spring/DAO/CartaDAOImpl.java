/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;


import java.util.List;
import javax.persistence.TypedQuery;
import spring.model.Carta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Clase con la implementación de los métodos DAO para la tabla Carta.
 * @author jcpm0
 */
@Repository
public class CartaDAOImpl implements CartaDao {
    
   
    @Autowired
    private SessionFactory sessionFactory;
   
    public void setSessionFactory(SessionFactory sesion){
        this.sessionFactory=sesion;
    }
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Carta.
     */
    @Override
    public void addCarta(Carta c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla carta.
     * @param c Recibe un objeto de tipo carta.
     */
    @Override
    public void updateCarta(Carta c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Carta
     * @return Devuelve una lista con los objetos Carta.
     */
    @Override
    public List<Carta> listCartas() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Carta> query =sessionFactory.getCurrentSession().createQuery("from Carta");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla carta por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Carta.
     */
    @Override
    public Carta getCartaById(int id) {
            
       return sessionFactory.getCurrentSession().load(Carta.class, id);
    }
    /**
     * Método para borrar un registro de la tabla carta por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeCarta(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Carta c = sesion.load(Carta.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
    
}
