/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import spring.config.RootConfig;
import spring.config.SpringWebAppInitializer;
import spring.config.WebConfig;
import spring.model.Clientes;
import spring.model.Usuarios;

/**
 *
 * @author jcpm0
 */
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientesDAOImplTest {
  
      private static final Logger LOG = Logger.
    getLogger(ClientesDAOImplTest.class.getName());
  
  @Autowired
  ClientesDao clientesDao;
  
  List<Clientes> lst;
  Clientes c;
  
  public ClientesDAOImplTest() {
  }
  
  @Before
  public void setUp() {
    
    Usuarios u = new Usuarios();
    u.setIdusuarios(2);
    u.setNombre("Javier");
    u.setApellido1("García");
    u.setApellido2("Gómez");
    u.setFechanacimiento("25/01/1960");
    //un cliente para las pruebas
    c=new Clientes();
    c.setEmail("jcpm@gmail.com");
    c.setPaswd("1234");
    c.setUsuario(u);
    c.setIdclientes(100);
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addClientes method, of class ClientesDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testAddClientes() {
    //inserto el cliente
    clientesDao.addClientes(c);
    //obtengo la lista de clientes
    lst= clientesDao.listClientes();
    //compruebo que el último sea el que he añadido
    assertEquals(c.getEmail(),lst.get(lst.size()-1).getEmail());
    
  }

  /**
   * Test of updateClientes method, of class ClientesDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testUpdateClientes() {
        LOG.info("entra en testupdate");
    //obtengo la lista de clientes
    lst = clientesDao.listClientes();
    LOG.info("lista : "+lst.toString());
    //modifico un cliente
    Clientes e = lst.get(0);
    e.setEmail("nuevoEmail@email.es");
    e.setPaswd("nuevoPas");
    //actualizo
    clientesDao.updateClientes(e);
    LOG.info("despues de actualizar");
    //obtengo de nuevo la lista
    lst= clientesDao.listClientes();
    Clientes clienteModificado=lst.get(0);
    assertEquals("nuevoEmail@email.es", clienteModificado.getEmail());
    assertEquals("nuevoPas", clienteModificado.getPaswd());
  }

  /**
   * Test of listClientes method, of class ClientesDAOImpl.
   */
  @Test
  @Transactional
  public void testListClientes() {
    assertNotNull(clientesDao.listClientes());
  }

  /**
   * Test of getClientesById method, of class ClientesDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testGetClientesById() {
    Clientes e = clientesDao.getClientesById(1);
    assertNotNull(e);
    assertEquals("garcgome", e.getPaswd());
  }

  /**
   * Test of removeClientes method, of class ClientesDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testRemoveClientes() {
        Clientes e = null;
    clientesDao.removeClientes(1);
   try {e=clientesDao.getClientesById(1);
  }catch(Exception ex)
          {
            assertNull(e);
          }
  }
  
}
