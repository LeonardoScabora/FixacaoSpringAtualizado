package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.DisciplinasDTO;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDoEstagioEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Disciplina")
public class Disciplinas {

    @EmbeddedId
    private DisciplinasID ids;

    @Column(name = "Nome_disciplina")
    private String nome;

    @Column(name = "SIMBOLO")
    private String simbolo;

    @Column(name = "ESTAGIO")
    @Enumerated(EnumType.STRING)
    private TipoDoEstagioEnum estagio;

    @Column(name = "DATAREGISTRO")
    private LocalDateTime dataRegistro;

    @ManyToOne
    @JoinColumn(name = "ID_prof", nullable = false)
    private Professores professorId; ///Relação Adicionada

    public Disciplinas() {}

    public Disciplinas(DisciplinasDTO disciplinasDTO){
        this.ids = disciplinasDTO.ids();
        this.nome = disciplinasDTO.nome();
        this.simbolo = disciplinasDTO.simbolo();
        this.estagio = disciplinasDTO.estagio();
        this.dataRegistro = LocalDateTime.now();
        this.professorId = disciplinasDTO.professorId();
    }
}
