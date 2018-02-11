/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import spring.model.Usuarios;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.model.Usuarios;

/**
 *
 * @author jcpm0
 */
@Repository
public class UsuariosDAOImpl implements UsuariosDao {
    
   
   
    private SessionFactory sessionFactory;
    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Usuarios.
     */
    @Override
    public void addUsuarios(Usuarios c) {
       
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla carta.
     * @param c Recibe un objeto de tipo carta.
     */
    @Override
    public void updateUsuarios(Usuarios c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Usuarios
     * @return Devuelve una lista con los objetos Usuarios.
     */
    @Override
    public List<Usuarios> listUsuarios() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Usuarios> query =
               sessionFactory.getCurrentSession().createQuery("from usuarios");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla carta por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Usuarios.
     */
    @Override
    public Usuarios getUsuariosById(int id) {
            
       return sessionFactory.getCurrentSession().load(Usuarios.class, id);
    }
    /**
     * Método para borrar un registro de la tabla carta por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeUsuarios(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Usuarios c = sesion.load(Usuarios.class, id);
       if(c != null){
           sesion.remove(c);
       }
    }
}
