package Config;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.logging.Logger;
import javax.servlet.ServletContext;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { spring.config.SpringWebAppInitializer.class,
spring.config.WebConfig.class , spring.config.RootConfig.class})
public class TestContextConfiguration {
  
  private static final Logger LOG= 
          Logger.getLogger(TestContextConfiguration.class.getName());
  @Autowired
  private WebApplicationContext ctx;
  
  private MockMvc mockMvc;
  
  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
      .build();
  }
  @Test
  public void testApplicaticatonContextBeans() {
    ServletContext servletContext = ctx.getServletContext();
    LOG.info("servletContext ="+servletContext.toString());
    Assert.assertNotNull(servletContext);
        LOG.info("getBean iniciocontroller"+ ctx.getBean("inicioController"));
   
    Assert.assertNotNull(ctx.getBean("inicioController"));
    LOG.info("getBean Logincontroller"+ ctx.getBean("loginController"));
    Assert.assertNotNull(ctx.getBean("loginController"));

  }
}