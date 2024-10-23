package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.TurmasDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Turma")
public class Turmas {

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
    private Professores profId;

    public Turmas() {}

    public Turmas(TurmasDTO turmasDTO) {
        this.serie = turmasDTO.serie();
        this.dataRegistro = LocalDateTime.now();
        this.profId = turmasDTO.profId();
    }
}
