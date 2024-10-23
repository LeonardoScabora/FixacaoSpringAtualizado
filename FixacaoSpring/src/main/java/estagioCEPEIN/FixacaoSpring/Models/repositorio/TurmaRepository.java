package estagioCEPEIN.FixacaoSpring.Models.repositorio;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Turmas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turmas, Long> {

    List<Turmas> findBySerieIn(List<String> serie);
}
