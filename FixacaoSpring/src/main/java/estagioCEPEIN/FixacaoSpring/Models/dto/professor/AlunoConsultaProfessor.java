package estagioCEPEIN.FixacaoSpring.Models.dto.professor;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Endereco;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlunoConsultaProfessor(Long id, String nome, String sobrenome, LocalDate dataNascimento, LocalDateTime dataRegistro, Endereco endereco) {
}
