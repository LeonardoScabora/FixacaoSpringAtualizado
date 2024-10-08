package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Turmas;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProfessorDTO(String nome, LocalDate dataNascimento, BigDecimal salario, TipoCargoEnum cargo, List<TurmasDTO> turmasList, List<DisciplinasDTO> disciplinasList) {
}
