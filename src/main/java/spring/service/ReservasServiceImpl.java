/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

import spring.DAO.ReservasDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import spring.model.Reservas;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jcpm0
 */
@Service
public class ReservasServiceImpl implements ReservasService{
        
    @Autowired
    private ReservasDao reserva;
    

    @Override
    @Transactional
    public void addReservas(Reservas c) {
        this.reserva.addReservas(c);
    }

    @Override
    @Transactional
    public void updateReservas(Reservas c) {
        this.reserva.updateReservas(c);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Reservas> listReservas() {
        return this.reserva.listReservas();
    }

    @Override
    @Transactional
    public Reservas getReservasById(int id) {
        return this.reserva.getReservasById(id);
    }

    @Override
    @Transactional
    public void removeReservas(int id) {
        this.reserva.removeReservas(id);
    }
}
