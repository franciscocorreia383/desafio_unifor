package desafio.unifor.repository;

import desafio.unifor.entity.CursoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CursoRepository implements PanacheRepository<CursoEntity> {
}
