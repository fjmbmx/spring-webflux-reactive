package com.santander;

import com.santander.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> rutas (UserHandler userHandler){
    return RouterFunctions.route(RequestPredicates.GET("/api/client"),userHandler::listar)
            .andRoute(RequestPredicates.GET("/api/client/{id}"),userHandler::detalle)
            .andRoute(RequestPredicates.POST("/api/client"),userHandler::save)
            .andRoute(RequestPredicates.PUT("/api/client/{id}"),userHandler::editar)
            .andRoute(RequestPredicates.DELETE("/api/client/{id}"),userHandler::eliminar);

    }
}