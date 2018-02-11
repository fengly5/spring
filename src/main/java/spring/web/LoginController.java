/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.web;



import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import spring.model.Empleados;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.service.EmpleadosService;



/**
 *
 * @author jcpm0
 */
@Controller
public class LoginController  {
    private static final Logger LOG = 
            Logger.getLogger(LoginController.class.getName());
    
    @Autowired
    public EmpleadosService empleadoService;

    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest hsr, 
            HttpServletResponse hsr1) throws Exception {
        LOG.info("entra en handledrequest");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        mv.addObject("empleado", new Empleados());
       
        return mv;
    }
    @RequestMapping(value="/login",method=RequestMethod.POST)    
    public String doLogin(Empleados emp, ModelMap model,HttpSession session){
      
        model.addAttribute("login", emp.getLogin());
        model.addAttribute("paswd", emp.getPaswd());
        
        Empleados logado=null;
        LOG.info("entra en dologin "+ emp.toString());
        logado = empleadoService.LoginEmpleado(emp.getLogin(),emp.getPaswd());
        LOG.info("logado= "+ logado.toString());
        if (logado != null){
            session.setAttribute("empleado", emp);
            LOG.info("return del if");
               return "redirect:/inicio";
        }else{
            LOG.info("return del else");
             return "redirect:login.jsp";
        }
        
                
             
    }
}
