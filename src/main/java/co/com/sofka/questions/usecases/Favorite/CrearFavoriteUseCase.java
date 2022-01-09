package co.com.sofka.questions.usecases.Favorite;

import co.com.sofka.questions.collections.Favorite;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.usecases.Questions.SaveQuestion;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CrearFavoriteUseCase implements SaveFavorite {
    private final FavoriteRepository favoriteRepository;
    private final MapperUtils mapperUtils;

    public CrearFavoriteUseCase(MapperUtils mapperUtils, FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<FavoriteDTO> apply(FavoriteDTO favoriteDTO) {
        return favoriteRepository
                .save(mapperUtils.mapperToFavorite().apply(favoriteDTO))
                .map(mapperUtils.mapperToFavoriteDTO());
    }

}
