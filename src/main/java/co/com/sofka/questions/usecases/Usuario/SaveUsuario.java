package co.com.sofka.questions.usecases.Usuario;

import co.com.sofka.questions.model.UsuarioDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveUsuario {
    Mono<UsuarioDTO> apply(@Valid UsuarioDTO usuarioDTO);
}