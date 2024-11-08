package com.danielhernandez.mensajeria.persistence.entities;

import jakarta.persistence.*;

@Entity
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
