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
import org.mockito.junit.MockitoJUnitRunner;
import spring.DAO.EmpleadosDaoImpl;
import spring.model.Empleados;
import spring.model.Usuarios;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;

/**
 *Clase de pruebas para el servicio EmpleadosServiceImpl
 * @author jcpm0
 */

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { spring.config.SpringWebAppInitializer.class,
spring.config.WebConfig.class , spring.config.RootConfig.class})
public class EmpleadosServiceImplTest {
  
  //preparamos un logger para dedug
  private static final Logger LOG = Logger.
          getLogger(EmpleadosServiceImplTest.class, "TEST-EmpleadosService");
  
  public EmpleadosServiceImplTest() {
  }
  
  private Empleados empleado;
  private Usuarios usuario;
  //lista de empleados
  List<Empleados> t = new ArrayList<>();
  
  @InjectMocks
  EmpleadosServiceImpl empleadosService;
  
  //objeto mock que simula el comportamiento de la clase EmpleadosDaoImpl
  @Mock
  private EmpleadosDaoImpl empleadosDao;

  @Before
  public void setUp() {
    //incialización de los mocks
    MockitoAnnotations.initMocks(this);
    
    
    usuario= new Usuarios();
    usuario.setIdusuarios(1);
    usuario.setNombre("Juan Carlos");
    usuario.setApellido1("Peña");
    usuario.setApellido2("Mera");
    usuario.setFechanacimiento("19/10/1972");
    
    empleado = new Empleados();
    empleado.setIdempleados(1);
    empleado.setLogin("admin");
    empleado.setPaswd("juank");
    empleado.setRol("jefe");
    empleado.setUsuario(usuario);
    
    //inicialización de la lista de empleados
    t.add(empleado);
    
    //configuración de los comportamientos del mock
    
    //si se llama a listEmpleados devuelve la lista
    when(empleadosDao.listEmpleados()).thenReturn(t);
    
    //en caso de que el objeto mock reciba una llamada al métdodo
    // addEmpleados(empleado) añade el empleado a la lista.
    doAnswer(new Answer<Void>(){
      @Override
      public Void answer(InvocationOnMock iom) throws Throwable {
        LOG.info("entra en answer");
       Object[] arguments = iom.getArguments(); 
       LOG.info("arguments: "+arguments.toString());
       if (arguments != null && arguments.length == 1 && arguments[0] 
               != null ) {
         
         Empleados empleado = (Empleados) arguments[0];
         LOG.info("doAnswer: "+ empleado.toString());
         t.add(empleado);
                }
        return null;
      }
    }).when(empleadosDao).addEmpleados(any(Empleados.class));
    
    //Simulo la operación de actualización, actualizo el primer empleado con 
    //los datos que se pasan del nuevo.
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock iom) throws Throwable {
        Object[] arguments = iom.getArguments();
        
        if (arguments != null && arguments.length == 1 && arguments[0] != null){
          
          //obtengo el empleado que se pasa en el metodo
          Empleados empNuevo=(Empleados) arguments[0];
          //obtengo el primer empleado de la lista
          Empleados emp0 = t.get(0);
          //actualizo
          emp0.setLogin(empNuevo.getLogin());
          emp0.setPaswd(empNuevo.getPaswd());
          emp0.setRol(empNuevo.getRol());
          emp0.setUsuario(empNuevo.getUsuario());
          
          //meto en la lista el usuario modificado
          t.add(0,emp0);
          
        }
        return null;
      }
    }).when(empleadosDao).updateEmpleados(any(Empleados.class));
    
    //Comportamiento para getEmpleadosById(id)
    when(empleadosDao.getEmpleadosById(any(Integer.class)))
            .thenAnswer(new Answer(){
      @Override
      public Object answer(InvocationOnMock iom) throws Throwable {
        Object[] arguments =iom.getArguments();
        int id=1;
        if (arguments != null && arguments.length == 1 && arguments[0] != null){
           id = (int)arguments[0];
          
        }
        return t.get(id-1);
      }
      
    });
    
    //comportamiento para removeEmpleados()
    
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock iom) throws Throwable {
        Object[] arguments = iom.getArguments();
        
        if (arguments != null && arguments.length == 1 && arguments[0] != null){
          int id = (int) arguments[0];
          t.remove(id-1);
        }
        return null;
      }
    }).when(empleadosDao).removeEmpleados(any(Integer.class));
    
            
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test del método addEmpleados()
   * añade un empleado y comprueba que el número de empleados a aumentado
   */
  @Test
  public void testAddEmpleados() {
    System.out.println("addEmpleados");
    
    //definimos el empleado a añadir
    Empleados emp2 = new Empleados();
    emp2.setIdempleados(2);
    emp2.setLogin("empleado2");
    emp2.setPaswd("emp");
    emp2.setUsuario(new Usuarios("pepe","Garcia","Gomez","21/05/1965"));
    LOG.info("testAddEmpleados: empleadosService.addEmpleados");
    
    empleadosService.addEmpleados(emp2);
    LOG.info("t.size= "+t.size());
    //al añadir un nuevo empleado el tamaño de la lista debe ser 2.
    assertEquals(2, empleadosService.listEmpleados().size());
        
  }

  /**
   * Test of updateEmpleados method, of class EmpleadosServiceImpl.
   */
  @Test
  public void testUpdateEmpleados() {
    System.out.println("updateEmpleados");
    
    //creo un empleado 
    Empleados e = new Empleados();
    e.setLogin("nuevoLogin");
    e.setPaswd("nuevoPasswd");
    e.setRol("nuevoRol");
    
    empleadosDao.updateEmpleados(e);
    assertEquals("login actualizado", "nuevoLogin", t.get(0).getLogin());
    assertEquals("passwd actualizado", "nuevoPasswd", t.get(0).getPaswd());
    assertEquals("Rol actualizado", "nuevoRol", t.get(0).getRol());
    
    
    
  }

  /**
   * Test of listEmpleados method, of class EmpleadosServiceImpl.
   */
  @Test
  public void testListEmpleados() {
    System.out.println("listEmpleados");
    int expResult =1;
    LOG.info("t.size= "+t.size());
    assertEquals(expResult, this.empleadosService.listEmpleados().size());
    
    
  }

  /**
   * Test of getEmpleadosById method, of class EmpleadosServiceImpl.
   */
  @Test
  public void testGetEmpleadosById() {
    System.out.println("getEmpleadosById");
    int id = 1;
    
    Empleados e = empleadosDao.getEmpleadosById(id);
    assertEquals("Loacliza empleado por id", t.get(0), e);
  }

  /**
   * Test of removeEmpleados method, of class EmpleadosServiceImpl.
   */
  @Test
  public void testRemoveEmpleados() {
    System.out.println("removeEmpleados");
    int id = 1;
    
    empleadosDao.removeEmpleados(id);
    assertEquals("empleado eliminado", 0, empleadosDao.listEmpleados().size());
    
    
  }

  /**
   * Test of LoginEmpleado method, of class EmpleadosServiceImpl.
   */
  @Test
  public void testLoginEmpleado() {
    System.out.println("LoginEmpleado");
    Empleados e = empleadosService.LoginEmpleado("admin", "juank");
    LOG.info(e.toString());
    String loginEsperado="admin";
    String paswdEsperado="juank";
    assertEquals("login correcto", loginEsperado, e.getLogin());
    assertEquals("paswd correcto", paswdEsperado, e.getPaswd());
    
  }
  
}
