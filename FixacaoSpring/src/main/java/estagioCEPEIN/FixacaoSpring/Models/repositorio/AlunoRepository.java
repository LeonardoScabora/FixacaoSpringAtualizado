package estagioCEPEIN.FixacaoSpring.Models.repositorio;


import estagioCEPEIN.FixacaoSpring.Models.dto.aluno.AlunoConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<AlunoConsultaDTO> findFirstBy();

    List<Aluno> findByIdAndNomeContainingIgnoreCase(Long id, String nome);

    List<Aluno> findByNomeContainingIgnoreCaseOrSobrenomeContainingIgnoreCase(String nome, String sobrenome);

    List<Aluno> findBySobrenomeIn(List<String> sobrenome);

}
