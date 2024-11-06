package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.turma.TurmasDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_turma")
    private Long id;

    @Column(name = "SERIE_turma")
    private String serie;

    @Column(name = "DATAREGISTRO")
    private LocalDateTime dataRegistro;

    @ManyToOne
    @JoinColumn(name="ID_prof", nullable = false)
    private Professor profId;

    public Turma() {}

    public Turma(TurmasDTO turmasDTO) {
        this.serie = turmasDTO.serie();
        this.dataRegistro = LocalDateTime.now();
        this.profId = turmasDTO.profId();
    }
}
