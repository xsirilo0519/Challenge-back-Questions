package co.com.sofka.questions.routers.Usuario;

import co.com.sofka.questions.model.UsuarioDTO;
import co.com.sofka.questions.usecases.Usuario.CreateUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

@Configuration
public class CreateUsuarioRouter {
    @Bean
    public RouterFunction<ServerResponse> createUsuario(CreateUsuarioUseCase createUsuarioUseCase){
        Function<UsuarioDTO, Mono<ServerResponse>> executor = usuarioDTO -> createUsuarioUseCase.apply(usuarioDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/createUsuario").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UsuarioDTO.class).flatMap(executor)
        );
    }
}
