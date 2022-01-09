package co.com.sofka.questions.routers.Favorite;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecases.Favorite.GetMyFavoritesUseCase;
import co.com.sofka.questions.usecases.Questions.OwnerListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetFavoriteRouter {
    @Bean
    public RouterFunction<ServerResponse> getFavorite(GetMyFavoritesUseCase getMyFavoritesUseCase) {
        return route(
                GET("/getFavorites/{userId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getMyFavoritesUseCase.apply(request.pathVariable("userId")),
                                QuestionDTO.class
                        ))
        );
    }
}
