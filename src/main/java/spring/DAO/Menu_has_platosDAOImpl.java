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
import spring.model.Menu_has_platos;
import spring.model.Menu_has_platosId;

/**
 *
 * @author jcpm0
 */
@Repository
public class Menu_has_platosDAOImpl implements Menu_has_platosDao {
    
   
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Menu_has_platos.
     */
    @Override
    public void addMenu_has_platos(Menu_has_platos c) {
       
      sessionFactory.getCurrentSession().merge(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla carta.
     * @param c Recibe un objeto de tipo carta.
     */
    @Override
    public void updateMenu_has_platos(Menu_has_platos c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Menu_has_platos
     * @return Devuelve una lista con los objetos Menu_has_platos.
     */
    @Override
    public List<Menu_has_platos> listMenu_has_platos() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Menu_has_platos> query =
               sessionFactory.getCurrentSession()
                       .createQuery("from Menu_has_platos");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla carta por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Menu_has_platos.
     */
    @Override
    public Menu_has_platos getMenu_has_platosById(Menu_has_platosId id) {
            
      return sessionFactory.getCurrentSession().load(Menu_has_platos.class, id);
    }
    /**
     * Método para borrar un registro de la tabla carta por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeMenu_has_platos(Menu_has_platosId id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Menu_has_platos c = sesion.load(Menu_has_platos.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
}
