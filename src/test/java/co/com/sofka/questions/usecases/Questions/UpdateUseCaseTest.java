package co.com.sofka.questions.usecases.Questions;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.mockito.Mockito.*;

@SpringBootTest
class UpdateUseCaseTest {

    @SpyBean
    private UpdateUseCase updateUseCase;

    @MockBean
    private QuestionRepository repository;

    @Test
    void updateQuestionTest() {

        var questionDTO = new QuestionDTO("11",
                "xxxx",
                "What is java?",
                Type.OPEN,
                Category.SCIENCES,"hola");

        var question = new Question("11",
                "xxxx",
                "What is java?",
                Type.OPEN,
                Category.SCIENCES,"hola");


        when(repository.save(Mockito.any())).thenReturn(Mono.just(question));

        var result = updateUseCase.apply(questionDTO);

        Assertions.assertEquals(Objects.requireNonNull(result.block()), "11");

        Mockito.verify(repository,Mockito.times(1)).save(any());

    }

}