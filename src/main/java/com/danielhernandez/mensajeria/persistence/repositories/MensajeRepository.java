package com.danielhernandez.mensajeria.persistence.repositories;

import com.danielhernandez.mensajeria.persistence.entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    List<Mensaje> findByDestinatario_Id(Integer destinatarioId);  // Buscar mensajes por el ID del destinatario (mensajes recibidos)
}
