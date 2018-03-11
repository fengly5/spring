/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.model.Carta;
import spring.model.Platos;
import spring.model.Platos_has_carta;
import spring.model.Platos_has_cartaId;
import spring.model.Tipo_plato;
import spring.service.CartaService;
import spring.service.PlatosService;
import spring.service.Platos_has_cartaService;
import spring.validacion.CartaPlato;

/**
 * Controlador para operaciones CRUD de la tabla cartas.
 * @author jcpm0
 */
@Controller
@Transactional/* anotación para que spring se encargue de las transacciones*/
public class CartaController {
  private static final Logger LOG = 
          Logger.getLogger(CartaController.class.getName()); 
  
  /*inyección de dependencias*/
  @Autowired
  private CartaService cartaService;
  @Autowired
  private PlatosService platosService;
  @Autowired
  private Platos_has_cartaService phcService;
  
  private Platos_has_cartaId id;
  
  /**
   * Método que llama a la vista carta.jsp con el listado de cartas.
   * @param model Panámetro para pasar datos a la vista
   * @param offset Desplazamiento en la paginación
   * @param maxResults El máximo de resultados por página
   * @return  devuelve el nombre de la vista 
   */
  @RequestMapping(value="carta")
  public String list(Model model, Integer offset, Integer maxResults){
    
      if (offset == null){
    offset=0;
  }
  Carta carta = new Carta();    
  /*Contamos el número de cartas para pasarselo a la vista para el paginador*/
  int count = cartaService.listCartas().size();
  /*paso a la vista la lista de cartas*/
  model.addAttribute("cartas",cartaService.listCartas(offset,maxResults));
  /*el número total de registros para el paginador*/
  model.addAttribute("count", count);
  /*desplazamiento dentro del número de registros.*/
  model.addAttribute("offset",offset);
  /*objeto carta*/
  model.addAttribute("carta", carta);
  return "carta";
  }

  /**
   * La anotación @ModelAttribute indica que este método se va a usar para compner
   * un modelo de datos para pasarlo a la vista.
   * En concreto se va a usar para rellenar el select del formulario con la lista
   * de platos de la carta.
   * @return devuelve un HasMap formado por pares con el idPlato y el nombre
   */
   @ModelAttribute("platos")
 public Map<Integer,String> rellenaPlatos() {
   List<Platos> lst=platosService.listaPlatosCarta();
   
   Map<Integer,String> tipos = new HashMap<>();
   for(Platos p:lst){
     int i=p.getIdPlato();
     String tipo=p.getNombre();
     tipos.put(i,tipo);
   }
  return tipos;
 }
 
  /**
   * Método para editar los platos de una carta
   * @param model Objeto para pasar datos a la vista
   * @param id el id de la carta que se va a editar
   * @param nuevoPlatoCarta Clase auxiliar con el modelo de datos.
   * @return devuelve el nombre de la vista asociada
   */
  @RequestMapping(value="/editaCarta/{id}",method = RequestMethod.GET)
  public String editaCarta(Model model, @PathVariable int id,
          @ModelAttribute("nuevoPlatoCarta")CartaPlato nuevoPlatoCarta ){
    /* Obtengo la carta que se va a modificar a partir del id que se pasa
    en la url*/
    Carta carta = cartaService.getCartaById(id);
    /* Preparo los datos para psar a la vista
     le paso:
        la carta, 
        la lista de platos de esa carta
        Un obejto CartaPlato que es en el que luego el formulario va a devolver
        los datos.*/   
    model.addAttribute("carta", carta);
    model.addAttribute("platosEnCarta", phcService.listPlatos_has_cartas(id));
    model.addAttribute("nuevoPlatoCarta",nuevoPlatoCarta );
    
    return "editaCarta";
  }
/**
 * Método para crear una nueva carta
 * @param carta Objeto Carta en el que recibe los datos del formulario.
 * @return la vista a la que tiene que ir
 */  
@RequestMapping(value="/crearCarta",method=RequestMethod.POST)
public String crearCarta(@ModelAttribute("carta")Carta carta){
  
 /* crea la carta*/
  cartaService.addCarta(carta);
  return "carta";    
  
}

/**
 * Método para borrar una carta
 * @param id el id de la carta
 * @return redirecciona a la vista carta.
 */
  @RequestMapping(value = "/borraCarta/{id}", method = RequestMethod.GET)
 public ModelAndView delete(@PathVariable int id) {
   /*Llamada al servicio para borrar la carta*/
  cartaService.removeCarta(id);
   /*volvemos a la vista carta*/
  return new ModelAndView("redirect:/Carta");
 }
 /**
  * Método para borrar un plato de la carta
  * @param id nombre del plato
  * @param carta id de la carta
  * @return 
  */
   @RequestMapping(value = "/borraPlatoCarta/{id}/{carta}", method = RequestMethod.GET)
 public ModelAndView deletePlatoCarta(@PathVariable String id,@PathVariable int carta) {
    
    /*objeto Platos_has_cartaId para manejar la clave primaria compuesta de la tabla
   platos_has_carta*/   
    Platos_has_cartaId id1=null;
    /*obtengo la lista de platos de la carta*/
    List<Platos_has_carta> lst=phcService.listPlatos_has_cartas(carta);
    /*recorro la lista buscando el plato*/
    for (Platos_has_carta phc :lst){
      /*si lo encuentro obtengo la clave primaria y la cargo en id1*/
      if (phc.getPrimaryKey().getPlato().getNombre().equals(id)){
                
        id1=phc.getPrimaryKey();
      }
    }
    /* borro el plato*/
    phcService.removePlatos_has_carta(id1);
   /*volvemos a la vista platos*/
   ModelAndView mv = new ModelAndView();
   mv.addObject("carta", cartaService.getCartaById(carta));
   mv.setViewName("/editaCarta");
   mv.addObject("nuevoPlatoCarta", new CartaPlato());
   mv.addObject("platosEnCarta", phcService.listPlatos_has_cartas(carta));
  return mv;
 }
/**
 * Método para actualizar el estado de la propiedad aparece
 * @param model
 * @param nombre
 * @param idcarta
 * @param estado
 * @return 
 */
 @RequestMapping(value="/actualizaEstado/{nombre}/{idcarta}/{estado}",method = RequestMethod.GET)
 public String actualizaEstado(Model model,@PathVariable String nombre,@PathVariable int idcarta,@PathVariable String estado){
   
   Carta carta = cartaService.getCartaById(idcarta);
   /*obtengo lista platos de la carta*/
   List<Platos_has_carta> lst = phcService.listPlatos_has_cartas(idcarta);
   /*busco el plato y cambio el estado*/
   for (Platos_has_carta  phc:lst){
     if (phc.getPlato().getNombre().equals(nombre)){
       if (estado.equals("on") && phc.getAparece() == 0){
         phc.setAparece(1);
         phcService.updatePlatos_has_carta(phc);
       }
       if (estado.equals("off") && phc.getAparece() == 1){
         phc.setAparece(0);
         phcService.updatePlatos_has_carta(phc);
       }
     }
   }
     model.addAttribute("carta", carta);
          model.addAttribute("platosEnCarta", phcService.listPlatos_has_cartas(idcarta));
           model.addAttribute("nuevoPlatoCarta",new CartaPlato());
      return "redirect:/editaCarta/{idcarta}";
 }
 /**
  * Métdo para actualizar la carta con un plato nuevo
  * @param model objeto para pasar datos a la vista
  * @param nuevoPlatoCarta Datos del nuevo plato
  * @return 
  */
 @RequestMapping(value="/actualizaCarta", method = RequestMethod.POST)
 public String actualizaCarta(Model model,@ModelAttribute("nuevoPlatoCarta")CartaPlato nuevoPlatoCarta){
   /*Si el plato seleccionado ya existe en la carta devolvemos este error*/
   String error="El plato ya existe en la carta, selecciona otro.";
   /*Obtengo los datos desde el formulario*/
   Platos plato= new Platos();
   int aparece=0;
   /*el id de la carta*/
   int idCarta = Integer.parseInt(nuevoPlatoCarta.getIdCarta());
   /*Obtengo la lista de platos de la carta*/
   List<Platos_has_carta> lst = phcService.listPlatos_has_cartas(idCarta);
   
   /*Obtengo el plato*/
    if (!nuevoPlatoCarta.getIdPlato().equals("")){
      int i = Integer.parseInt(nuevoPlatoCarta.getIdPlato());
       plato = platosService.getPlatosById(i);
    }
    /* Obtengo el valos del atributo aparece*/
    if (!nuevoPlatoCarta.getAparece().equals("")){
       aparece = Integer.parseInt(nuevoPlatoCarta.getAparece());
    }
    /*Obtengo la carta*/
    Carta carta = cartaService.getCartaById(idCarta);
    /*Instancio un objeto de Platos_has_carta */
    Platos_has_carta phc = new Platos_has_carta();
    phc.setPlato(plato);
    phc.setCarta(carta);
    phc.setAparece(aparece);
    /*ahora compruebo si ya existe en la bbdd*/
    Boolean existe = false;
    for(Platos_has_carta p:lst){
      if(p.getPlato().getIdPlato() == phc.getPlato().getIdPlato() && p.getCarta().getIdcarta() == phc.getCarta().getIdcarta()){
        existe = true;
      }
    }
    /* si no existe, lo inserto y preparo los datos para recargar la vista con 
    la nueva lista de platos que incluirá el recién insertado*/
    if (!existe){
      try{
          phcService.addPlatos_has_carta(phc);
          model.addAttribute("carta", carta);
          model.addAttribute("platosEnCarta", phcService.listPlatos_has_cartas(idCarta));
          
      }catch (Exception e){
      
                LOG.info(e.toString());
      }
    }else{/*si existe preparo la vista con el mensaje de error y la lista de platos tal como estaba*/
      
       model.addAttribute("msg", error);
      model.addAttribute("carta", carta);
     
      model.addAttribute("platosEnCarta", lst);
     
      
    }
    /*en cualquier caso siempre se vuelve a la vista editaCarta.*/
 return "/editaCarta";
 }
}
