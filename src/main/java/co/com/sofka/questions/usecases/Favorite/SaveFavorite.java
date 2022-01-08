package co.com.sofka.questions.usecases.Favorite;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.model.QuestionDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveFavorite {
    Mono<String> apply(@Valid FavoriteDTO favoriteDTO);

}
