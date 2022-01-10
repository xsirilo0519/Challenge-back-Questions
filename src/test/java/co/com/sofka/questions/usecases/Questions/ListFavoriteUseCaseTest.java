package co.com.sofka.questions.usecases.Questions;

import co.com.sofka.questions.collections.Favorite;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
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
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class ListFavoriteUseCaseTest {
    @MockBean
    private QuestionRepository questionRepository;
    @MockBean
    private FavoriteRepository favoriteRepository;
    @SpyBean
    private ListFavoriteUseCase listFavoriteUseCase;

    @Test
    public void ListFavoriteUseCaseTests(){

        var questionDTO = new QuestionDTO("1"
                ,"1",
                "¿What is java?",
                Type.OPEN,
                Category.TECHNOLOGY_AND_COMPUTER,"hola");
        var questionDTO2 = new QuestionDTO("1"
                ,"1",
                "¿What is java?",
                Type.OPEN,
                Category.TECHNOLOGY_AND_COMPUTER,"hola");

        var question= new Question();
        question.setId("3");
        question.setQuestion("¿What is java?");
        question.setUserId("1");
        question.setType(Type.OPEN);
        question.setCategory(Category.TECHNOLOGY_AND_COMPUTER);
        var question2= new Question();
        question.setId("2");
        question.setQuestion("¿What is java?");
        question.setUserId("1");
        question.setType(Type.OPEN);
        question.setCategory(Category.TECHNOLOGY_AND_COMPUTER);

        var favorite = new Favorite("123", "1", "1");

        Mockito.when(questionRepository.findAll()).thenReturn(Flux.just(question,question2));
        Mockito.when(favoriteRepository.findFirstByUidAndQuestionid(anyString(),anyString())).thenReturn(Mono.just(favorite));


        var respuesta = listFavoriteUseCase.apply("1").blockFirst();
        Assertions.assertEquals(respuesta.getFavorite().getId(), favorite.getId());
        Assertions.assertEquals(respuesta.getId(), question.getId());
        Assertions.assertEquals(respuesta.getUserId(), question.getUserId());
        Assertions.assertEquals(respuesta.getType(), question.getType());
        Assertions.assertEquals(respuesta.getCategory(), question.getCategory());

        Mockito.verify(questionRepository,Mockito.times(1)).findAll();
        Mockito.verify(favoriteRepository,Mockito.times(1)).findFirstByUidAndQuestionid(anyString(),anyString());

    }

}