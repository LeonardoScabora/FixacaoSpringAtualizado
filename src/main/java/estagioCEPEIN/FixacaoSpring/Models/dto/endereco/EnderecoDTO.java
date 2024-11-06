package estagioCEPEIN.FixacaoSpring.Models.dto.endereco;

import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDeMoradiaEnum;

import java.time.LocalDateTime;

public record EnderecoDTO(String rua, String bairro, TipoDeMoradiaEnum moradia, LocalDateTime dataRegistro) {
}
