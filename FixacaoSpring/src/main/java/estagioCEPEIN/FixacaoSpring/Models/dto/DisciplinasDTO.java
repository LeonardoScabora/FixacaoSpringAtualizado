package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.entidades.DisciplinasID;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professores;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDoEstagioEnum;

public record DisciplinasDTO(DisciplinasID ids, String nome, String simbolo, TipoDoEstagioEnum estagio, Professores professorId) {
}
