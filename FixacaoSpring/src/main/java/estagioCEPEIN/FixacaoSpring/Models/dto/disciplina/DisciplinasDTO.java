package estagioCEPEIN.FixacaoSpring.Models.dto.disciplina;

import estagioCEPEIN.FixacaoSpring.Models.entidades.DisciplinaID;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professor;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDoEstagioEnum;

public record DisciplinasDTO(DisciplinaID ids, String nome, String simbolo, TipoDoEstagioEnum estagio, Professor professorId) {
}
