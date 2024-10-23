package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.entidades.DisciplinasID;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDoEstagioEnum;

import java.time.LocalDateTime;

public record DisciplinasConsultaDTO (DisciplinasID ids, String nome, String simbolo, TipoDoEstagioEnum estagio, LocalDateTime dataRegistro){
}
