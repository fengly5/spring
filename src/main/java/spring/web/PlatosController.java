/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.web;


import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.model.Platos;



/**
 *
 * @author jcpm0
 */
@Controller
public class PlatosController  {
 private static final Logger logger = 
          Logger.getLogger(PlatosController.class.getName());

    
    private SessionFactory sessionFactory;
//    @Override
//   @RequestMapping(value= "/platos", method = RequestMethod.GET)
//    public ModelAndView handleRequest(HttpServletRequest hsr, 
//            HttpServletResponse hsr1) throws Exception 
//    {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("platos");
//        mv.addObject("platos", listarPlatos());
//        return mv;
//    }
 @RequestMapping(value="/platos")
 public String list(Model model, Integer offset, Integer maxResults){
  model.addAttribute("platos", listarPlatos(offset, maxResults));
  model.addAttribute("count", count());
  logger.info("count: "+count().toString());
  model.addAttribute("offset", offset);
  return "/platos";
 }
  public Long count(){
       List<Platos> result= new ArrayList<Platos>();
      Session session = sessionFactory.getCurrentSession();
      String[] tipo={"Menu"};
      String hql1="select platos from Platos platos join fetch platos.tipo_plato p where p.tipo not in :tipo";
      Query query;
        query= session.createQuery(hql1);
        query.setParameterList("tipo", tipo);
        result = query.list();
        return (long) result.size();
 }
    private List<Platos> listarPlatos(Integer offset, Integer maxResults){
      String[] tipo={"Menu"};
      
      String hql1="select platos from Platos platos join fetch platos.tipo_plato p where p.tipo not in :tipo";
              List<Platos> result= new ArrayList<Platos>();
      try {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        logger.info("empieza transacción");
//        result=session.createCriteria(hql1)
//                .setFirstResult(offset!=null?offset:0)
//                .setMaxResults(maxResults!=null?maxResults:10).list();
        
        Query query;
        query= session.createQuery(hql1);
        query.setParameterList("tipo", tipo);
        query.setFirstResult(offset!=null?offset:0);
        query.setMaxResults(maxResults!=null?maxResults:10);
        logger.info("offset: "+offset);
        logger.info("max: "+maxResults);
        result = query.list();
        logger.info(result.toString());
//        session.getTransaction().commit();
          } catch (Exception e) {
              e.printStackTrace();
          }
        return result;
    }
    
}
