package com.project.engineer.project.repository;

import com.project.engineer.project.model.Reservation;
import com.project.engineer.project.service.ReservetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;

public abstract class ReservationRepositoryImplementation implements ReservationRepository {

    @Autowired
    @Qualifier("ReservationRepository")
    ReservationRepository resverationRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    ReservetService reservetService;

    public ReservationRepositoryImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <S extends Reservation> S save(S s) {
        reservetService.setReservation(s);
        resverationRepository.save(s);
        return s;
    }

}
