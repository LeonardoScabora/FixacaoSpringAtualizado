package estagioCEPEIN.FixacaoSpring.Models.repositorio;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplina;
import estagioCEPEIN.FixacaoSpring.Models.entidades.DisciplinaID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, DisciplinaID> {
}
