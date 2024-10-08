package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProfessorConsultaDTO(Long id, String nome, LocalDate dataNascimento, BigDecimal salario, TipoCargoEnum cargo, List<TurmasConsultaDTO> turmasList, List<DisciplinasConsultaDTO> disciplinasList) {

}
