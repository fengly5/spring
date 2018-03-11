/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.model.Menu;
import spring.model.Menu_has_platos;
import spring.model.Platos;
import spring.service.MenuService;
import spring.service.Menu_has_platosSerice;
import spring.service.PlatosService;
import spring.validacion.MenuDetalle;
import spring.validacion.MenuDetalle.Plato;

/**
 *
 * @author jcpm0
 */
@Controller
@Transactional
public class MenuController {

  private static final Logger LOG
          = Logger.getLogger(MenuController.class.getName());

  @Autowired
  private MenuService menuService;
  @Autowired
  private Menu_has_platosSerice mhpService;
  @Autowired
  private PlatosService platosService;

  @RequestMapping(value = "menu")
  public String list(Model model, Integer offset, Integer maxResults) {

    if (offset == null) {
      offset = 0;
    }
    Menu menu = new Menu();
    /*Contamos el número de cartas para pasarselo a la vista para el paginador*/
    int count = menuService.listMenus().size();
    /*paso a la vista la lista de cartas*/
    model.addAttribute("menus", menuService.listMenus(offset, maxResults));
    /*el número total de registros para el paginador*/
    model.addAttribute("count", count);
    /*desplazamiento dentro del número de registros.*/
    model.addAttribute("offset", offset);
    /*objeto carta*/
    model.addAttribute("menu", menu);
    return "menu";

  }
  @RequestMapping(value="/crearMenu",method = RequestMethod.POST)
  public String cearMenu(@ModelAttribute("menu")Menu menu){
    menuService.addMenu(menu);
    return "menu";
  }
  
  @RequestMapping(value="/editaMenu/{id}",method = RequestMethod.GET)
  public String editaMenu(Model model, @ModelAttribute("detalle")MenuDetalle detalle,
          @PathVariable int id){
    
    /*obtengo el menu*/
    Menu menu = menuService.getMenuById(id);
   
    /*Preparo el objeto MenuDetalle*/
    detalle = new MenuDetalle();
    detalle.setMenu(menu);
    detalle=rellenaDetalle( mhpService.listMenus_has_platos(menu.getIdmenu()), detalle, menu);
    /*preparo datos para la vista*/
   
    model.addAttribute("plato", platosService.listarPlatosMenu());
    model.addAttribute("detalle", detalle);
    model.addAttribute("menus", menuService.listMenus());
    
    return "editaMenu";
  }
  @RequestMapping(value="/listadoMenu/{id}",method = RequestMethod.GET)
  public String listadoMenu(Model model,
          @PathVariable int id, @ModelAttribute("detalle")MenuDetalle detalle){
    
    Menu menu = menuService.getMenuById(id);
   
    model.addAttribute("mhp", mhpService.listMenus_has_platos(menu.getIdmenu()));
   
    model.addAttribute("menu", menu);
    return "listaPlatosMenu";
  }
  
  @RequestMapping(value="/actualizaMenu",method = RequestMethod.POST)
  public String actualizaMenu(Model model,@ModelAttribute("detalle")MenuDetalle detalle, BindingResult results){
    LOG.info("entra en actualiza");
       
    LOG.info(detalle.toString());
    Menu menu = detalle.getMenu();
    LOG.info(results.toString());
    insertaPlatoMenu(detalle,menu);
     int count = menuService.listMenus().size();
    /*paso a la vista la lista de cartas*/
    model.addAttribute("menus", menuService.listMenus(0, 5));
    /*el número total de registros para el paginador*/
    model.addAttribute("count", count);
    /*desplazamiento dentro del número de registros.*/
    model.addAttribute("offset", 0);
    /*objeto carta*/
   model.addAttribute("menu", menu);
    
    return "menu";
   
  }
     @ModelAttribute("platos")
 public Map<Integer,String> rellenaPlatos() {
   List<Platos> lst=platosService.listarPlatosMenu();
   
   Map<Integer,String> tipos = new HashMap<>();
   for(Platos p:lst){
     int i=p.getIdPlato();
     String tipo=p.getNombre();
     tipos.put(i,tipo);
   }
  return tipos;
 }
 
 private MenuDetalle rellenaDetalle(List<Menu_has_platos> lista,
         MenuDetalle detalle, Menu menu){
  
   
    detalle.setMenu(menu);
    LOG.info("RellenaDetalle: "+lista.toString());
    if(lista.isEmpty()){
      for (int i= 0;i<3;i++){
       Plato p = new Plato();
          p.setPlato("Selecciona plato...");
          p.setIdplato(0);
          detalle.getPrimeros().add(p);
           detalle.getSegundos().add(p);
           detalle.getPostres().add(p);
          
    }
    }
 
    for (Menu_has_platos mhp:lista){
      switch(mhp.getTipo()){
        case "Primero":{
          Plato p = new Plato();
          String nombre=mhp.getPlato().getNombre();
          int id = mhp.getPlato().getIdPlato();
          p.setPlato(nombre.trim());
          p.setIdplato(id);
          detalle.getPrimeros().add(p);
        }
        break;
        case "Segundo":{
          Plato p = new Plato();
          String nombre=mhp.getPlato().getNombre();
          int id = mhp.getPlato().getIdPlato();
          p.setPlato(nombre.trim());
          p.setIdplato(id);
          detalle.getSegundos().add(p);
        }
        break;
        case "Postre":{
          Plato p = new Plato();
          String nombre=mhp.getPlato().getNombre();
          int id = mhp.getPlato().getIdPlato();
          p.setPlato(nombre.trim());
          p.setIdplato(id);
          detalle.getPostres().add(p);
        }
        break;
       
      }
    }
    LOG.info("RellenaDetalle: "+detalle.toString());
    return detalle;
 }
 
 private List<Menu_has_platos> MenuDetalleAMenu_has_Platos(MenuDetalle detalle, Menu menu){
   List<Menu_has_platos> datos_formulario = new ArrayList<>();
   for (Plato p:detalle.getPrimeros()){
     Menu_has_platos m = new Menu_has_platos();
     m.setMenu(menu);
     m.setTipo("Primero");
     Platos plato = platosService.getPlatosById(Integer.parseInt(p.getPlato()));
     m.setPlato(plato);
     datos_formulario.add(m);
   }
      for (Plato p:detalle.getSegundos()){
     Menu_has_platos m = new Menu_has_platos();
     m.setMenu(menu);
     m.setTipo("Segundo");
     Platos plato = platosService.getPlatosById(Integer.parseInt(p.getPlato()));
     m.setPlato(plato);
     datos_formulario.add(m);
   }
         for (Plato p:detalle.getPostres()){
     Menu_has_platos m = new Menu_has_platos();
     m.setMenu(menu);
     m.setTipo("Postre");
     Platos plato = platosService.getPlatosById(Integer.parseInt(p.getPlato()));
     m.setPlato(plato);
     datos_formulario.add(m);
   }
   return datos_formulario;
 }
 private void insertaPlatoMenu (MenuDetalle detalle, Menu menu){
   
 
   List<Menu_has_platos> lst=mhpService.listMenus_has_platos(menu.getIdmenu());
   List<Menu_has_platos> datos_formulario;
   LOG.info(lst.toString());
   LOG.info("detalle: "+detalle.toString());
   
   if(lst.isEmpty()){/*si esta lista está vacia es que el menú aún no tiene platos*/
     LOG.info("inseta: dentro del if");
     datos_formulario=MenuDetalleAMenu_has_Platos(detalle, menu);
     LOG.info(datos_formulario.toString());
     for (Menu_has_platos m:datos_formulario){
       LOG.info("menu: "+m.getMenu().getIdmenu());
       LOG.info("plato: "+m.getPlato().getIdPlato());
       LOG.info("primarukey: "+m.getPrimaryKey().getMenu().getIdmenu()+" - "+m.getPrimaryKey().getPlato().getIdPlato());
       mhpService.addMenu_has_platos(m);
     }
   }else{/*si la lista tiene items es que es un menu que se está editando*/
     LOG.info("inserta: dentro el else");
     datos_formulario=MenuDetalleAMenu_has_Platos(detalle, menu);
     LOG.info("dentro del else datos_formulario: "+datos_formulario.toString());
     for (int i=0;i<lst.size();i++){
       LOG.info("i: "+i+" "+lst.get(i).getPlato().getIdPlato() + " - "+ datos_formulario.get(i).getPlato().getIdPlato());
       if(lst.get(i).getPlato().getIdPlato() != datos_formulario.get(i).getPlato().getIdPlato()){
         lst.get(i).setPlato(datos_formulario.get(i).getPlato());
         mhpService.updateMenu_has_platos(lst.get(i));
       
         }
       }
     }
   }
   
 
}
