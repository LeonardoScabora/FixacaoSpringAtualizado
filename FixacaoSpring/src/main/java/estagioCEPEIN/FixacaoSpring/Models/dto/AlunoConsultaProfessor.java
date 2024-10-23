package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Enderecos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlunoConsultaProfessor(Long id, String nome, String sobrenome, LocalDate dataNascimento, LocalDateTime dataRegistro, Enderecos endereco) {
}
