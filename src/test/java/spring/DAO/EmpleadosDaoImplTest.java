/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import spring.DAO.EmpleadosDao;
import spring.config.RootConfig;
import spring.config.SpringWebAppInitializer;
import spring.config.WebConfig;
import spring.model.Empleados;
import spring.model.Usuarios;


/**
 *
 * @author jcpm0
 */

//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class EmpleadosDaoImplTest {
  
    private static final Logger LOG = Logger.
    getLogger(EmpleadosDaoImplTest.class.getName());
  public EmpleadosDaoImplTest() {
  }
  
  Empleados empleado;
  Usuarios usuario;
  List<Empleados> lst;
  @Autowired
  EmpleadosDao empleadosDao;
  
  @Before
  public void setUp() {
   
    //defino un usuario y un empleado para las pruebas
    usuario= new Usuarios();
    usuario.setIdusuarios(1);
    usuario.setNombre("Eleuterio");
    usuario.setApellido1("perez");
    usuario.setApellido2("sanchez");
    usuario.setFechanacimiento("19/10/1972");
    
    empleado = new Empleados();
    empleado.setIdempleados(1);
    empleado.setLogin("ele");
    empleado.setPaswd("uterio");
    empleado.setRol("jefe");
    empleado.setUsuario(usuario);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addEmpleados method, of class EmpleadosDaoImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testAddEmpleados() {
       
    //inserto en empleado 
    empleadosDao.addEmpleados(empleado);
    //obtengo la lista de empleados
    lst = empleadosDao.listEmpleados();
    //el test pasa si el último empleado coincide con el de pruebas
    assertEquals(empleado.getLogin(), lst.get(lst.size()-1).getLogin());
    
  } 

  /**
   * Test of updateEmpleados method, of class EmpleadosDaoImpl.
   */
  @Test
  @Rollback(true)
  @Transactional
  public void testUpdateEmpleados() {
    LOG.info("entra en testupdate");
    //obtengo la lista de empleados
    lst = empleadosDao.listEmpleados();
    LOG.info("lista : "+lst.toString());
    //modifico un empleado
    Empleados e = lst.get(0);
    e.setLogin("nuevoLogin");
    e.setPaswd("nuevoPas");
    //actualizo
    empleadosDao.updateEmpleados(e);
    LOG.info("despues de actualizar");
    //obtengo de nuevo la lista
    lst= empleadosDao.listEmpleados();
    Empleados empleadoModificado=lst.get(0);
    assertEquals("nuevoLogin", empleadoModificado.getLogin());
    assertEquals("nuevoPas", empleadoModificado.getPaswd());
  }

  /**
   * Test of listEmpleados method, of class EmpleadosDaoImpl.
   */
  @Test
  @Transactional
  public void testListEmpleados() {
    
    Assert.assertNotNull(empleadosDao.listEmpleados());
  }

  /**
   * Test of getEmpleadosById method, of class EmpleadosDaoImpl.
   */
  @Test
  @Transactional
  public void testGetEmpleadosById() {
    
    Empleados e = empleadosDao.getEmpleadosById(1);
    Assert.assertNotNull(e);
    assertEquals("juank", e.getPaswd());
  }

  /**
   * Test of removeEmpleados method, of class EmpleadosDaoImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testRemoveEmpleados() {
    Empleados e = null;
    empleadosDao.removeEmpleados(1);
   try {
     e=empleadosDao.getEmpleadosById(1);
     System.out.println("spring.DAO.EmpleadosDaoImplTest.testRemoveEmpleados()"+e.toString());
     fail("test falla");
  }catch(Exception ex)
          {
            Assert.assertNull(e);
          }
  }

}
