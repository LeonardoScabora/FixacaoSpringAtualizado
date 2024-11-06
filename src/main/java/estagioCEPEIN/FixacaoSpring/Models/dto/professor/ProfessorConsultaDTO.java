package estagioCEPEIN.FixacaoSpring.Models.dto.professor;

import estagioCEPEIN.FixacaoSpring.Models.dto.turma.TurmasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.disciplina.DisciplinasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProfessorConsultaDTO(Long id, String nome, String sobrenome, LocalDate dataNascimento, BigDecimal salario, TipoCargoEnum cargo, LocalDateTime dataRegistro, List<TurmasConsultaDTO> turmasList, List<DisciplinasConsultaDTO> disciplinasList, List<AlunoConsultaProfessor> alunos) {

}
