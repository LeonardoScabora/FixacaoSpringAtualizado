package estagioCEPEIN.FixacaoSpring.Models.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Endereco")
public class Enderecos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_endereco")
    private Long id;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "DATAREGISTRO")
    private LocalDateTime dataRegistro = LocalDateTime.now();
}
