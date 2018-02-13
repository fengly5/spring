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
import spring.model.Carta;
import spring.model.Platos;

/**
 *
 * @author jcpm0
 */
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CartaDAOImplTest {
  
  private static final Logger LOG = Logger.
    getLogger(CartaDAOImplTest.class.getName());
      
  @Autowired
  private CartaDao cartaDao;
  
  Carta c;
  
  List<Carta> lst;
  
  public CartaDAOImplTest() {
  }
  
  @Before
  public void setUp() {
    
    //defino una carta de prueba
    c = new Carta();
    c.setNoombre("Carta prueba");
       
  }
  
  @After
  public void tearDown() {
  }



  /**
   * Test of addCarta method, of class CartaDAOImpl.
   */
  @Test
  @Transactional
  @Rollback
  public void testAddCarta() {
    
    //inserto la carta
    cartaDao.addCarta(c);
    //recupero la lista de cartas.
    lst= cartaDao.listCartas();
    //compruebo si se ha introducido
    assertEquals(c.getNoombre(), lst.get(lst.size()-1).getNoombre());
  }

  /**
   * Test of updateCarta method, of class CartaDAOImpl.
   */
  @Test
  @Transactional
  @Rollback
  public void testUpdateCarta() {
    
    //obtengo lista de cartas
    lst= cartaDao.listCartas();
    //modifico una carta
    Carta cartaModificada =lst.get(0);
    cartaModificada.setNoombre("OtraCarta");
    //actualizo
    cartaDao.updateCarta(cartaModificada);
    //vuelvo a obtener la lista
    lst=cartaDao.listCartas();
    //compruebo si se ha modificado
    assertEquals("OtraCarta", lst.get(0).getNoombre());
    
  }

  /**
   * Test of listCartas method, of class CartaDAOImpl.
   */
  @Test
  @Transactional
  public void testListCartas() {
    assertNotNull(cartaDao.listCartas());
  }

  /**
   * Test of getCartaById method, of class CartaDAOImpl.
   */
  @Test
  @Transactional
  public void testGetCartaById() {
    //localizamos la carta
    Carta c =cartaDao.getCartaById(1);
    //test ok si c no es nulo
    assertNotNull(c);
    //test ok si la carta se llama General
    assertEquals("General",c.getNoombre());
  }

  /**
   * Test of removeCarta method, of class CartaDAOImpl.
   */
  @Test
  @Transactional
  @Rollback
  public void testRemoveCarta() {
    Carta c = null;
    cartaDao.removeCarta(1);
    try {
      c= cartaDao.getCartaById(1);
    } catch (Exception e) {
      assertNull(c);
    }
  }
  
}
