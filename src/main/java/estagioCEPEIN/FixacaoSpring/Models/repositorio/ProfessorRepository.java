package estagioCEPEIN.FixacaoSpring.Models.repositorio;

import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    ///Procura Professor Por Cargo
    Iterable<Professor> findByCargo(TipoCargoEnum cargo);

    ///Procura o Primeiro Professor
    List<Professor> findFirstBy();

    ///PROCURAR PROFESSOR POR NOME E CARGO
    List<Professor> findByNomeContainingIgnoreCaseAndCargo(String nome, TipoCargoEnum cargo);

    List<Professor> findBySalarioGreaterThanOrderBySalarioDesc(BigDecimal salario);

    List<Professor> findByNomeContainingIgnoreCaseOrSobrenomeContainingIgnoreCase(String nome, String sobrenome);

    List<Professor> findBySalarioBetween(BigDecimal salarioMin, BigDecimal salarioMax);

    List<Professor> findByOrderBySalario();

    List<Professor> findBySalarioLessThan(BigDecimal salarioMax);

    List<Professor> findBySalarioLessThanEqual(BigDecimal salarioMax);

    List<Professor> findBySalarioGreaterThan(BigDecimal salarioMin);

    List<Professor> findBySalarioGreaterThanEqual(BigDecimal salarioMin);
}
