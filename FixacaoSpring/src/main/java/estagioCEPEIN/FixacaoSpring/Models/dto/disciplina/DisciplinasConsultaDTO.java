package estagioCEPEIN.FixacaoSpring.Models.dto.disciplina;

import estagioCEPEIN.FixacaoSpring.Models.entidades.DisciplinaID;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDoEstagioEnum;

import java.time.LocalDateTime;

public record DisciplinasConsultaDTO (DisciplinaID ids, String nome, String simbolo, TipoDoEstagioEnum estagio, LocalDateTime dataRegistro){
}
