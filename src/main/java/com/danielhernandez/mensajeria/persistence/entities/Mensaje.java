package com.danielhernandez.mensajeria.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@JsonIgnoreProperties({"destinatario","remitente"})
@Entity
@Data
public class Mensaje {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//Para generar números autoincrementados
    private int id;
    @ManyToOne
    private User remitente;
    @ManyToOne
    private User destinatario;
    private String mensaje;
}
