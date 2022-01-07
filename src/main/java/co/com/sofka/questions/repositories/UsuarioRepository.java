package co.com.sofka.questions.repositories;

import co.com.sofka.questions.collections.Usuario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, String> {
    Mono<Usuario> findUsuarioByUid(String uid);
}
