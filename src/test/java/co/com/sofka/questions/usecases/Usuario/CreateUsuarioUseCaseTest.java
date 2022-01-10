package co.com.sofka.questions.usecases.Usuario;

import co.com.sofka.questions.collections.Usuario;
import co.com.sofka.questions.model.UsuarioDTO;
import co.com.sofka.questions.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreateUsuarioUseCaseTest {
    @SpyBean
    private CreateUsuarioUseCase createUsuarioUseCase;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    void createUsuariotest(){

        var usuarioDTO = new UsuarioDTO("1", "123", "sebas", "lopez", "hola", "hola@example.com");

        var usuario = new Usuario("1", "123", "sebas", "lopez", "hola", "hola@example.com" );

        when(usuarioRepository.findUsuarioByUid("123")).thenReturn(Mono.just(usuario));

        when(usuarioRepository.save(Mockito.any())).thenReturn(Mono.just(usuario));

        var result = createUsuarioUseCase.apply(usuarioDTO);

        Assertions.assertEquals(Objects.requireNonNull(result.block()),usuarioDTO);

        Mockito.verify(usuarioRepository,Mockito.times(1)).findUsuarioByUid(any());
        Mockito.verify(usuarioRepository,Mockito.times(1)).save(any());

    }

}