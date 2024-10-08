package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.TurmasDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private Professor profId;

//    @ManyToMany
//    @JoinTable(name = "Aluno_Turmas",
//            joinColumns = @JoinColumn(name = "turmas_fk"),
//            inverseJoinColumns = @JoinColumn(name = "aluno_fk")
//    )
//    List<Aluno> alunoList = new ArrayList<>();


    public Turmas() {}

    public Turmas(TurmasDTO turmasDTO) {
        this.serie = turmasDTO.serie();
        this.profId = turmasDTO.profId();
    }
}
