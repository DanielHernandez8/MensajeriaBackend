package com.danielhernandez.mensajeria.dto;

import com.danielhernandez.mensajeria.persistence.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MessageDTO {
    private String message;
    private List<Integer> usuarios;
    private Integer remitenteId;
}
