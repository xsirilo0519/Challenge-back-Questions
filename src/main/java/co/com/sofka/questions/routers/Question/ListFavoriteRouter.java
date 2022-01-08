package co.com.sofka.questions.routers.Question;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecases.Questions.ListFavoriteUseCase;
import co.com.sofka.questions.usecases.Questions.ListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ListFavoriteRouter {
    @Bean
    public RouterFunction<ServerResponse> getAllFavorite(ListFavoriteUseCase listFavoriteUseCase) {
        return route(GET("/getAllFavorite/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listFavoriteUseCase.apply( request.pathVariable("id")), QuestionDTO.class))
        );
    }
}
