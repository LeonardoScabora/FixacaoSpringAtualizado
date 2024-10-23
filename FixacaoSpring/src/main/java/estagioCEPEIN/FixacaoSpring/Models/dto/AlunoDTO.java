package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Enderecos;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professores;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Turmas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AlunoDTO (String nome, String sobrenome, LocalDate dataNascimento, LocalDateTime dataRegistro, Enderecos endereco) {
}
