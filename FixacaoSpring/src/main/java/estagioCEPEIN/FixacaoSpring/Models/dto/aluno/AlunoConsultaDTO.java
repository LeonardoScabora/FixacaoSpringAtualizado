package estagioCEPEIN.FixacaoSpring.Models.dto.aluno;

import estagioCEPEIN.FixacaoSpring.Models.entidades.Endereco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AlunoConsultaDTO(Long id, String nome, String sobrenome, LocalDate dataNascimento, LocalDateTime dataRegistro, Endereco endereco, List<ProfessorConsultaAluno> professores) {
}
