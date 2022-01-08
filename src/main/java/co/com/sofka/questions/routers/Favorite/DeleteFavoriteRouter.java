package co.com.sofka.questions.routers.Favorite;

import co.com.sofka.questions.usecases.Answer.DeleteAnswerUseCase;
import co.com.sofka.questions.usecases.Favorite.DeleteFavoriteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteFavoriteRouter {
         @Bean
        public RouterFunction<ServerResponse> deleteFavorites(DeleteFavoriteUseCase deleteFavoriteUseCase) {
            return route(
                    DELETE("/deleteFavorite/{id}").and(accept(MediaType.APPLICATION_JSON)),
                    request -> ServerResponse.accepted()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromPublisher(deleteFavoriteUseCase.apply(request.pathVariable("id")), Void.class))
            );

    }
}
