package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.EnderecoDTO;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDeMoradiaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Endereco")
public class Enderecos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_endereco")
    private Long id;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "MORADIA")
    @Enumerated(EnumType.STRING)
    private TipoDeMoradiaEnum moradia;

    @Column(name = "DATAREGISTRO")
    private LocalDateTime dataRegistro = LocalDateTime.now();

    public Enderecos (){}

    public Enderecos(EnderecoDTO endereco) {
        this.rua = endereco.rua();
        this.bairro = endereco.bairro();
        this.moradia = endereco.moradia();
    }
}
