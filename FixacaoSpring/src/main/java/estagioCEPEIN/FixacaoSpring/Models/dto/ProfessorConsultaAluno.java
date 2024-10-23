package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProfessorConsultaAluno(Long id, String nome, String sobrenome, LocalDate dataNascimento, BigDecimal salario, TipoCargoEnum cargo) {

}
