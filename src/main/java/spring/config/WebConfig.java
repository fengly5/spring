/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.config;


import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author jcpm0
 */
@Configuration
@EnableWebMvc
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan("spring")  
public class WebConfig implements WebMvcConfigurer {
   @Autowired
   private Environment env;
   
   @Bean
   public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver=
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }
    

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }
    


   @Bean
   public DataSource getDataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   @Bean
   public LocalSessionFactoryBean sessionFactory() {
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(getDataSource());
      
      Properties props=new Properties();
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//      props.put("hibernate.current_session_context_class", 
//              env.getProperty("hibernate.current_session_context_class"));
      sessionFactory.setHibernateProperties(props);
      sessionFactory.setPackagesToScan("spring.model");
      return sessionFactory;
   }

   @Bean
   public HibernateTransactionManager getTransactionManager() {
      HibernateTransactionManager transactionManager = 
              new HibernateTransactionManager();
      transactionManager.setSessionFactory(sessionFactory().getObject());
      return transactionManager;
   }
   

}
