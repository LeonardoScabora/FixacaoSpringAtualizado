package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Enderecos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AlunoConsultaDTO(Long id, String nome, String sobrenome, LocalDate dataNascimento, LocalDateTime dataRegistro, Enderecos endereco, List<ProfessorConsultaAluno> professores) {
}
