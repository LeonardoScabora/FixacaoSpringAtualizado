package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.disciplina.DisciplinasDTO;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDoEstagioEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Disciplina")
public class Disciplina {

    @EmbeddedId
    private DisciplinaID ids;

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
    private Professor professorId; ///Relação Adicionada

    public Disciplina() {}

    public Disciplina(DisciplinasDTO disciplinasDTO){
        this.ids = disciplinasDTO.ids();
        this.nome = disciplinasDTO.nome();
        this.simbolo = disciplinasDTO.simbolo();
        this.estagio = disciplinasDTO.estagio();
        this.dataRegistro = LocalDateTime.now();
        this.professorId = disciplinasDTO.professorId();
    }
}
