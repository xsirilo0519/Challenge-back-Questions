package co.com.sofka.questions.usecases.Answer;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class DeleteAnswerUseCaseTest {
    @MockBean
    private AnswerRepository answerRepository;

    @SpyBean
    private DeleteAnswerUseCase deleteAnswerUseCase;

    @Test
    void deleteAnswerUseCaseTest(){

        var answerDTO = new AnswerDTO("1","12345", "123", "hola?");
        var answer = new Answer("1", "123", "12345", "hola?", 1);

        Mockito.when(answerRepository.deleteById("1")).thenReturn(Mono.empty());
        var result = deleteAnswerUseCase.apply("1").block();
        Assertions.assertNull(result);

        Mockito.verify(answerRepository,Mockito.times(1)).deleteById("1");

    }
}