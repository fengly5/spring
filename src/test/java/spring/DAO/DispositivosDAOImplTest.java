/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.List;
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
import spring.model.Dispositivos;
import spring.model.Dispositivos;
import spring.model.Usuarios;

/**
 *
 * @author jcpm0
 */
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DispositivosDAOImplTest {
  
  @Autowired
  DispositivosDao dispositivosDao;
  
  //lista de dispositivos
  List<Dispositivos> lst;
  
  Clientes c = new Clientes();
  Dispositivos d = new Dispositivos();
  
  public DispositivosDAOImplTest() {
  }
  
  
  @Before
  public void setUp() {
    
    //Usuario 
    Usuarios u = new Usuarios();
    u.setIdusuarios(2);
    u.setNombre("Javier");
    u.setApellido1("García");
    u.setApellido2("Gómez");
    u.setFechanacimiento("25/01/1960");
    //un cliente para las pruebas
    
    c.setEmail("garcia.gomez@gmail.com");
    c.setPaswd("garcgome");
    c.setUsuario(u);
    c.setIdclientes(1);
    //dispositivo para las pruebas
    
    d.setIddispositivos(20);
    d.setToken("1234567890");
    //hay que insertar un cliente que sea el dueño del dispositivo
    //1 	garcia.gomez@gmail.com 	garcgome 	
    
    
    d.setCliente(c);
   
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addDispositivos method, of class DispositivosDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testAddDispositivos() {
    //inserto el cliente
    dispositivosDao.addDispositivos(d);
    //obtengo la lista de dispositivos
    lst= dispositivosDao.listDispositivos();
    //compruebo que el último sea el que he añadido
    assertEquals(d.getToken(),lst.get(lst.size()-1).getToken());
    
  }

  /**
   * Test of updateDispositivos method, of class DispositivosDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testUpdateDispositivos() {
       
    //obtengo la lista de dispositivos
    lst = dispositivosDao.listDispositivos();
    
    //modifico un dispositivo
    Dispositivos e = lst.get(0);
    e.setToken("0987654321");
    
    //actualizo
    dispositivosDao.updateDispositivos(e);
    
    //obtengo de nuevo la lista
    lst= dispositivosDao.listDispositivos();
    Dispositivos dispositivoModificado=lst.get(0);
    assertEquals("0987654321", dispositivoModificado.getToken());
    
  }

  /**
   * Test of listDispositivos method, of class DispositivosDAOImpl.
   */
  @Test
  @Transactional
  public void testListDispositivos() {
    assertNotNull(dispositivosDao.listDispositivos());
  }

  /**
   * Test of getDispositivosById method, of class DispositivosDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testGetDispositivosById() {
    Dispositivos e = dispositivosDao.getDispositivosById(1);
    assertNotNull(e);
    assertEquals("eCxMs78RWQQ:APA91bEhavDAvsBdLSVs9fzSGJgT5kG9tRAhfYe4ptTbH805H"
            + "yWOhV1c-R2HEAEjmsHA98_Kzg2mGBFeo_hr9dtlD5ElsGNyLvP0KJpO7Demryv"
            + "d5iqoTd686D879LpAgy_o-rl4j8oo", e.getToken());
  }

  /**
   * Test of removeDispositivos method, of class DispositivosDAOImpl.
   */
   @Test
  @Transactional
  @Rollback(true)
  public void testRemoveDispositivos() {
        Dispositivos e = null;
    dispositivosDao.removeDispositivos(1);
   try {e=dispositivosDao.getDispositivosById(1);
        System.out.println("spring.DAO.DispositivosDAOImplTest.testRemoveDispositivos()"+ e.getIddispositivos());
  }catch(Exception ex)
          {
            assertNull(e);
          }
  }
  
}
