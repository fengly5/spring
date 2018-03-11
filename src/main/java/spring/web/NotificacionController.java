/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.model.Clientes;
import spring.model.Dispositivos;
import spring.model.Notif_sistema;
import spring.model.Notificaciones;
import spring.model.Tipo_notif;
import spring.service.ClientesService;
import spring.service.DispositivosService;
import spring.service.Notif_sistemaService;
import spring.service.NotificacionesService;
import spring.service.PushNotificationHelper;
import spring.service.Tipo_notifService;
import spring.validacion.NotificacionCliente;

/**
 * Controlados para las operaciones CRUD de la tabla noificaciones
 * 
 * @author jcpm0
 */
@Controller
@Transactional/* anotación para que spring se encargue de las transacciones*/
public class NotificacionController {
   private static final Logger LOG = 
          Logger.getLogger(NotificacionController.class.getName()); 
   
   /*Dependencias*/
  @Autowired
  private NotificacionesService notificacionesService;
  @Autowired
  private Tipo_notifService tipo_notifService;
  @Autowired
  private Notif_sistemaService notif_sistemaService;
  @Autowired
  private DispositivosService dispositivosService;
  @Autowired
  private ClientesService clientesSrevice;

  /**
   * Método para listar las notificaciones de sistema
   * @param model Objeto model con los datos para la vista
   * @param offset Desplazamiento en el total de registros
   * @param maxResults Resulados máximos por página
   * @return devuelve String con el nombre de la vista
   */
  @RequestMapping(value="notificaciones")
  public String list(Model model, Integer offset,Integer maxResults){
    if (offset == null){
      offset=0;
    }
      
    /*lista de notificaciones*/
    List<Notificaciones> lst_notificaciones = notificacionesService.listNotificaciones(offset,maxResults);
    /*Lista para devolver solo las notificaciones de sistema*/
    List<Notificaciones> lst_notif_sistema= new ArrayList<>();
        
    /*extraigo las notificaciones de sistema a su propia lista*/
    for (Notificaciones n:lst_notificaciones){
      if(n.getDispositivo().isEmpty()){
        lst_notif_sistema.add(n);
      }
    }

    /* número total de notificaciones*/
    int count = lst_notificaciones.size();
    /*Le paso datos a la vista*/
    model.addAttribute("count", count);
    model.addAttribute("ofsset", offset);
    model.addAttribute("maxResults", maxResults);
    model.addAttribute("notificaciones", lst_notif_sistema);
 
    
    return "notificaciones";
    
  }
  /**
   * Método para listar las notificaciones a clientes
   * @param model Objeto Model para pasar datos a la vista
   * @param offset Desplazamiento sobre el total de registros
   * @param maxResults Máximo de resultados por página
   * @return String con el nombre de la vista
   */
    @RequestMapping(value="notificacionesClientes")
  public String listClientes(Model model, Integer offset,Integer maxResults){
    if (offset == null){
      offset=0;
    }
   
   /*Lista para los clientes*/ 
   List<Clientes> clientes = new ArrayList<>();
   /*Lista de las notificaciones a clientes*/
   List<NotificacionCliente> notificaciones = new ArrayList<>();
    /*lista de notificaciones*/
    List<Notificaciones> lst_notificaciones = notificacionesService.listNotificaciones(offset,maxResults);
   /* Separo las notificaciones de clientes*/
    for (Notificaciones n:lst_notificaciones){
      if(n.getNotif_sistema().isEmpty()){
        /*Para cada notificacion creo una instancia de un objeto NotificacionCliente e inserto la notificación y el cliente
        a continuación lo meto en la lista
        Esa es la lista que se le pasa a la vista.*/
        NotificacionCliente not  = new NotificacionCliente();
        not.setCliente(n.getDispositivo().get(0).getCliente());
        not.setNotificacion(n);
        notificaciones.add(not);
      }
      
    }
    
    /* número total de notificaciones*/
    int count = lst_notificaciones.size();
    /*datos para la vista*/
    model.addAttribute("count", count);
    model.addAttribute("ofsset", offset);
    model.addAttribute("maxResults", maxResults);
    model.addAttribute("notif", notificaciones);
    
    return "notificacionesClientes";
    
  }
   /**
  * Método para rellenar el campo tipo de notificacion en la vista.
  * @return devuelve un HashMap con los tipos de notificacion de la bbdd
  */
 @ModelAttribute("tipos")
 public Map<Integer,String> rellenaTipoNotif() {
   List<Tipo_notif> lst=tipo_notifService.listTipo_notifs();
   
   Map<Integer,String> tipos = new HashMap<>();
   for(Tipo_notif t:lst){
     int i=t.getIdtipo_notif();
     String tipo=t.getTipo();
     tipos.put(i,tipo);
   }
  return tipos;
 }

  /**
   * Método para llamar a la vista para crear notificaciones de sistema,
   * prepara los datos para la vista
   * @param model
   * @return
   */
  @RequestMapping(value="crearNotificacion",method = RequestMethod.GET)
  public String crearNotificacion (Model model){
    Notificaciones notificacion = new Notificaciones();
    Tipo_notif tn = new Tipo_notif();
    Notif_sistema ns = new Notif_sistema();
    model.addAttribute("tipo", tn);
    model.addAttribute("notificacion", notificacion);
    model.addAttribute("notif_sistema", ns);
    model.addAttribute("tipos_notif", tipo_notifService.listTipo_notifs());
    return "crearNotificacion";
    
    
  }
  
  /**
   * Método que recibe los datos del formulario de creación de notificaciones
   * los almacena en la base de datos y envía la notificación a través del 
   * servicio Firebase Cloud Messaging
   * 
   * @param notificacion
   * @param tipos
   * @param model
   * @return
   */
  @RequestMapping(value="crearNotificacion",method = RequestMethod.POST)
  public String almacenaNotificacion(Model model,String tipos,
          @ModelAttribute("notificacion")Notificaciones notificacion){
    
    String result="";
    /*obtengo un objeto tipo_notif */
    Tipo_notif tipo= tipo_notifService.getTipo_notifById(Integer.parseInt(tipos));
    
    
    /*creo un objeto notif_sistema*/
    Notif_sistema ns =new Notif_sistema();
    ns.setTipo_notif(tipo);
    ns.setNotificacion(notificacion);
    notificacion.setEntregada(0);
     try {
       /*envío la notificación*/
       result=PushNotificationHelper.sendPushNotificationTopic(tipo.getTipo(), notificacion.getMensaje());
     } catch (IOException ex) {
       java.util.logging.Logger.getLogger(NotificacionController.class.getName()).log(Level.SEVERE, null, ex);
     }
     if (result.equals("exito")){
       notificacion.setEntregada(1);
     }else{
       notificacion.setEntregada(0);
     }
    /*almaceno en la base de datos*/ 
    notificacionesService.addNotificaciones(notificacion);
    notif_sistemaService.addNotif_sistema(ns);
    LOG.info(notificacion.toString());

    
  
    return "redirect:notificaciones";
  }
  
  /**
   * Método para crear la vista del formulario de creación de notificaciones para clientes
   * @param model
   * @return
   */
  @RequestMapping(value="crearNotificacionClientes",method = RequestMethod.GET)
  public String crearNotificacionClientes (Model model){
    Notificaciones notificacion = new Notificaciones();
    List<Clientes> c = clientesSrevice.listClientes();
    
    model.addAttribute("clientes", c);
    model.addAttribute("notificacion", notificacion);
    
    
    return "crearNotificacionClientes";
    
    
  }
  
  /**
   * Método para crear y enviar las notificaciones a los clientes
   * @param notificacion
   * @param clientes
   * @param tipos
   * @param model
   * @return
   * @throws org.codehaus.jettison.json.JSONException
   */
  @RequestMapping(value="crearNotificacionClientes",method = RequestMethod.POST)
  public String almacenaNotificacionClientes(Model model,String clientes,
          @ModelAttribute("notificacion")Notificaciones notificacion) {
    
    String result="";
    /*el id del cliente seleccionado*/
    int idcliente =Integer.parseInt(clientes);
    /*dispositivos del cliente, las notificaciones se envian a todos los dispositios en el que el cliente tenga instalada la app*/
    List<Dispositivos> d = dispositivosService.listaDispositivosCliente(idcliente);
    /*añado los dispositivos del cliente a la notificacion*/
    for (Dispositivos dp:d){
      notificacion.getDispositivo().add(dp);
    }
    /*en principio está no entregada*/
    notificacion.setEntregada(0);
     
     try {
       for (Dispositivos dispo:d){/*envio la notificacion a todos los dispositivos*/
         dispo.getNotificacion().add(notificacion);
         result=PushNotificationHelper.sendPushNotification(dispo.getToken(), notificacion.getMensaje());
       }
       
     } catch (IOException ex ) {
       java.util.logging.Logger.getLogger(NotificacionController.class.getName()).log(Level.SEVERE, null, ex);
     }
     
     if (result.equals("exito")){
       notificacion.setEntregada(1);
     }else{
       notificacion.setEntregada(0);
     }
     notificacionesService.addNotificaciones(notificacion);
    return "redirect:notificacionesClientes";
  }
}
