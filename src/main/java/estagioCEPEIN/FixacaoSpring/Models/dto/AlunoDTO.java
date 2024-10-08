package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Endereco;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlunoDTO (String nome, LocalDate dataNascimento, LocalDateTime dataRegistro, Endereco enderecoAluno) {
}
