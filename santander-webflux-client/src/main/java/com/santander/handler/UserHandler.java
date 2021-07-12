package com.santander.handler;

import com.santander.model.User;
import com.santander.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

@Component
public class UserHandler {

    @Autowired
    private UserService userService;

    public Mono<ServerResponse> listar(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(userService.findAll(), User.class);
    }

    public Mono<ServerResponse> detalle(ServerRequest request) {
        String id = request.pathVariable("id");

        return userService.findById(id)
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .syncBody(user)
                ).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);

        return userMono.flatMap(user ->
        {
            if (user.getFechaCreacion() == null) {
                user.setFechaCreacion(LocalDateTime.now());
            }
            return userService.save(user);
        }).flatMap(u -> ServerResponse.created(URI.create("/api/client/".concat(u.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(u));
    }

    public Mono<ServerResponse> editar(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        String id = request.pathVariable("id");

        return userMono.flatMap(user -> ServerResponse.created(URI.create("/api/client/".concat(id)))
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.update(user, id), User.class));
    }

    public Mono<ServerResponse> eliminar(ServerRequest request) {
        String id = request.pathVariable("id");

        return userService.delete(id).flatMap(unused -> ServerResponse.noContent().build());
    }
}