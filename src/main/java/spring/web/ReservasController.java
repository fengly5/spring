/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.web;


import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.model.Reservas;

/**
 *
 * @author jcpm0
 */
@Controller
public class ReservasController {
  private static final Logger logger =  Logger.getLogger(LoginController.class.getName()); 

  @RequestMapping("reservas.htm")
  public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
    logger.info("entra en handledrequest");
    ModelAndView mv = new ModelAndView();
    mv.setViewName("reservas");
    mv.addObject("reservas", new Reservas());
       
    return mv;
  }
  
  
}
