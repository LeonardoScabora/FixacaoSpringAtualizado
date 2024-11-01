package estagioCEPEIN.FixacaoSpring.Models.dto.aluno;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Endereco;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlunoDTO (String nome, String sobrenome, LocalDate dataNascimento, LocalDateTime dataRegistro, Endereco endereco) {
}
