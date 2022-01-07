package co.com.sofka.questions.routers.Usuario;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UsuarioDTO;
import co.com.sofka.questions.usecases.Questions.FindAllByCategoryUseCase;
import co.com.sofka.questions.usecases.Usuario.GetUsuarioByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetUsuarioByIdRouter {
    @Bean
    public RouterFunction<ServerResponse> GetUsuarioById(GetUsuarioByIdUseCase getUsuarioByIdUseCase) {
        return route(
                GET("/getUsuario/{uid}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getUsuarioByIdUseCase.apply(request.pathVariable("uid")), UsuarioDTO.class))
        );
    }
    }
