package desafio.unifor.repository;

import desafio.unifor.entity.AlunoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlunoRepository implements PanacheRepository<AlunoEntity> {
}
