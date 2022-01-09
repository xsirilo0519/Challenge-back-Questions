package co.com.sofka.questions.usecases.Favorite;

import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class GetMyFavoritesUseCase implements Function<String, Flux<QuestionDTO>> {
    private final FavoriteRepository favoriteRepository;
    private final MapperUtils mapperUtils;
    private final QuestionRepository questionRepository;


    public GetMyFavoritesUseCase(MapperUtils mapperUtils, FavoriteRepository favoriteRepository,QuestionRepository questionRepository) {
        this.favoriteRepository = favoriteRepository;
        this.mapperUtils = mapperUtils;
        this.questionRepository=questionRepository;
    }


    @Override
    public Flux<QuestionDTO> apply(String uid) {
        return favoriteRepository.findFavoriteByUid(uid)
                .flatMap(favorite -> questionRepository.findById(favorite.getQuestionid())
                        .map(mapperUtils.mapEntityToQuestion()));
    }
}
