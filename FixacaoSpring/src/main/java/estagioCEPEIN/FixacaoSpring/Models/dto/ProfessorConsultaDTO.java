package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Alunos;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProfessorConsultaDTO(Long id, String nome, String sobrenome, LocalDate dataNascimento, BigDecimal salario, TipoCargoEnum cargo, LocalDateTime dataRegistro, List<TurmasConsultaDTO> turmasList, List<DisciplinasConsultaDTO> disciplinasList, List<AlunoConsultaProfessor> alunos) {

}
