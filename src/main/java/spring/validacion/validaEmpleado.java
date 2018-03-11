/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.validacion;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import spring.model.Empleados;

/**
 *
 * @author jcpm0
 */
public class validaEmpleado implements Validator{
 

    private static final int MINIMUM_PASSWORD_LENGTH = 6;

    @Override
    public boolean supports(Class clazz) {
       return Empleados.class.isAssignableFrom(clazz);
    }

    
 
    

  @Override
  public void validate(Object o, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empleado.login.vavio");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paswd", "empleado.paswd.vacio");
    Empleados empleado =(Empleados) o;
if (empleado.getPaswd() != null
             && empleado.getPaswd().trim().length() < MINIMUM_PASSWORD_LENGTH) {
          errors.rejectValue("password", "field.min.length",
                new Object[]{Integer.valueOf(MINIMUM_PASSWORD_LENGTH)},
                "The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
       }
  }
 }

