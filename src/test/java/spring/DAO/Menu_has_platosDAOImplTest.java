/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
import spring.model.Empleados;
import spring.model.Menu;
import spring.model.Menu_has_platos;
import spring.model.Menu_has_platosId;
import spring.model.Platos;
import spring.model.Usuarios;

/**
 *
 * @author jcpm0
 */
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class Menu_has_platosDAOImplTest {
  
        private static final Logger LOG = Logger.
    getLogger(ClientesDAOImplTest.class.getName());
        
  @Autowired
  Menu_has_platosDao mhp;
  
  Menu_has_platos menu_has_plato= new Menu_has_platos();
   Menu_has_platosId primaryKey = new Menu_has_platosId();
    Platos platoExistente = new Platos();
    Empleados e = new Empleados();
      Usuarios u = new Usuarios();
       Platos plato = new Platos();
       Menu menu = new Menu();
  
  List<Menu_has_platos> lst;
  
  public Menu_has_platosDAOImplTest() {
  }
  
  @Before
  public void setUp() {
    //para las pruebas necesito un plato y un menu que estén en la bbdd
    //Plato:5 	Ropa Vieja 	0.00 	0.00 	0.00 	1
    //menu: 1 	2017-12-25
    //usuario: 1 	Juan Carlos 	Peña 	Mera 	19/10/1972
    //Empelado: 1 	admin 	juank 	administrador 	1
    /**
     * Para el test testAddMenu_has_platos() necesito un plato que no esté
     * incluido en un menu. Necesito crear un usuario y um empleado poruqe los
     * platos son creados por empleados que son usuarios.
     * 
     * Para los test testGetClientesById() y testRemoveMenu_has_platos() 
     * necesito un plato que esté en la base de datos para localizarlo y 
     * después borrarlo.
     */
    
  
      u.setIdusuarios(1);
      u.setNombre("Juan Carlos");
      u.setApellido1("Peña");
      u.setApellido2("Mera");
      u.setFechanacimiento("19/10/1972");
    
    
      e.setIdempleados(1);
      e.setLogin("admiin");
      e.setPaswd("juank");
      e.setRol("administrador");
      e.setUsuario(u);
         
    //plato nuevo
   
      plato.setNombre("Ropa Vieja");
      plato.setPrecioTapa(0);
      plato.setPrecioMedia(0);
      plato.setPrecioRacion(0);
      plato.setIdPlato(50);//un id que no existe para que sea nuevo
      plato.setEmpleado(e);
      
   
      platoExistente.setNombre("Ropa Vieja");
      platoExistente.setPrecioTapa(0);
      platoExistente.setPrecioMedia(0);
      platoExistente.setPrecioRacion(0);
      platoExistente.setIdPlato(5);//este id si existe en la bbdd
      platoExistente.setEmpleado(e);
    
    

      Calendar cl = new GregorianCalendar();
      cl.set(2017, 11,25);
      menu.setFecha(cl.getTime());
      menu.setIdmenu(1);
       
      primaryKey.setMenu(menu);
      primaryKey.setPlato(plato);
      
    
    menu_has_plato.setTipo("primero");
    menu_has_plato.setPrimaryKey(primaryKey);
    menu_has_plato.setMenu(menu);
    menu_has_plato.setPlato(plato);
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addMenu_has_platos method, of class Menu_has_platosDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testAddMenu_has_platos() {
        //inserto el cliente
    mhp.addMenu_has_platos(menu_has_plato);
    //obtengo la lista de clientes
    lst= mhp.listMenu_has_platos();
    //compruebo que el último sea el que he añadido
    assertEquals(menu_has_plato.getMenu(),lst.get(lst.size()-1).getMenu());
  }

  /**
   * Test of updateMenu_has_platos method, of class Menu_has_platosDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testUpdateMenu_has_platos() {
        
    //obtengo la lista 
    lst = mhp.listMenu_has_platos();
    
    //modifico un cliente
    Menu_has_platos e = lst.get(0);
    e.setTipo("otroTipo");
    
    //actualizo
    mhp.updateMenu_has_platos(e);
    
    //obtengo de nuevo la lista
    lst= mhp.listMenu_has_platos();
    Menu_has_platos modificado=lst.get(0);
    assertEquals("otroTipo", modificado.getTipo());
    
  }

  /**
   * Test of listMenu_has_platos method, of class Menu_has_platosDAOImpl.
   */
  @Test
  @Transactional
  public void testListMenu_has_platos() {
    assertNotNull(mhp.listMenu_has_platos());
  }

  /**
   * Test of getMenu_has_platosById method, of class Menu_has_platosDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testGetClientesById() {
    LOG.info("primaryKey: "+primaryKey.toString());
    //modifico menu_has_platos para que el plato sea el que existe
    primaryKey.setPlato(platoExistente);
    
    Menu_has_platos e = mhp.getMenu_has_platosById(primaryKey);
    assertNotNull(e);
    assertEquals("Primero", e.getTipo());
  }

  /**
   * Test of removeMenu_has_platos method, of class Menu_has_platosDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testRemoveMenu_has_platos() {
    primaryKey.setPlato(platoExistente);
    Menu_has_platos m = null;
    mhp.removeMenu_has_platos(primaryKey);
    try {
        mhp.getMenu_has_platosById(primaryKey);
         
    } catch (Exception ex) {
      assertNull(m);
    }
  }
  
}
