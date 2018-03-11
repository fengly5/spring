/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.web;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.model.Platos;
import spring.model.Tipo_plato;
import spring.service.PlatosService;
import spring.service.Tipo_platoService;



/**
 * Contolador para las operaciones CRUD con los platos de la carta
 * 
 * @author jcpm0
 */
@Controller
@Transactional/* anotación para que spring se encargue de las transacciones*/
public class PlatosController  {
 private static final Logger LOG = 
          Logger.getLogger(PlatosController.class.getName());

 /*Inyección de las dependencias necesarias*/
 @Autowired
 private PlatosService platosService;
 
 @Autowired
 private Tipo_platoService tipo_platoService;

 /**
  * Método que lista los platos de la carta paginados y lo pasa a la vista
  * platos.jsp
  * @param model Objeto Model para pasar atributos a la vista
  * @param offset Objeto Integer con el desplazamiento en el total de registros
  * @param maxResults Objeto Integer con el máximo de resultados por página
  * @return devuelve un String con el nombre de la vista asociada
  */
 @RequestMapping(value="platos")
 public String list(Model model, Integer offset, Integer maxResults){
  if (offset == null){
    offset=0;
  }
 
  /* cuento los platos que tiene la carta.*/ 
  int count = platosService.listaPlatosCarta().size();
  /*Añado al modelo la lista de platos de la carta*/
  model.addAttribute("platos", 
          platosService.listaPlatosCarta(offset, maxResults));
  /*Añado al modelo el número total de platos*/
  model.addAttribute("count", count);
  /*Añado al modelo el offset*/
  model.addAttribute("offset", offset);
  
  return "platos";
 }
 
 /**
  * Método para borrar un plato
  * @param id el id del plato
  * @return devuelve un objeto ModelAndView regresando a la vista platos
  */
 @RequestMapping(value = "/borraPlato/{id}", method = RequestMethod.GET)
 public ModelAndView delete(@PathVariable int id) {
   /*Llamada al servicio para borrar el plato*/
   platosService.removePlatos(id);
   /*volvemos a la vista platos*/
  return new ModelAndView("redirect:/platos");
 }
 /**
  * Método para editar un plato de la lista.
  * @param id recibe el id del plato
  * @param offset pasamos el offset para poder regresar luego a la misma página
  * @return devuelve un objeto ModelAndView con los atributos del modelo y 
  * el nombre de la vista.
  */
 @RequestMapping(value = "editaPlato/{id}")
 public ModelAndView edit(@PathVariable int id,
         @RequestParam(value= "offset") int offset ) {
   
   /* Variable para almacenar el tipo de plato actual*/
   String tipoPlatoActual=null;
   /*Obtengo el plato que voy a editar de la bbdd*/
   Platos plato = platosService.getPlatosById(id);
   /*Obtengo el tipo de plato actual del objeto plato */
  Set<Tipo_plato> tp=plato.getTipo_plato();
  for (Tipo_plato t:tp){
    if (t.getTipo()!= null)
    { 
     tipoPlatoActual=t.getTipo();
  }}
  /* Preparo el ModelAndView con los datos que necesito para la vista*/
  ModelAndView mv = new ModelAndView();
  mv.addObject("plato", plato);
  mv.addObject("tipoActual", tipoPlatoActual);
  mv.addObject("offset", offset);
  mv.setViewName("/editaPlato");
  return mv;
 }  
 /**
  * Método para rellenar el campo tipo de plato en la vista.
  * @return devuelve un HashMap con los tipos de platos de la bbdd
  */
 @ModelAttribute("tipoPlato")
 public Map<Integer,String> rellenaTipoPlato() {
   List<Tipo_plato> lst=tipo_platoService.listTipo_platos();
   
   Map<Integer,String> tipos = new HashMap<>();
   for(Tipo_plato t:lst){
     int i=t.getIdTipo();
     String tipo=t.getTipo();
     tipos.put(i,tipo);
   }
  return tipos;
 }
/**
 * Método para actualizar un plato. Recibe desde la vista los datos del 
 * plato modificado y el offset para luego volver a la misma página en el 
 * listado de platos.
 * @param plato objeto de tipo Platos con los datos del plato
 * @param tipo objeto tipo Tipo_platos
 * @param offset Desplazamiento dentro del total de registros
 
 * @param model
 * @return 
 */
@RequestMapping(value="/editaPlato",method = RequestMethod.POST)
public String actualizaPlato(@ModelAttribute("platos")Platos plato,
                             @ModelAttribute("tipo_plato")Tipo_plato tipo,
                             @RequestParam("offset")String offset,
                       
                               Model model)
{
 /*Obtengo el plato de la bbdd*/
 Platos p = platosService.getPlatosById(plato.getIdPlato());
  /* Obtengo un objeto tipo_plato a partir de la seleccion del formulario.
     En la vista el campo tipo está asociado con un objeto Tipo_plato según
     se declara en la cabecera con @ModelAttribute("tipo_plato")Tipo_plato tiop
     sin embargo cuando desde el formulario se recibe la información, al usar
     el método getTipo() en lugar de dar el String tipo, devuelve el id en
     forma de String.
     Como no he conseguido averiguar porque lo he adaptado para usarlo así. */
  
  /*si no se hace ningún cambio en el campo tipo del formulario en .getTipo()
    se obtiene el String con el tipo de plato que está actualmente 
    (tipoPlatoActual), si se hace cambio devuelve el idTipo en forma de String
    por lo que lo primero es comprobar si el String mide menos de 3, o sea que 
    puede ser un número y no un tipo de plato.
  */
  if(tipo.getTipo().length()< 3){
  /*Obtengo el tipo de plato por el id*/  
  Tipo_plato tp = 
          tipo_platoService.getTipo_platoById(Integer.parseInt(tipo.getTipo()));
  //creo un conjunto para poder añadirlo al objeto plato
  Set<Tipo_plato> tipos = new HashSet();
  tipos.add(tp);
  /* Camgio el tipo de plato en el plato*/
  p.setTipo_plato(tipos);
  }
  /*actualizo el resto de los campos.*/
  
  p.setNombre(plato.getNombre());
  p.setPrecioTapa(plato.getPrecioTapa());
  p.setPrecioMedia(plato.getPrecioMedia());
  p.setPrecioRacion(plato.getPrecioRacion());
  
  LOG.info("offset: "+offset);
  
  Integer offsetInt = new Integer(offset);
  
  //actualizo
  platosService.updatePlatos(p);
  
    int count = platosService.listaPlatosCarta().size();
  model.addAttribute("platos", 
          platosService.listaPlatosCarta(offsetInt, 10));
  
  model.addAttribute("count", count);
 
  model.addAttribute("offset",offsetInt);

  
  LOG.info("entra en POST");
  return "platos";
}

@RequestMapping(value="crearPlato",method = RequestMethod.GET)
public ModelAndView crearPlato(){
  LOG.info("crearPlato");
  /* instancio un plato*/
  Platos p = new Platos();
  Tipo_plato tp = new Tipo_plato();
  
  ModelAndView mv = new ModelAndView();
  mv.addObject("plato", p);
  mv.addObject("tipo_plato", tp);
  mv.setViewName("crearPlato");
 
  return mv;
}

@RequestMapping(value="crearPlato",method=RequestMethod.POST)
public String insertaPlato(Model model,
                          @ModelAttribute("platos")Platos plato,
                          @ModelAttribute("tipo_plato")Tipo_plato tipo){
  
  /*instancio un plato y tipo_plato*/
  Platos p=new Platos();
  Tipo_plato tp=new Tipo_plato();
  /*cargo el plato con los datos del formulario*/
  p.setNombre(plato.getNombre());
  if(tipo.getTipo().length()< 3){
  /*Obtengo el tipo de plato por el id*/  
  tp =tipo_platoService.getTipo_platoById(Integer.parseInt(tipo.getTipo()));
  //creo un conjunto para poder añadirlo al objeto plato
  Set<Tipo_plato> tipos = new HashSet();
  tipos.add(tp);
  /* Camgio el tipo de plato en el plato*/
  p.setTipo_plato(tipos);
  }
  p.setPrecioTapa(plato.getPrecioTapa());
  p.setPrecioMedia(plato.getPrecioMedia());
  p.setPrecioRacion(plato.getPrecioRacion());
  
  /*añado el plato a la bbdd*/
  platosService.addPlatos(p);
  
  /*Preparo la vista platos.
    le paso la cantidad de platos en count,
  la lista de platos en platos, y offset 0*/
  int count = platosService.listaPlatosCarta().size();
  model.addAttribute("platos", 
          platosService.listaPlatosCarta(0, 10));
  
  model.addAttribute("count", count);
 
  model.addAttribute("offset",0);
  
  return "platos";
}
}
