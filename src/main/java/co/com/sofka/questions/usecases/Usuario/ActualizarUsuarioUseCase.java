package co.com.sofka.questions.usecases.Usuario;

import co.com.sofka.questions.model.UsuarioDTO;
import co.com.sofka.questions.repositories.UsuarioRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class ActualizarUsuarioUseCase implements SaveUsuario{
    private final UsuarioRepository usuarioRepository;
    private final MapperUtils mapperUtils;

    public ActualizarUsuarioUseCase(MapperUtils mapperUtils, UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Mono<UsuarioDTO> apply(UsuarioDTO usuarioDTO) {
        return usuarioRepository
                .save(mapperUtils.mapperToUsuario(usuarioDTO.getId()).apply(usuarioDTO))
                .map(mapperUtils.mapperToUsuarioDTO());
    }
}