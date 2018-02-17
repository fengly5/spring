/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import spring.config.RootConfig;
import spring.config.SpringWebAppInitializer;
import spring.config.WebConfig;
import spring.model.Empleados;
import spring.model.Usuarios;

/**
 *
 * @author jcpm0
 */
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UsuariosDAOImplTest {
  
  @Autowired
  private UsuariosDao usuariosDao;
  
  List<Usuarios> lst;
  
  Usuarios usuario = new Usuarios();
  Empleados empleado = new Empleados();
  
  public UsuariosDAOImplTest() {
  }
  
  @Before
  public void setUp() {
    
    usuario.setNombre("Pepe");
    usuario.setApellido1("Teruel");
    usuario.setApellido2("Martín");
    usuario.setFechanacimiento("25/12/2000");
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addUsuarios method, of class UsuariosDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testAddUsuarios() {
    
    usuariosDao.addUsuarios(usuario);
    
    lst= usuariosDao.listUsuarios();
    
    assertEquals("Pepe",lst.get(lst.size()-1).getNombre());
  }

  /**
   * Test of updateUsuarios method, of class UsuariosDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testUpdateUsuarios() {
    
    //obtengo lista
    lst=usuariosDao.listUsuarios();
    //modifico el primero
    Usuarios u= lst.get(0);
    u.setNombre("modifico");
    u.setApellido1("modifico");
    
    //actualizo
    usuariosDao.updateUsuarios(u);
    //obtengo de nuevo la lista y compruebo si ha cambiado
    lst=usuariosDao.listUsuarios();
    assertEquals("modifico",lst.get(0).getNombre());
    assertEquals("modifico",lst.get(0).getApellido1());
  }

  /**
   * Test of listUsuarios method, of class UsuariosDAOImpl.
   */
  @Test
  @Transactional
  public void testListUsuarios() {
    assertNotNull(usuariosDao.listUsuarios());
  }

  /**
   * Test of getUsuariosById method, of class UsuariosDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testGetUsuariosById() {
    
    Usuarios u = usuariosDao.getUsuariosById(1);
    
    assertNotNull(u);
    
    assertEquals("Juan Carlos",u.getNombre());
    
  }

  /**
   * Test of removeUsuarios method, of class UsuariosDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testRemoveUsuarios() {
    
    usuariosDao.removeUsuarios(1);
    
    lst=usuariosDao.listUsuarios();
    
    assertNotEquals(1, lst.get(0).getIdusuarios());
  }
  
}
