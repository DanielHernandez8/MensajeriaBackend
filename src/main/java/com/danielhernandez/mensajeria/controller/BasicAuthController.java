package com.danielhernandez.mensajeria.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.danielhernandez.mensajeria.dto.MessageDTO;
import com.danielhernandez.mensajeria.persistence.entities.Mensaje;
import com.danielhernandez.mensajeria.persistence.entities.User;
import com.danielhernandez.mensajeria.persistence.repositories.MensajeRepository;
import com.danielhernandez.mensajeria.persistence.repositories.UserRepository;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/mensajeria")
public class BasicAuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MensajeRepository mensajeRepository;

    @PostMapping(path = "/login")
    public ResponseEntity<String> basicauth(UsernamePasswordAuthenticationToken upa) {
        // El objeto upa tiene información sobre el usuario y la contraseña
        // Si el login ha sido exitoso, a partir de ese momento, desde el front, mandaremos en la cabecera de cada petición el username y password que han provocado que el login sea exitoso
        User u = (User) upa.getPrincipal(); // Si en IntelliJ nos da un error cannot find symbol, significa que no esta pillando el getter de la id
        return ResponseEntity.ok().body("{\"resp\":\"Login exitoso\", \"id\":"+u.getId()+"}");

    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/{id}")
    public List<Mensaje> getMensajeById(@PathVariable("id") Integer id) {
        List<Mensaje> mensajes = mensajeRepository.findByDestinatario_Id(id);
        return mensajes;
    }


    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO, Principal principal) {
        // Obtener el remitente desde el usuario autenticado
        String username = principal.getName();  // Esto da el nombre del usuario autenticado
        User remitente = userRepository.findByUserName(username); // Buscar al remitente por el nombre de usuario

        if (remitente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Remitente no encontrado");
        }

        // Obtener los destinatarios de la base de datos según los IDs enviados desde el frontend
        List<User> destinatarios = userRepository.findAllById(messageDTO.getUsuarios());

        // Crear y guardar los mensajes
        for (User destinatario : destinatarios) {
            Mensaje nuevoMensaje = new Mensaje();
            nuevoMensaje.setMensaje(messageDTO.getMessage());
            nuevoMensaje.setRemitente(remitente);  // El remitente es el usuario autenticado
            nuevoMensaje.setDestinatario(destinatario);  // El destinatario de la lista

            // Guardar el mensaje en la base de datos
            mensajeRepository.save(nuevoMensaje);
        }

        return ResponseEntity.ok("Mensaje enviado correctamente a todos los destinatarios");
    }





}