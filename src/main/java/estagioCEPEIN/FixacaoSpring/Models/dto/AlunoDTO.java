package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Enderecos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlunoDTO (String nome, LocalDate dataNascimento, LocalDateTime dataRegistro, Enderecos endereco) {
}
