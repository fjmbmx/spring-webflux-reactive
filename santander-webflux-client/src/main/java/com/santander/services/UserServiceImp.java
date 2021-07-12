package com.santander.services;

import com.santander.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Flux<User> findAll() {
        return webClient.build().get()
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(User.class);
    }

    @Override
    public Mono<User> findById(String id) {
        return webClient.build().get()
                .uri("/{id}", Collections.singletonMap("id", id))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class);
    }

    @Override
    public Mono<User> save(User user) {
        return webClient.build().post()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                // .syncBody(user)
                .body(BodyInserters.fromObject(user))
                .retrieve()
                .bodyToMono(User.class);
    }

    @Override
    public Mono<User> update(User user, String id) {
        return webClient.build().put()
                .uri("/{id}", Collections.singletonMap("id", id))
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                // .syncBody(user)
                .body(BodyInserters.fromObject(user))
                .retrieve()
                .bodyToMono(User.class);
    }

    @Override
    public Mono<Void> delete(String id) {
        return webClient.build().delete()
                .uri("/{id}", Collections.singletonMap("id", id))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class)
                .then();
    }
}
