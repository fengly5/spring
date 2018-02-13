/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.Instant.now;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
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
import spring.model.Menu;

/**
 *
 * @author jcpm0
 */
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MenuDAOImplTest {
  
  @Autowired
  MenuDao menuDao;
  
  List<Menu> lst;
  
  Menu m;
  
  
  public MenuDAOImplTest() {
  }
  
  @Before
  public void setUp() {
    m = new Menu();
    m.setFecha(new Date());
    m.setIdmenu(2);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addMenu method, of class MenuDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testAddMenu() {
    //inserto el cliente
    menuDao.addMenu(m);
    //obtengo la lista de dispositivos
    lst= menuDao.listMenus();
    //compruebo que el último sea el que he añadido
    assertEquals(m.getFecha(),lst.get(lst.size()-1).getFecha());
    
  }
  /**
   * Test of updateMenu method, of class MenuDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testUpdateMenus() {
        
    //obtengo la lista de menus
    lst = menuDao.listMenus();
    
    //modifico un menu
    Menu e = lst.get(0);
    SimpleDateFormat sdf = new SimpleDateFormat();
    
    try {
      Date fecha = sdf.parse("19/10/1972");
              } catch (ParseException ex) {
     
    }
    e.setFecha(fecha);
    
    //actualizo
    menuDao.updateMenu(e);
    
    //obtengo de nuevo la lista
    lst= menuDao.listMenus();
    Menu menuModificado=lst.get(0);
    assertEquals(, clienteModificado.getEmail());
    assertEquals("nuevoPas", clienteModificado.getPaswd());
  }

  /**
   * Test of listMenus method, of class MenuDAOImpl.
   */
  @Test
  public void testListMenus() {
  }

  /**
   * Test of getMenuById method, of class MenuDAOImpl.
   */
  @Test
  public void testGetMenuById() {
  }

  /**
   * Test of removeMenu method, of class MenuDAOImpl.
   */
  @Test
  public void testRemoveMenu() {
  }
  
}
