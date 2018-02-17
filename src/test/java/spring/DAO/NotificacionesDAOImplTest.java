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
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.config.RootConfig;
import spring.config.SpringWebAppInitializer;
import spring.config.WebConfig;
import spring.model.Dispositivos;
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
public class NotificacionesDAOImplTest {
        private static final Logger LOG = 
                Logger.getLogger(NotificacionesDAOImplTest.class.getName());
  @Autowired
  NotificacionesDao notificacionesDao;
  
  List<Notificaciones> lst;
  
  Notificaciones notificacion= new Notificaciones();
 
  Notif_sistema ns=new Notif_sistema();
  Tipo_notif tn= new Tipo_notif();  
  

  public NotificacionesDAOImplTest() {
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
   * Test of addNotificaciones method, of class NotificacionesDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testAddNotificaciones() {
    
    notificacionesDao.addNotificaciones(notificacion);
    lst= notificacionesDao.listNotificaciones();
    
    assertEquals ("notificacion prueba",lst.get(lst.size()-1).getMensaje());
  }

  /**
   * Test of updateNotificaciones method, of class NotificacionesDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testUpdateNotificaciones() {
    
    lst=notificacionesDao.listNotificaciones();
    Notificaciones n =lst.get(0);
    
    n.setMensaje("mensaje modificado");
    notificacionesDao.updateNotificaciones(n);
    
    lst= notificacionesDao.listNotificaciones();
    assertEquals("mensaje modificado",lst.get(0).getMensaje());
  }

  /**
   * Test of listNotificaciones method, of class NotificacionesDAOImpl.
   */
  @Test
  @Transactional
  public void testListNotificaciones() {
    assertNotNull(notificacionesDao.listNotificaciones());
  }

  /**
   * Test of getNotificacionesById method, of class NotificacionesDAOImpl.
   */
  @Test
  @Transactional
  public void testGetNotificacionesById() {
    //intento obtener la primera notificacion
    Notificaciones n =notificacionesDao.getNotificacionesById(1);
    //si no es nulo algo he obtenido
    assertNotNull(n);
    //compruebo que el mensaje de la que he obtenido coincide con el de la 1ª
    // notificacion
    
    assertEquals("Ya está listo el menú del día",n.getMensaje());
  }

  /**
   * Test of removeNotificaciones method, of class NotificacionesDAOImpl.
   */
  @Test
  //@Rollback(true)
  @Transactional(propagation = Propagation.NESTED)
  public void testRemoveNotificaciones() {
      Notificaciones n=null;
      notificacionesDao.removeNotificaciones(1);
      
       lst=notificacionesDao.listNotificaciones();
        n= lst.get(0);
        LOG.info("n: "+n.getFecha().toString());
        LOG.info("n: "+n.getIdnotificaciones());
        assertNotEquals(1,n.getIdnotificaciones());
       
        
    
    
  }
  
}
