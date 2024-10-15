package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.TurmasDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name="ID_prof", nullable = false)
    private Professores profId;

    @ManyToMany
    @JoinTable(
            name = "Aluno_Turma",
            joinColumns = @JoinColumn(name = "ID_turma"),
            inverseJoinColumns = @JoinColumn(name = "ID_aluno"))
    private List<Alunos> alunos = new ArrayList<>();

//    @OneToMany(mappedBy = "Turmas", cascade = CascadeType.ALL)
//    private Set<AlunoTurma> alunoTurmasSet = new HashSet<>();


    public Turmas() {}

    public Turmas(TurmasDTO turmasDTO) {
        this.serie = turmasDTO.serie();
        this.profId = turmasDTO.profId();
    }
}
