/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import spring.model.Empleados;
import spring.model.Platos;
import spring.model.Platos_has_carta;
import spring.model.Platos_has_cartaId;
import spring.model.Tipo_plato;
import spring.model.Usuarios;

/**
 *
 * @author jcpm0
 */
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PlatosDAOImplTest {
  
  @Autowired
  PlatosDao platosDao;
  
  List<Platos> lst;
  
  Platos plato= new Platos();
  Platos plato_existente = new Platos();
  Empleados e= new Empleados();
  Usuarios u= new Usuarios();
  Tipo_plato tp = new Tipo_plato();
  Platos_has_carta phc = new Platos_has_carta();
  Carta carta = new Carta();
  Platos_has_cartaId phcId = new Platos_has_cartaId();
  public PlatosDAOImplTest() {
  }
  
  @Before
  public void setUp() {
    //1 	Salmorejo 	0.00 	0.00 	0.00 	1
      u.setIdusuarios(1);
      u.setNombre("Juan Carlos");
      u.setApellido1("Peña");
      u.setApellido2("Mera");
      u.setFechanacimiento("19/10/1972");
    
    
      e.setIdempleados(1);
      e.setLogin("admin");
      e.setPaswd("juank");
      e.setRol("administrador");
      e.setUsuario(u);
      
      Set<Platos> platos=new HashSet();
      platos.add(plato_existente);
      tp.setPlato(platos);
      tp.setIdTipo(1);
      tp.setTipo("Menu");
      Set<Tipo_plato> tipos = new HashSet();
      tipos.add(tp);
      
      carta.setIdcarta(1);
      carta.setNoombre("General");
      Set<Platos_has_carta> platos_has_carta =new HashSet();
      carta.setPlatos_has_carta(platos_has_carta);
     
      phcId.setCarta(carta);
      phcId.setPlato(plato_existente);
      
      phc.setAparece(1);
      phc.setPlato(plato_existente);
      phc.setCarta(carta);
      phc.setPrimaryKey(phcId);
         
    //plato nuevo
   
      plato.setNombre("Plato nuevo");
      plato.setPrecioTapa(0);
      plato.setPrecioMedia(0);
      plato.setPrecioRacion(0);
      plato.setIdPlato(50);//un id que no existe para que sea nuevo
      plato.setEmpleado(e);

      plato_existente.setNombre("Salmorejo");
      plato_existente.setPrecioTapa(0);
      plato_existente.setPrecioMedia(0);
      plato_existente.setPrecioRacion(0);
      plato_existente.setIdPlato(1);//un id que no existe para que sea nuevo
      plato_existente.setEmpleado(e);
      plato_existente.setPlatos_has_carta(platos_has_carta);
      plato_existente.setTipo_plato(tipos);
      
  }
  
  @After
  public void tearDown() {
  }

 
  /**
   * Test of addPlatos method, of class PlatosDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testAddPlatos() {
    
    platosDao.addPlatos(plato);
    
    lst= platosDao.listPlatos();
    
    assertEquals("Plato nuevo", lst.get(lst.size()-1).getNombre());
    
  }

  /**
   * Test of updatePlatos method, of class PlatosDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testUpdatePlatos() {
    lst=platosDao.listPlatos();
    
    Platos p = lst.get(0);
    p.setNombre("Plato modificado");
    
    platosDao.updatePlatos(p);
    
    lst=platosDao.listPlatos();
      assertEquals("Plato modificado", lst.get(0).getNombre());
    
  }

  /**
   * Test of listPlatos method, of class PlatosDAOImpl.
   */
     @Test
  @Transactional
  public void testListPlatos() {
    
    assertNotNull(platosDao.listPlatos());
  }

  /**
   * Test of getPlatosById method, of class PlatosDAOImpl.
   */
      @Test
  @Transactional
  public void testGetPlatosById() {
    
    Platos p = platosDao.getPlatosById(1);
    assertNotNull(p);
        assertEquals("Salmorejo", p.getNombre());
  }

  /**
   * Test of removePlatos method, of class PlatosDAOImpl.
   */
      @Test
  @Transactional
  @Rollback(true)
  public void testRemovePlatos() {
         Platos n=null;
      platosDao.removePlatos(1);
      
       lst=platosDao.listPlatos();
        n= lst.get(0);
        assertNotEquals(1,n.getIdPlato());
  }
  
}
