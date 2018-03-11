/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.web;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.model.Reservas;
import spring.service.ReservasService;



/**
 *
 * @author jcpm0
 */
@Controller
public class inicioController {
    
private static final Logger LOG = 
        Logger.getLogger(inicioController.class.getName());
   

    @Autowired
    public ReservasService reservasService;
    
    @RequestMapping(value= "inicio", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest hsr,
            HttpServletResponse hsr1) throws Exception {
               LOG.info("entra en handledrequest");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/inicio");
        List<Reservas> lst=reservasService.listReservas();
        for (Reservas r:lst){
          LOG.info(r.toString());
        }
        mv.addObject("reservas", reservasService.listReservas());
        return mv;
    }


}
