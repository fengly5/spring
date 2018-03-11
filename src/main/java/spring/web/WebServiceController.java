/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.web;

import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.model.Carta;
import spring.model.Clientes;
import spring.model.Menu;
import spring.model.Menu_has_platos;
import spring.model.Platos_has_carta;
import spring.model.Reservas;
import spring.service.CartaService;
import spring.service.ClientesService;
import spring.service.MenuService;
import spring.service.Menu_has_platosSerice;
import spring.service.PlatosService;
import spring.service.Platos_has_cartaService;
import spring.service.ReservasService;

/**
 *
 * @author jcpm0
 */
@RestController
public class WebServiceController {
     private static final Logger LOG = 
          Logger.getLogger(WebServiceController.class.getName());
  @Autowired
  private CartaService cartaService;
  @Autowired
  private MenuService menuService;
  @Autowired
  private Menu_has_platosSerice mhpService;
  @Autowired 
  private Platos_has_cartaService phcService;
  @Autowired
  private ReservasService reservasService; 
  @Autowired
  private ClientesService clientesService;
  
  @RequestMapping (value="/REST/menu",method = RequestMethod.GET)
  public ResponseEntity<List<Menu>> listMenu(){
        List<Menu> phc = menuService.listMenus();
        LOG.info("entra. "+phc.toString());
        if(phc.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(phc, HttpStatus.OK);
    }
    @RequestMapping (value="/REST/menu/{id}",method = RequestMethod.GET)
  public ResponseEntity<List<Menu_has_platos>> listMenu(@PathVariable("id")int id){
        List<Menu_has_platos> mhp = mhpService.listMenus_has_platos(id);
        LOG.info("entra. "+mhp.toString());
        if(mhp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(mhp, HttpStatus.OK);
    }
  
  @RequestMapping (value="/REST/carta",method = RequestMethod.GET)
  public ResponseEntity<List<Carta>> listCarta(){
        List<Carta> carta = cartaService.listCartas();
        LOG.info("entra. "+carta.toString());
        if(carta.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(carta, HttpStatus.OK);
    }
    @RequestMapping (value="/REST/carta/{id}",method = RequestMethod.GET)
  public ResponseEntity<List<Platos_has_carta>> listCarta(@PathVariable("id")int id){
        List<Platos_has_carta> mhp = phcService.listPlatos_has_cartas(id);
        LOG.info("entra. "+mhp.toString());
        if(mhp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(mhp, HttpStatus.OK);
    }

  /**
   *
   * @param id
   * @return
   */
  @RequestMapping (value="/REST/reservas/{id}",method = RequestMethod.GET)
  public ResponseEntity<List<Reservas>> listReservas(@PathVariable("id")int id){
        List<Reservas> mhp = reservasService.listReservas(id);
        LOG.info("entra. "+mhp.toString());
        if(mhp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(mhp, HttpStatus.OK);
    }
  
  @RequestMapping(value="/REST/cliente",method = RequestMethod.GET)
  public ResponseEntity<Clientes> compruebaCliente(@RequestParam("email")String email,@RequestParam("passwd")String passwd){
    LOG.info(passwd);
    LOG.info(email);
    String email_limpio= email.replace("\"", "\'");
    String passwd_limpio= passwd.replace("\"", "");
    LOG.info(email_limpio);
    Clientes c = clientesService.findClienteByEmail(email_limpio);
    
    if (c == null){
      LOG.info("c = null");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }else{
      if (c.getPaswd().equals(passwd_limpio)){
        LOG.info("contraseña valida");
        return new ResponseEntity<>(c,HttpStatus.OK);
      }else{
        LOG.info("contraseña no valida");
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    }
      
  }
}
