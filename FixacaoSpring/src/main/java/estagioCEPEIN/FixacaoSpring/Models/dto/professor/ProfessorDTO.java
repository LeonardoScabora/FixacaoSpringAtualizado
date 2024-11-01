package estagioCEPEIN.FixacaoSpring.Models.dto.professor;

import estagioCEPEIN.FixacaoSpring.Models.dto.disciplina.DisciplinasDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.turma.TurmasDTO;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProfessorDTO(String nome, String sobrenome, LocalDate dataNascimento, BigDecimal salario, TipoCargoEnum cargo, List<TurmasDTO> turmasList, List<DisciplinasDTO> disciplinasList) {
}
