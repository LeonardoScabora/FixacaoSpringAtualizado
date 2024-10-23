package estagioCEPEIN.FixacaoSpring.Models.dto;

import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDeMoradiaEnum;

public record EnderecoDTO(String rua, String bairro, TipoDeMoradiaEnum moradia) {
}
