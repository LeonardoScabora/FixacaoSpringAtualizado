package estagioCEPEIN.FixacaoSpring.Models.repositorio;

import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    ///Procura Professor Por Cargo
    Iterable<Professor> findByCargo(TipoCargoEnum cargo);

    ///Procura o Primeiro Professor
    Iterable<Professor> findFirstBy();

    ///PROCURAR PROFESSOR POR NOME E CARGO
    Iterable<Professor> findByNomeContainingIgnoreCaseAndCargo(String nome, TipoCargoEnum cargo);

}
