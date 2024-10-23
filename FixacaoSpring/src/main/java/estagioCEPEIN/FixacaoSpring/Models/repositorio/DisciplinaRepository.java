package estagioCEPEIN.FixacaoSpring.Models.repositorio;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplinas;
import estagioCEPEIN.FixacaoSpring.Models.entidades.DisciplinasID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplinas, DisciplinasID> {
}
