package co.com.sofka.questions.routers.Favorite;

import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.model.UsuarioDTO;
import co.com.sofka.questions.usecases.Favorite.CrearFavoriteUseCase;
import co.com.sofka.questions.usecases.Usuario.CreateUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CrearFavoriteRouter {
    @Bean
    public RouterFunction<ServerResponse> createFavorite(CrearFavoriteUseCase crearFavoriteUseCase){

        Function<FavoriteDTO, Mono<ServerResponse>> executor = favoriteDTO -> crearFavoriteUseCase.apply(favoriteDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                POST("/createFavorite").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(FavoriteDTO.class)
                        .flatMap(executor)
        );
    }
}