/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.EmpleadosDao;
import java.util.List;
import spring.model.Empleados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
@Service
public class EmpleadosServiceImpl implements EmpleadosService {
     
         
         
    @Autowired  
    private EmpleadosDao empleado;
     
     
    @Override
    @Transactional
    public void addEmpleados(Empleados c) {
        this.empleado.addEmpleados(c);
    }

    @Override
    @Transactional
    public void updateEmpleados(Empleados c) {
        this.empleado.updateEmpleados(c);
    }

    @Override
    @Transactional
    public List<Empleados> listEmpleados() {
        return this.empleado.listEmpleados();
    }

    @Override
    @Transactional
    public Empleados getEmpleadosById(int id) {
        return this.empleado.getEmpleadosById(id);
    }

    @Override
    @Transactional
    public void removeEmpleados(int id) {
        this.empleado.removeEmpleados(id);
    }

    @Override
    @Transactional
    public Empleados LoginEmpleado(String login, String paswd) {
        
        return this.empleado.LoginEmpleado(login,paswd);
    }
}
