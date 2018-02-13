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
import spring.model.Menu;

/**
 *
 * @author jcpm0
 */
@Repository
public class MenuDAOImpl implements MenuDao {
    
   
    @Autowired
    private SessionFactory sessionFactory;

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
    
    
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Menu.
     */
    @Override
    public void addMenu(Menu c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla menu.
     * @param c Recibe un objeto de tipo menu.
     */
    @Override
    public void updateMenu(Menu c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Menu
     * @return Devuelve una lista con los objetos Menu.
     */
    @Override
    public List<Menu> listMenus() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Menu> query =sessionFactory.getCurrentSession()
               .createQuery("from Menu");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla menu por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Menu.
     */
    @Override
    public Menu getMenuById(int id) {
            
       return sessionFactory.getCurrentSession().load(Menu.class, id);
    }
    /**
     * Método para borrar un registro de la tabla menu por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeMenu(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Menu c = sesion.load(Menu.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
}
