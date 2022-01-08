package co.com.sofka.questions.usecases.Questions;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.MapperUtils;
import co.com.sofka.questions.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


import static org.mockito.Mockito.*;

@SpringBootTest
class ListUseCaseTest {

    QuestionRepository repository;
    ListUseCase listUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        repository = mock(QuestionRepository.class);
        listUseCase = new ListUseCase( mapperUtils,repository);
    }

    @Test
    void listQuestionsTest(){

        var question = new Question("11",
                "xxxx",
                "What is java?",
                Type.OPEN,
                Category.SCIENCES,"hola",false);

        when(repository.findAll()).thenReturn(Flux.just(question));

        StepVerifier.create(listUseCase.get())
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx");
                    assert questionDTO.getCategory().equals(Category.SCIENCES);
                    assert questionDTO.getQuestion().equals("What is java?");
                    assert questionDTO.getType().equals(Type.OPEN);
                    return true;
                })
                .verifyComplete();

        verify(repository).findAll();
    }
}