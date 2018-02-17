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
import spring.model.Tipo_notif;

/**
 *
 * @author jcpm0
 */
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class Tipo_notifDAOImplTest {
  
  @Autowired
  Tipo_notifDao tipo_notifDao;
  
  List<Tipo_notif> lst;
  
  Tipo_notif tn = new Tipo_notif();
  
  public Tipo_notifDAOImplTest() {
  }
  
  @Before
  public void setUp() {
    
    // creo una tipo_notif para los test
   
    tn.setTipo("prueba");
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addTipo_notif method, of class Tipo_notifDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testAddTipo_notif() {
    //añado tn
    tipo_notifDao.addTipo_notif(tn);
    //compruebo que se ha añadido
    lst= tipo_notifDao.listTipo_notifs();
    assertEquals("prueba", lst.get(lst.size()-1).getTipo());
  }

  /**
   * Test of updateTipo_notif method, of class Tipo_notifDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testUpdateTipo_notif() {
    //obtengo la lista
    lst= tipo_notifDao.listTipo_notifs();
    //modifico el primer elemento
    Tipo_notif t = lst.get(0);
    t.setTipo("modifico");
    //actualizo
    tipo_notifDao.updateTipo_notif(t);
    //lo compruebo
     lst=tipo_notifDao.listTipo_notifs();
      assertEquals("modifico", lst.get(0).getTipo());
  }

  /**
   * Test of listTipo_notifs method, of class Tipo_notifDAOImpl.
   */
  @Test
  @Transactional
  public void testListTipo_notifs() {
    assertNotNull(tipo_notifDao.listTipo_notifs());
  }

  /**
   * Test of getTipo_notifById method, of class Tipo_notifDAOImpl.
   */
  @Test
  @Transactional
  public void testGetTipo_notifById() {
    
    //obtengo el tipo con id 1
    Tipo_notif t = tipo_notifDao.getTipo_notifById(1);
    //si no es nulo algo he obtenido
    assertNotNull(t);
    //compruebo que el tipo es menu que es el primer tipo en la bbdd
    assertEquals("menu", t.getTipo());
  }

  /**
   * Test of removeTipo_notif method, of class Tipo_notifDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testRemoveTipo_notif() {
    //borro el tipo con id 1
    tipo_notifDao.removeTipo_notif(1);
    //cargo la lista 
    lst= tipo_notifDao.listTipo_notifs();
    //el primer elemento no puede tener id 1
    assertNotEquals(1, lst.get(0));
  }
  
}
