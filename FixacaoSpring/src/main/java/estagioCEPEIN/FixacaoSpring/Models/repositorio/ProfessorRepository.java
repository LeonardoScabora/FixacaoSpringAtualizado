package estagioCEPEIN.FixacaoSpring.Models.repositorio;

import estagioCEPEIN.FixacaoSpring.Models.dto.ProfessorConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Alunos;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professores, Long> {

    ///Procura Professor Por Cargo
    Iterable<Professores> findByCargo(TipoCargoEnum cargo);

    ///Procura o Primeiro Professor
    Iterable<Professores> findFirstBy();

    ///PROCURAR PROFESSOR POR NOME E CARGO
    Iterable<Professores> findByNomeContainingIgnoreCaseAndCargo(String nome, TipoCargoEnum cargo);

    List<Professores> findBySalarioGreaterThanOrderBySalarioDesc(BigDecimal salario);

    Iterable<Professores> findByNomeContainingIgnoreCaseOrSobrenomeContainingIgnoreCase(String nome, String sobrenome);

    List<Professores> findBySalarioBetween(BigDecimal salarioMin, BigDecimal salarioMax);

    List<Professores> findByOrderBySalario();
}
