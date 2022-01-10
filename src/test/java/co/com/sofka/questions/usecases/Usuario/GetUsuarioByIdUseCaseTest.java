package co.com.sofka.questions.usecases.Usuario;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.collections.Usuario;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UsuarioDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.repositories.UsuarioRepository;
import co.com.sofka.questions.usecases.Questions.GetUseCase;
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
class GetUsuarioByIdUseCaseTest {
    @SpyBean
    private GetUsuarioByIdUseCase getUsuarioByIdUseCase;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void getQuestion(){

        var usuarioDTO = new UsuarioDTO("1", "123", "sebas", "lopez", "hola", "hola@example.com");

        var usuario = new Usuario("1", "123", "sebas", "lopez", "hola", "hola@example.com" );

        Mockito.when(usuarioRepository.findUsuarioByUid(Mockito.any(String.class))).thenReturn(Mono.just(usuario));

        var respuesta = getUsuarioByIdUseCase.apply("1");
        Assertions.assertEquals(respuesta.block().getUid(), usuario.getUid());
        Assertions.assertEquals(respuesta.block().getId(), usuario.getId());
        Assertions.assertEquals(respuesta.block().getApellido(), usuario.getApellido());
        Assertions.assertEquals(respuesta.block().getNombre(), usuario.getNombre());
        Assertions.assertEquals(respuesta.block().getEmail(), usuario.getEmail());

        Mockito.verify(usuarioRepository,Mockito.times(1)).findUsuarioByUid("1");
    }
}