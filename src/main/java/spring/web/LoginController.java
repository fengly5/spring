/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.web;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.jboss.logging.Logger;
import spring.model.Empleados;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.service.EmpleadosService;
import spring.validacion.validaEmpleado;



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
    
//    private validaEmpleado validator;
    
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
    public String doLogin( 
     @Valid Empleados emp,Model model,BindingResult errors,HttpSession session){
      
//        validator.validate(emp, errors);
        LOG.info("emp: "+emp.getLogin());
        LOG.info("errors: "+errors.toString());
        if (errors.hasErrors()){
          return "login";
        }else{
         
        LOG.info("entra en dologin "+ emp.toString());
        try{
          Boolean logado = empleadoService.LoginEmpleado(emp.getLogin(),emp.getPaswd());
            LOG.info("logado= "+ logado.toString());
        
            if (false != logado){
              session.setAttribute("empleado", emp);
              LOG.info("return del if");
               return "redirect:/inicio";
            }else{
              LOG.info("return del else");
              model.addAttribute("empleado", emp);
              return "login";
            }
          }catch (NullPointerException e){
            return "redirect:login.jsp";
          }
     }
    }
        
}
