/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;
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
import spring.model.Notif_sistema;
import spring.model.Notificaciones;
import spring.model.Tipo_notif;

/**
 *
 * @author jcpm0
 */
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class Notif_sistemaDAOImplTest {
  
        private static final Logger LOG = 
                Logger.getLogger(Notif_sistemaDAOImplTest.class);
                
              
  
        
  @Autowired
  Notif_sistemaDao notif_sistemaDao;
  
  List<Notif_sistema> lst;
  
  Notif_sistema ns = new Notif_sistema();
  Notificaciones notificacion = new Notificaciones();
  Tipo_notif tn = new Tipo_notif();
  
  
  public Notif_sistemaDAOImplTest() {
  }
  
  @Before
  public void setUp() {
    
//preparamos datos de prueba
/*
 -------------                ----------------              -----------------
| Tipo_notif | < - 1 --- 1 - >| Notif_sistema |<-1 * -- 1 ->| Notificaciones |
 -------------                ----------------              -----------------
    
  Para poder realizar correctamente las operaciones CRUD necesito crear un 
  objeto de cada entidad relacionada.
    */
  //notificación
  notificacion.setFecha(new Date());
  notificacion.setIdnotificaciones(1);
  notificacion.setMensaje("notificacion prueba");
  notificacion.setDispositivo(null);
  Set<Notif_sistema> notifSisistema = new HashSet();
  notifSisistema.add(ns);
  notificacion.setNotif_sistema(notifSisistema);
    
  //tipo_nofif  
  tn.setIdtipo_notif(1);
  tn.setTipo("menu");
  
  //notif_sistema
  ns.setIdnotif_sistema(3);
  ns.setNotificacion(notificacion);
  ns.setTipos(tn);
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addNotif_sistema method, of class Notif_sistemaDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testAddNotif_sistema() {
    
    notif_sistemaDao.addNotif_sistema(ns);
    
    lst = notif_sistemaDao.listNotif_sistemas();
    
    assertEquals("menu", lst.get(lst.size()-1).getTipos().getTipo());
  }

  /**
   * Test of updateNotif_sistema method, of class Notif_sistemaDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testUpdateNotif_sistema() {
    //obtengo la lista de notificaciones del sistema
    lst = notif_sistemaDao.listNotif_sistemas();
    //saco el primer registro
    
    Notif_sistema n = lst.get(0);
    //Modifico el tipo de notificación de sistema
    Tipo_notif  tipo =n.getTipos();
    tipo.setTipo("TipoCambiado");
    n.setTipos(tipo);
    //actualizo
    notif_sistemaDao.updateNotif_sistema(n);
    //obtengo de nuevo la lista
    lst=notif_sistemaDao.listNotif_sistemas();
    //compruebo que se ha modificado
    assertEquals("TipoCambiado", lst.get(0).getTipos().getTipo());
  }

  /**
   * Test of listNotif_sistemas method, of class Notif_sistemaDAOImpl.
   */
  @Test
  @Transactional
  public void testListNotif_sistemas() {
    
    assertNotNull(notif_sistemaDao.listNotif_sistemas());
  }

  /**
   * Test of getNotif_sistemaById method, of class Notif_sistemaDAOImpl.
   */
   @Test
  @Transactional
  @Rollback(true)
  public void testGetNotif_sistemaById() {
    //obtengo el registro con id 1
    Notif_sistema n =notif_sistemaDao.getNotif_sistemaById(1);
    //si no es null es que he obtenido algo
    assertNotNull(n);
    //el primer registro es de tipo_notif 1, que como tipo es menu
    assertEquals("menu",n.getTipos().getTipo());
    
  }

  /**
   * Test of removeNotif_sistema method, of class Notif_sistemaDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testRemoveNotif_sistema() {
    Notif_sistema n;
    LOG.error("removeNotif_sistema");
    notif_sistemaDao.removeNotif_sistema(1);
        lst=notif_sistemaDao.listNotif_sistemas();
        n= lst.get(0);
        LOG.info("n: "+n.getIdnotif_sistema());
        
        assertNotEquals(1,n.getIdnotif_sistema());
  }
  
}
