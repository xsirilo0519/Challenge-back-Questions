package co.com.sofka.questions.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class SendMailService {


    private final JavaMailSender javaMailSender;


    private final String FROM = "sendemail0519@gmail.com";

    public SendMailService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    public Mono<String> sendMail(String to, String subject, String body){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(FROM);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

         javaMailSender.send(simpleMailMessage);

        return Mono.just("¡Send!");
    }
}