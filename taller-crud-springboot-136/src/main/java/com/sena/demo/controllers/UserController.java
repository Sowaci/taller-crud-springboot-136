package com.sena.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.demo.models.User;

// Indica que esta clase maneja peticiones HTTP y devuelve JSON automáticamente
@RestController

// Todas las rutas de este controlador empiezan con /user
@RequestMapping("user")
public class UserController {

    private ArrayList<User> users = new ArrayList<User>();

    UserController() {
        this.users.add(new User("1", "Alejo", "alejo@mail.com", 23, "34872347"));
    }

    // Devuelve la lista de todos los usuarios con el estado HTTP 200 OK
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity
                .status(200)
                .body(this.users);
    }

    // Recorre la lista buscando el usuario que coincida con el ID, si lo encuentra devuelve el usuario con 200 OK; si no existe devuelve 404 not found
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {

        for (User user : users) {
            if (user.getId().equals(id)) {
                return ResponseEntity
                        .status(200)
                        .body(user);
            }
        }

        return ResponseEntity
                .status(404)
                .build();
    }


    // Recibe un JSON en la petición, lo convierte en el objeto User, lo agrega a la lista y responde con 201 Created.
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        this.users.add(user);
        return ResponseEntity.status(201).body(user);
    }

    // Recorre la lista buscando el usuario por el ID, si lo encuentra remplaza sus campos con los datos nuevos, devolviendo la actualizarUsuario con 200 OK, o si no existe 404
    @PutMapping ("/{id}")
    public ResponseEntity<User> actualizarUsuario(@PathVariable String id, @RequestBody User actualizarUsuario){
        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getId().equals(id)){
                users.get(i).setName(actualizarUsuario.getName());
                users.get(i).setEmail(actualizarUsuario.getName());
                users.get(i).setAge(actualizarUsuario.getAge());
                users.get(i).setPhone(actualizarUsuario.getPhone());

                return ResponseEntity
                    .status(200)
                    .body(users.get(i));
            }
        }
        return ResponseEntity
            .status(404)
            .build();
    }

    // Busca el usuario por el ID, si lo encuentra lo elimina de la lista y responde con 200 OK, si no existe responde con 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarUsuario (@PathVariable String id){
        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getId().equals(id)){
                users.remove(i);
                return ResponseEntity
                    .status(200)
                    .build();
            }
        }
        return ResponseEntity
            .status(404)
            .build();
    }
}
