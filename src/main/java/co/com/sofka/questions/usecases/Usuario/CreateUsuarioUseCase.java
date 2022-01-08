package co.com.sofka.questions.usecases.Usuario;

import co.com.sofka.questions.model.UsuarioDTO;
import co.com.sofka.questions.repositories.UsuarioRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateUsuarioUseCase implements SaveUsuario{
    private final UsuarioRepository usuarioRepository;
    private final MapperUtils mapperUtils;

    public CreateUsuarioUseCase(MapperUtils mapperUtils, UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Mono<UsuarioDTO> apply(UsuarioDTO usuarioDTO) {
        return usuarioRepository.findUsuarioByUid(usuarioDTO.getUid())
                .switchIfEmpty(usuarioRepository.save(mapperUtils.mapperToUsuario(null).apply(usuarioDTO)))
                .map(mapperUtils.mapperToUsuarioDTO());
    }
}
