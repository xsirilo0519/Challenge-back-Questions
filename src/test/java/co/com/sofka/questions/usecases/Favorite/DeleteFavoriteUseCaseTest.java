package co.com.sofka.questions.usecases.Favorite;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.usecases.Questions.DeleteUseCase;
import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteFavoriteUseCaseTest {
    @MockBean
    private FavoriteRepository favoriteRepository;

    @SpyBean
    DeleteFavoriteUseCase deleteFavoriteUseCase;

    @Test
    void deleteFavoriteUseCase(){


        Mockito.when(favoriteRepository.deleteById("xxxx")).thenReturn(Mono.empty());

        var result = deleteFavoriteUseCase.apply("xxxx").block();
        Assertions.assertNull(result);

        Mockito.verify(favoriteRepository,Mockito.times(1)).deleteById("xxxx");
    }
}