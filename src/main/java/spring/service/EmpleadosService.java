/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import java.util.List;
import spring.model.Empleados;

/**
 *
 * @author jcpm0
 */
public interface EmpleadosService {
            public void addEmpleados(Empleados c);
	public void updateEmpleados(Empleados c);
	public List<Empleados> listEmpleados();
	public Empleados getEmpleadosById(int id);
	public void removeEmpleados(int id);
        public Boolean LoginEmpleado(String login, String paswd);
}
