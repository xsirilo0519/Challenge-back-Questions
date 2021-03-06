package co.com.sofka.questions.usecases.Answer;

import co.com.sofka.questions.Service.SendMailService;
import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.usecases.Questions.GetUseCase;
import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class AddAnswerUseCaseTest {
    @SpyBean
    AddAnswerUseCase addAnswerUseCase;

    @MockBean
    GetUseCase getUseCase;

    @MockBean
    AnswerRepository answerRepository;

    @MockBean
    SendMailService sendMailService;



    @Test
    void addAnswerTest() {
        var question = new QuestionDTO("11",
                "xxxx",
                "What is java?",
                Type.OPEN,
                Category.SCIENCES,"hola");

        var answerDTO = new AnswerDTO(null,"1",
                "xxxx",
                "Es un lenguaje de programación y otras palabras");

        var answer = new Answer("11",
                "xxxx",
                "1",
                "Es un lenguaje de programación y otras palabras", 1);

        when(answerRepository.save(any())).thenReturn(Mono.just(answer));
        when(getUseCase.apply(any())).thenReturn(Mono.just(question));
        when(sendMailService.sendMail(anyString(),anyString(),anyString())).thenReturn(Mono.just("¡Send!"));



        var questionDTO = addAnswerUseCase.apply(answerDTO);
        var resultQuestionDTO = questionDTO.block();

        assert resultQuestionDTO != null;
        Assertions.assertEquals(resultQuestionDTO.getId(),question.getId());
        Assertions.assertEquals(resultQuestionDTO.getQuestion(),question.getQuestion());
        Assertions.assertEquals(resultQuestionDTO.getAnswers().get(0).getQuestionId(),answerDTO.getQuestionId());
        Assertions.assertEquals(resultQuestionDTO.getAnswers().get(0).getAnswer(),answerDTO.getAnswer());

        Mockito.verify(answerRepository,Mockito.times(1)).save(any());
        Mockito.verify(sendMailService,Mockito.times(1)).sendMail( "hola",    "Han respondido a tu pregunta: What is java?",  "Respuesta: \nEs un lenguaje de programación y otras palabras");

    }
}