package co.com.sofka.questions.usecases.Favorite;

import co.com.sofka.questions.collections.Favorite;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.usecases.Questions.GetUseCase;
import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GetMyFavoritesUseCaseTest {

    @MockBean
    private FavoriteRepository favoriteRepository;

    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    private GetMyFavoritesUseCase getMyFavoritesUseCase;

    @Test
    public void getQuestionFavorite(){

        var questionDTO = new QuestionDTO("1"
                ,"1",
                "¿What is java?",
                Type.OPEN,
                Category.TECHNOLOGY_AND_COMPUTER,"hola");


        var favoriteCollection = new Favorite();
        favoriteCollection.setId("123");
        favoriteCollection.setUid("1");
        favoriteCollection.setQuestionid("1");

        var favoriteCollection2 = new Favorite();
        favoriteCollection.setId("123");
        favoriteCollection.setUid("1");
        favoriteCollection.setQuestionid("1");


        var favorite=Flux.just(favoriteCollection);

        var question= new Question();
        question.setId("1");
        question.setQuestion("¿What is java?");
        question.setUserId("1");
        question.setType(Type.OPEN);
        question.setCategory(Category.TECHNOLOGY_AND_COMPUTER);

        Mockito.when(favoriteRepository.findFavoriteByUid("1")).thenReturn(favorite);
        Mockito.when(questionRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(question));

        var respuesta = getMyFavoritesUseCase.apply("1").blockFirst();
        Assertions.assertEquals(respuesta.getQuestion(), question.getQuestion());
        Assertions.assertEquals(respuesta.getId(), question.getId());
        Assertions.assertEquals(respuesta.getUserId(), question.getUserId());
        Assertions.assertEquals(respuesta.getType(), question.getType());
        Assertions.assertEquals(respuesta.getCategory(), question.getCategory());

        Mockito.verify(questionRepository,Mockito.times(1)).findById("1");
        Mockito.verify(favoriteRepository,Mockito.times(1)).findFavoriteByUid("1");

    }

}