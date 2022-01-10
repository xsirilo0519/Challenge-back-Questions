package co.com.sofka.questions.usecases.Favorite;

import co.com.sofka.questions.collections.Favorite;
import co.com.sofka.questions.collections.Usuario;
import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.model.UsuarioDTO;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.repositories.UsuarioRepository;
import co.com.sofka.questions.usecases.Usuario.CreateUsuarioUseCase;
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
import static org.mockito.Mockito.when;

@SpringBootTest
class CrearFavoriteUseCaseTest {
    @SpyBean
    private CrearFavoriteUseCase crearFavoriteUseCase;

    @MockBean
    private FavoriteRepository favoriteRepository;

    @Test
    void crearFavorite(){

        var favoritoDTO = new FavoriteDTO("1", "123", "sebas");

        var favorite = new Favorite("1", "123", "sebas");

        when(favoriteRepository.save(Mockito.any())).thenReturn(Mono.just(favorite));

        var result = crearFavoriteUseCase.apply(favoritoDTO);

        Assertions.assertEquals(Objects.requireNonNull(result.block().getUid()),favoritoDTO.getUid());
        Assertions.assertEquals(Objects.requireNonNull(result.block().getId()),favoritoDTO.getId());
        Assertions.assertEquals(Objects.requireNonNull(result.block().getQuestionid()),favoritoDTO.getQuestionid());


        Mockito.verify(favoriteRepository,Mockito.times(1)).save(any());

    }
}