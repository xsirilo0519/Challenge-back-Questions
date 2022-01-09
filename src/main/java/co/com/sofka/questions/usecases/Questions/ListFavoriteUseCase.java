package co.com.sofka.questions.usecases.Questions;

import co.com.sofka.questions.collections.Favorite;
import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class ListFavoriteUseCase implements Function<String, Flux<QuestionDTO>> {
    private final QuestionRepository questionRepository;
    private final MapperUtils mapperUtils;
    private final FavoriteRepository favoriteRepository;

    public ListFavoriteUseCase(MapperUtils mapperUtils, QuestionRepository questionRepository, FavoriteRepository favoriteRepository) {
        this.questionRepository = questionRepository;
        this.mapperUtils = mapperUtils;
        this.favoriteRepository=favoriteRepository;
    }

    @Override
    public Flux<QuestionDTO> apply(String id) {
        return questionRepository.findAll().map(mapperUtils.mapEntityToQuestion())
                .flatMap(mapQuestionAggregate(id));
    }
    private Function<QuestionDTO, Mono<QuestionDTO>> mapQuestionAggregate(String id) {

        return questionDTO ->

                Mono.just(questionDTO).zipWith(
                        favoriteRepository.findFirstByUidAndQuestionid(id,questionDTO.getId())
                                .switchIfEmpty(Mono.just(new Favorite()))
                                .map(mapperUtils.mapperToFavoriteDTO()),
                        (question, favoriteDTO) -> {

                            if(favoriteDTO.getId()!=null) {
                                question.setFavorite(favoriteDTO);
                            }

                            return question;
                        }
                );
    }




}
