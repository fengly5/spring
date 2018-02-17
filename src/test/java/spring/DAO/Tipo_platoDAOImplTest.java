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
public class Tipo_platoDAOImplTest {
  
  @Autowired
  private Tipo_platoDao tipo_platoDao;
  
  List<Tipo_plato> lst;
  
  Tipo_plato tp = new Tipo_plato();
  Usuarios u = new Usuarios();
  Empleados e = new Empleados();
  Platos plato_existente = new Platos();
  Platos_has_carta phc = new Platos_has_carta();
  Platos_has_cartaId phcId = new Platos_has_cartaId();
  Carta carta = new Carta();
  
  
  public Tipo_platoDAOImplTest() {
  }
  
  @Before
  public void setUp() {
    
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
      tp.setTipo("Prueba");
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
   * Test of addTipo_plato method, of class Tipo_platoDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testAddTipo_plato() {
    //añado el tipo plato
    tipo_platoDao.addTipo_plato(tp);
    //obtengo lista y compruebo que el ultimo sea el que he metido
    lst= tipo_platoDao.listTipo_platos();
    assertEquals("Prueba", lst.get(lst.size()-1).getTipo());
  }

  /**
   * Test of updateTipo_plato method, of class Tipo_platoDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testUpdateTipo_plato() {
    
    //obtengo lista
    lst= tipo_platoDao.listTipo_platos();
    //modifico el primer elemento
    Tipo_plato t =lst.get(0);
    t.setTipo("modifico");
    //actualizo
    tipo_platoDao.updateTipo_plato(t);
    //obtengo se nuevo la lista y compruebo
    lst= tipo_platoDao.listTipo_platos();
    assertEquals("modifico",lst.get(0).getTipo());
            
    
  }

  /**
   * Test of listTipo_platos method, of class Tipo_platoDAOImpl.
   */
  @Test
  @Transactional
  public void testListTipo_platos() {
    assertNotNull(tipo_platoDao.listTipo_platos());
  }

  /**
   * Test of getTipo_platoById method, of class Tipo_platoDAOImpl.
   */
  @Test
  @Transactional
  public void testGetTipo_platoById() {
    
    //obtengo el tipo_plato con id 1
    Tipo_plato t = tipo_platoDao.getTipo_platoById(1);
    
    //si no es nulo algo he obtenido
    assertNotNull(t);
    //compruebo que lo que he obtenido es el primer registro
    assertEquals(1,t.getIdTipo());
    assertEquals("Menu", t.getTipo());
    
  }

  /**
   * Test of removeTipo_plato method, of class Tipo_platoDAOImpl.
   */
   @Test
  @Transactional
  @Rollback(true)
  public void testRemoveTipo_plato() {
    
    //elimino el primer tipo_plato
    tipo_platoDao.removeTipo_plato(1);
    //obtengo lista
    lst= tipo_platoDao.listTipo_platos();
    //compruebo que el id del primero de la lista no es 1
    assertNotEquals(1,lst.get(0).getIdTipo());
  }
  
}
