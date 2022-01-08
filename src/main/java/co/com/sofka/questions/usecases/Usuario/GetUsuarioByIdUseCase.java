package co.com.sofka.questions.usecases.Usuario;

import co.com.sofka.questions.model.UsuarioDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.repositories.UsuarioRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetUsuarioByIdUseCase implements Function<String, Mono<UsuarioDTO>> {
    private final UsuarioRepository usuarioRepository;
    private final MapperUtils mapperUtils;

    public GetUsuarioByIdUseCase(MapperUtils mapperUtils, UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<UsuarioDTO> apply(String uid) {
        return usuarioRepository.findUsuarioByUid(uid).map(mapperUtils.mapperToUsuarioDTO());
    }
}
