package co.com.sofka.questions.usecases.Favorite;

import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class DeleteFavoriteUseCase implements Function<String, Mono<Void>> {
    private final FavoriteRepository favoriteRepository;

    public DeleteFavoriteUseCase(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }
    @Override
    public Mono<Void> apply(String id) {
        return favoriteRepository.deleteById(id);
    }
}
