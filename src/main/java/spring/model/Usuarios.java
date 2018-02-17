/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idusuarios")
    private int idusuarios;
    
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido1")
    private String apellido1;
    @Column(name="apellido2")
    private String apellido2;
    @Column(name="fechanacimiento")
    private String fechanacimiento;
    
    @OneToMany(mappedBy="usuarios",cascade=CascadeType.ALL)
    private Set<Empleados> empleados;
    
    @OneToMany(mappedBy="usuarios",cascade=CascadeType.ALL)
    private Set<Clientes> clientes;
    
    public Usuarios() {
    }

    public Usuarios(String nombre, String apellido1, String apellido2, String fechanacimiento) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechanacimiento = fechanacimiento;
    }

    public long getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Set<Empleados> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleados> empleados) {
        this.empleados = empleados;
    }

    public Set<Clientes> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Clientes> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "idusuarios=" + idusuarios + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", fechanacimiento=" + fechanacimiento + '}';
    }
    
    
}
