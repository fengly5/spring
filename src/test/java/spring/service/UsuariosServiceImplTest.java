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
import spring.DAO.UsuariosDAOImpl;

import spring.model.Usuarios;

/**
 *
 * @author jcpm0
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { spring.config.SpringWebAppInitializer.class,
spring.config.WebConfig.class , spring.config.RootConfig.class})
public class UsuariosServiceImplTest {
  
  private static final Logger LOG = 
          Logger.getLogger(UsuariosServiceImplTest.class);
  
  private Usuarios usuario;
  
 List<Usuarios> lst= new ArrayList();  
 
 @InjectMocks
 private UsuariosServiceImpl usuariosServiceImpl;
 
 @Mock
 private UsuariosDAOImpl usuariosDao;
 
 
  
  public UsuariosServiceImplTest() {
  }
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    usuario = new Usuarios();
    usuario.setNombre("Juan Carlos");
    usuario.setApellido1("Peña");
    usuario.setApellido2("Mera");
    usuario.setFechanacimiento("19/10/1972");
    usuario.setIdusuarios(1);
    
    lst.add(usuario);
    
    
    /*configuración de los comportamientos del mock*/
    
    /* Cuando se llame a usuariosDao.listUsuarios() */
    when(usuariosDao.listUsuarios()).thenReturn(lst);
    
    /*Cuando se llame a uruariosDao.addUsuarios(usuario) */
   doAnswer(new Answer<Void>(){
      @Override
      public Void answer(InvocationOnMock iom) throws Throwable {

       Object[] arguments = iom.getArguments(); 

       if (arguments != null && arguments.length == 1 && arguments[0] 
               != null ) {
         
         Usuarios usuario = (Usuarios) arguments[0];
         
         lst.add(usuario);
                }
        return null;
      }
    }).when(usuariosDao).addUsuarios(any(Usuarios.class));
   
   /* Cuando se llama a usuariosDao.updateUsuarios(usuario) */
   
       doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock iom) throws Throwable {
        Object[] arguments = iom.getArguments();
        
        if (arguments != null && arguments.length == 1 && arguments[0] != null){
          
          //obtengo el empleado que se pasa en el metodo
          Usuarios usuarioNuevo=(Usuarios) arguments[0];
          //obtengo el primer empleado de la lista
          Usuarios user = lst.get(0);
          //actualizo
          user.setNombre(usuarioNuevo.getNombre());
          user.setApellido1(usuarioNuevo.getApellido1());
          user.setApellido2(usuarioNuevo.getApellido2());
          user.setFechanacimiento(usuarioNuevo.getFechanacimiento());
          
          //meto en la lista el usuario modificado
          lst.add(0,user);
          
        }
        return null;
      }
    }).when(usuariosDao).updateUsuarios(any(Usuarios.class));
       
     /* Comportamiento para getUsuariosById(id) */
    when(usuariosDao.getUsuariosById(any(Integer.class)))
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
    
        //comportamiento para removeUsuarios()
    
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
    }).when(usuariosDao).removeUsuarios(any(Integer.class));
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addUsuarios method, of class UsuariosServiceImpl.
   */
  @Test
  public void testAddUsuarios() {
   
    //creamos un nuevo usuario
    Usuarios nuevo = new Usuarios();
    nuevo.setApellido1("Ruiz");
    nuevo.setApellido2("Martín");
    nuevo.setNombre("Esteban");
    nuevo.setFechanacimiento("25/01/2014");
    nuevo.setIdusuarios(2);
    
    //lo insertamos
    usuariosServiceImpl.addUsuarios(nuevo);
    
    //si todo va bien ahora en la lista de usuarios debe haber 2
    assertEquals(2,usuariosServiceImpl.listUsuarios().size());
    
  }

  /**
   * Test of updateUsuarios method, of class UsuariosServiceImpl.
   */
  @Test
  public void testUpdateUsuarios() {
        //creamos un nuevo usuario
    Usuarios nuevo = new Usuarios();
    //obtengo el usuario de la lista
    nuevo=usuariosServiceImpl.getUsuariosById(1);
    //lo modifico
    nuevo.setApellido1("Ruiz");
    nuevo.setApellido2("Martín");
    nuevo.setNombre("Esteban");
    nuevo.setFechanacimiento("25/01/2014");
    //actualizo    
    usuariosServiceImpl.updateUsuarios(nuevo);
    //vuelco a obtenerlo
    nuevo = usuariosServiceImpl.getUsuariosById(1);
    //compruebo que está modificado
    assertEquals("Ruiz",nuevo.getApellido1() );
    assertEquals("Martín",nuevo.getApellido2());
    assertEquals("Esteban",nuevo.getNombre());

  }

  /**
   * Test of listUsuarios method, of class UsuariosServiceImpl.
   */
  @Test
  public void testListUsuarios() {
    assertEquals(1, this.usuariosServiceImpl.listUsuarios().size());
  }

  /**
   * Test of getUsuariosById method, of class UsuariosServiceImpl.
   */
  @Test
  public void testGetUsuariosById() {
    Usuarios u = usuariosServiceImpl.getUsuariosById(1);
    
    assertEquals(usuariosServiceImpl.listUsuarios().get(0),u);
  }

  /**
   * Test of removeUsuarios method, of class UsuariosServiceImpl.
   */
  @Test
  public void testRemoveUsuarios() {
    
    usuariosServiceImpl.removeUsuarios(1);
    assertEquals(0, usuariosServiceImpl.listUsuarios().size());
  }
  
}
