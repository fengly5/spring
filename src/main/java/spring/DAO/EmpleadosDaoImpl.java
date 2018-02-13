/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;


import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import spring.model.Empleados;

/**
 *
 * @author jcpm0
 */
@Repository
public class EmpleadosDaoImpl implements EmpleadosDao {
    
   
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public EmpleadosDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Método para añadir un nuevo registro
     * @param c  Recibe un objeto de tipo Empleados.
     */
    @Override
    public void addEmpleados(Empleados c) {
       System.out.println("entra en addempleados");
      sessionFactory.getCurrentSession().save(c);
      
    }
    
    /**
     * Método para actualizar un registro en la tabla empleados.
     * @param c Recibe un objeto de tipo empleados.
     */
    @Override
    public void updateEmpleados(Empleados c) {
       sessionFactory.getCurrentSession().update(c);
    }
    /**
     * Método DAO para listar todos los elementos de la tabla Empleados
     * @return Devuelve una lista con los objetos Empleados.
     */
    @Override
    public List<Empleados> listEmpleados() {
       @SuppressWarnings("unchecked") 
       TypedQuery<Empleados> query =
               sessionFactory.getCurrentSession().createQuery("from Empleados");
       return query.getResultList();
    }
    /**
     * Método para obtener un registro de la tabla empleados por su Id
     * @param id Recibe un entero con el id del registro a obtener
     * @return Devuelve un objeto Empleados.
     */
    @Override
    public Empleados getEmpleadosById(int id) {
            
       return sessionFactory.getCurrentSession().load(Empleados.class, id);
    }
    /**
     * Método para borrar un registro de la tabla empleados por su id.
     * @param id Recibe un entero con el id del registro a borrar
     */
    @Override
    public void removeEmpleados(int id) {
       
       Session sesion =sessionFactory.getCurrentSession();
       Empleados c = sesion.get(Empleados.class, id);
       System.out.println(c.toString());
       if(c != null){
         System.out.println("Entra en removeEmpleados dentro del if");
           sesion.remove(c);
       }
    }

   

    
}
