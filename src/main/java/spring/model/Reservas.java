/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author jcpm0
 */
@Entity
@Table(name="reservas")
public class Reservas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idreservas")
    private int idreservas;
    
    @Column(name="nComensales")
    private int nComensales;
    
    @Column (name="turno")
    private String turno;
    

    @Column(name="fechaHora")
    @Temporal(TemporalType.DATE)
    private Date fechaHora;
    
    @ManyToOne
    @JoinColumn(name="clientes_idclientes")
    private Clientes cliente;

    public Reservas() {
    }

    public Reservas(int nComensales, String turno, 
            Date fechaHora, Clientes cliente) {
        this.nComensales = nComensales;
        this.turno = turno;
       
        this.fechaHora = fechaHora;
        this.cliente = cliente;
    }

    public int getIdreservas() {
        return idreservas;
    }

    public void setIdreservas(int idreservas) {
        this.idreservas = idreservas;
    }

    public int getnComensales() {
        return nComensales;
    }

    public void setnComensales(int nComensales) {
        this.nComensales = nComensales;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }



    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fecha) {
        this.fechaHora = fecha;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Reservas{" + "idreservas=" + idreservas + ", nComensales="
                + nComensales + ", turno=" + turno + ", fecha=" +
                fechaHora + ", cliente=" + cliente + '}';
    }
    
    
    
    
}
