package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.DisciplinasDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Disciplina")
public class Disciplinas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_disciplina")
    private Long id;

    @Column(name = "Nome_disciplina")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_turma", nullable = false)
    private Professor professorId; ///Relação Adicionada

    public Disciplinas() {}

    public Disciplinas(DisciplinasDTO disciplinasDTO){
        this.nome = disciplinasDTO.nome();
        this.professorId = disciplinasDTO.professorId();
    }

}
