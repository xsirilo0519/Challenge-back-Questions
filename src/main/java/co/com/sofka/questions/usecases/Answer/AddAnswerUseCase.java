package co.com.sofka.questions.usecases.Answer;

import co.com.sofka.questions.Service.SendMailService;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.usecases.Questions.GetUseCase;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;



@Service
@Validated
public class AddAnswerUseCase implements SaveAnswer {


    private final SendMailService sendMailService;
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;
    private final GetUseCase getUseCase;

    public AddAnswerUseCase(SendMailService sendMailService, MapperUtils mapperUtils, GetUseCase getUseCase, AnswerRepository answerRepository) {
        this.sendMailService=sendMailService;
        this.answerRepository = answerRepository;
        this.getUseCase = getUseCase;
        this.mapperUtils = mapperUtils;
    }

    public Mono<QuestionDTO> apply(AnswerDTO answerDTO) {
        Objects.requireNonNull(answerDTO.getQuestionId(), "Id of the answer is required");
        return getUseCase.apply(answerDTO.getQuestionId()).flatMap(question ->
                answerRepository.save(mapperUtils.mapperToAnswer().apply(answerDTO))
                        .map(answer -> {
                            question.getAnswers().add(mapperUtils.mapEntityToAnswer().apply(answer));
                            sendMailService.sendMail(
                                    question.getEmail(),
                                    "Han respondido a tu pregunta: " + question.getQuestion(),
                                    "Respuesta: \n" + answer.getAnswer());
                            return question;
                        })
        );
    }

}
