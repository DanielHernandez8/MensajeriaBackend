package com.danielhernandez.mensajeria.persistence.repositories;

import com.danielhernandez.mensajeria.persistence.entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje,Integer> {
    Mensaje findMensajeById(Integer id);
}
