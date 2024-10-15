package estagioCEPEIN.FixacaoSpring.Models.repositorio;

import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professores, Long> {

    ///Procura Professor Por Cargo
    Iterable<Professores> findByCargo(TipoCargoEnum cargo);

    ///Procura o Primeiro Professor
    Iterable<Professores> findFirstBy();

    ///PROCURAR PROFESSOR POR NOME E CARGO
    Iterable<Professores> findByNomeContainingIgnoreCaseAndCargo(String nome, TipoCargoEnum cargo);

}
