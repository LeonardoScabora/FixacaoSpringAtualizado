package estagioCEPEIN.FixacaoSpring.Models.repositorio;


import estagioCEPEIN.FixacaoSpring.Models.entidades.Alunos;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDeMoradiaEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Alunos, Long> {
    Iterable<Alunos> findFirstBy();

    Iterable<Alunos> findByIdAndNomeContainingIgnoreCase(Long id, String nome);


    Iterable<Alunos> findByNomeContainingIgnoreCaseOrSobrenomeContainingIgnoreCase(String nome, String sobrenome);

    List<Alunos> findBySobrenomeIn(List<String> sobrenome);
}
