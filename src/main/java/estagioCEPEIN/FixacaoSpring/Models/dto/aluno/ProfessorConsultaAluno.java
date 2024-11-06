package estagioCEPEIN.FixacaoSpring.Models.dto.aluno;

import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProfessorConsultaAluno(Long id, String nome, String sobrenome, LocalDate dataNascimento, BigDecimal salario, TipoCargoEnum cargo) {

}
