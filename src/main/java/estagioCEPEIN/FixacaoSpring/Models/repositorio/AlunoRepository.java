package estagioCEPEIN.FixacaoSpring.Models.repositorio;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Alunos;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface AlunoRepository extends JpaRepository<Alunos, Long> {

}
