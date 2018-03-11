/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.validacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author jcpm0
 */
public class StringDateConverter implements Converter<String, Date>{

  @Override
  public Date convert(String s) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return sdf.parse(s);
				} catch (ParseException e) {
					return null;
				}
  }
  
}
