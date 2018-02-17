/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import spring.DAO.Tipo_platoDAOImpl;
import spring.model.Tipo_plato;


/**
 *
 * @author jcpm0
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { spring.config.SpringWebAppInitializer.class,
spring.config.WebConfig.class , spring.config.RootConfig.class})
public class Tipo_platoServiceImplTest {
  
  private static final Logger LOG = 
          Logger.getLogger(Tipo_platoServiceImplTest.class);
  
  private Tipo_plato tipo_plato = new Tipo_plato();
  
  @InjectMocks
  private Tipo_platoServiceImpl tipo_platoServiceImpl;
  
  @Mock
  private Tipo_platoDAOImpl tipo_platoDao;
  
  List<Tipo_plato> lst = new ArrayList();
  
  public Tipo_platoServiceImplTest() {
  }
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    tipo_plato.setIdTipo(1);
    tipo_plato.setTipo("prueba");
    lst.add(tipo_plato);
    
    /*configuración de los comportamientos del mock*/
    
    /* Cuando se llame a tipo_platoDao.listTipo_plato() */
    when(tipo_platoDao.listTipo_platos()).thenReturn(lst);
    
    /*Cuando se llame a uruariosDao.addTipo_plato(usuario) */
   doAnswer(new Answer<Void>(){
      @Override
      public Void answer(InvocationOnMock iom) throws Throwable {

       Object[] arguments = iom.getArguments(); 

       if (arguments != null && arguments.length == 1 && arguments[0] 
               != null ) {
         
         Tipo_plato tp = (Tipo_plato) arguments[0];
         
         lst.add(tp);
                }
        return null;
      }
    }).when(tipo_platoDao).addTipo_plato(any(Tipo_plato.class));
   
   /* Cuando se llama a tipo_platoDao.updateTipo_plato(usuario) */
   
       doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock iom) throws Throwable {
        Object[] arguments = iom.getArguments();
        
        if (arguments != null && arguments.length == 1 && arguments[0] != null){
          
          //obtengo el objeto que se pasa en el metodo
          Tipo_plato tpNuevo=(Tipo_plato) arguments[0];
          //obtengo el primer empleado de la lista
          Tipo_plato tipoPlato = lst.get(0);
          //actualizo
          tipoPlato.setTipo(tpNuevo.getTipo());
          
          
          //meto en la lista el tp modificado
          lst.add(0,tipoPlato);
          
        }
        return null;
      }
    }).when(tipo_platoDao).updateTipo_plato(any(Tipo_plato.class));
       
     /* Comportamiento para getTipo_platoById(id) */
    when(tipo_platoDao.getTipo_platoById(any(Integer.class)))
            .thenAnswer(new Answer(){
      @Override
      public Object answer(InvocationOnMock iom) throws Throwable {
        Object[] arguments =iom.getArguments();
        int id=1;
        if (arguments != null && arguments.length == 1 && arguments[0] != null){
           id = (int)arguments[0];
          
        }
        return lst.get(id-1);
      }
      
    });
    
        //comportamiento para removeTipo_plato()
    
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock iom) throws Throwable {
        Object[] arguments = iom.getArguments();
        
        if (arguments != null && arguments.length == 1 && arguments[0] != null){
          int id = (int) arguments[0];
          lst.remove(id-1);
        }
        return null;
      }
    }).when(tipo_platoDao).removeTipo_plato(any(Integer.class));
    
  }
  
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addTipo_plato method, of class Tipo_platoServiceImpl.
   */
  @Test
  public void testAddTipo_plato() {
    
    Tipo_plato nuevo = new Tipo_plato();
    nuevo.setIdTipo(2);
    nuevo.setTipo("nuevo");
    tipo_platoServiceImpl.addTipo_plato(nuevo);
    
    assertEquals(2, tipo_platoServiceImpl.listTipo_platos().size());
  }

  /**
   * Test of updateTipo_plato method, of class Tipo_platoServiceImpl.
   */
  @Test
  public void testUpdateTipo_plato() {
    //obtengo el primer registro
    Tipo_plato modificado = new Tipo_plato();
    lst = tipo_platoServiceImpl.listTipo_platos();
    modificado=lst.get(0);
    
    //lo modifico
    modificado.setTipo("modificado");
    //actualizo
    tipo_platoServiceImpl.updateTipo_plato(modificado);
    //obtengo la lista de nuevo
    lst= tipo_platoServiceImpl.listTipo_platos();
    //compruebo que ha cambiado
    assertEquals("modificado",lst.get(0).getTipo());
  }

  /**
   * Test of listTipo_platos method, of class Tipo_platoServiceImpl.
   */
  @Test
  public void testListTipo_platos() {
    assertEquals(1,tipo_platoServiceImpl.listTipo_platos().size());
  }

  /**
   * Test of getTipo_platoById method, of class Tipo_platoServiceImpl.
   */
  @Test
  public void testGetTipo_platoById() {
      Tipo_plato u = tipo_platoServiceImpl.getTipo_platoById(1);
    
    assertEquals(tipo_platoServiceImpl.listTipo_platos().get(0),u);
  }

  /**
   * Test of removeTipo_plato method, of class Tipo_platoServiceImpl.
   */
  @Test
  public void testRemoveTipo_plato() {
      tipo_platoServiceImpl.removeTipo_plato(1);
    assertEquals(0, tipo_platoServiceImpl.listTipo_platos().size());
  }
  
}
