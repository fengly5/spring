/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.DAO;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import spring.model.Reservas;
import spring.model.Usuarios;

/**
 *
 * @author jcpm0
 */
@ContextConfiguration(classes={  SpringWebAppInitializer.class,
WebConfig.class , RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ReservasDAOImplTest {
  
  @Autowired
  private ReservasDao reservasDao;
  
  List<Reservas> lst;
  
  //entidades necesarias
  
  Reservas reserva = new Reservas();
  Clientes cliente = new Clientes();
  Usuarios usuario = new Usuarios();
  
  //para manejar la fecha y hora
  Calendar calendar = new GregorianCalendar();
  Date fecha;
  Date hora;
  
  public ReservasDAOImplTest() {
  }
  
  @Before
  public void setUp() {
    
    //usuario para los test    
    usuario.setIdusuarios(2);
    usuario.setNombre("Javier");
    usuario.setApellido1("García");
    usuario.setApellido2("Gómez");
    usuario.setFechanacimiento("25/01/1960");
    //un cliente para las pruebas
    cliente=new Clientes();
    cliente.setEmail("garcia.gomez@gmail.com");
    cliente.setPaswd("garcgome");
    cliente.setUsuario(usuario);
    cliente.setIdclientes(1);
    //reserva de prueba
    reserva.setCliente(cliente);
    reserva.setIdreservas(100);
    reserva.setTurno("cena");
    reserva.setnComensales(5);
    calendar.set(2018,1, 14,21,0);
    fecha=calendar.getTime();
    reserva.setFechaHora(fecha);
    
    
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addReservas method, of class ReservasDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testAddReservas() {
    //añado una reserva
    reservasDao.addReservas(reserva);
    //obtengo lista re reservas
    lst= reservasDao.listReservas();
    //comparo si el ultimo registro se corresponde a lo que he insertado
    assertEquals("cena", lst.get(lst.size()-1).getTurno());
    assertEquals(5,lst.get(lst.size()-1).getnComensales());
  }

  /**
   * Test of updateReservas method, of class ReservasDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testUpdateReservas() {
    //obtengo la lista
    lst= reservasDao.listReservas();
    //saco el primer elemento
    // idreserva  ncomensales turno     fechaHora           idclient
    //  1 	    5 	     Almuerz   2017-12-30 14:00 	1
    Reservas rs = lst.get(0);
    //modifico el turno y numero de comensales
    rs.setTurno("cena");
    rs.setnComensales(8);
    //actualizo
    reservasDao.updateReservas(rs);
    //obtengo la lista de nuevo
    lst = reservasDao.listReservas();
    //compruebo si se ha cambiado
    assertEquals("cena", lst.get(0).getTurno());
    assertEquals(8,lst.get(0).getnComensales());
  }

  /**
   * Test of listReservas method, of class ReservasDAOImpl.
   */
  @Test
  @Transactional
  public void testListReservas() {
    
    assertNotNull(reservasDao.listReservas());
  }

  /**
   * Test of getReservasById method, of class ReservasDAOImpl.
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testGetReservasById() {
    
    Reservas r = reservasDao.getReservasById(1);
    
    assertEquals("Almuerzo", r.getTurno());
    assertEquals(5, r.getnComensales());
  }

  /**
   * Test of removeReservas method, of class ReservasDAOImpl.
   */
    @Test
  @Transactional
  @Rollback(true)
  public void testRemoveReservas() {
    
    reservasDao.removeReservas(1);
    lst = reservasDao.listReservas();
    
      assertNotEquals(1, lst.get(0).getIdreservas());
    
  }
  
}
