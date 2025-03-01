package estagioCEPEIN.FixacaoSpring.Models.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import estagioCEPEIN.FixacaoSpring.Models.dto.professor.ProfessorDTO;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_prof")
    private Long id;

    @Column(name = "NOME_prof")
    private String nome;

    @Column(name = "SOBRENOME_prof")
    private String sobrenome;

    @Column(name = "DataNASC_prof")
    private LocalDate dataNascimento;

    @Column(name = "SALARIO")
    @Min(value = 0)
    private BigDecimal salario;

    @Column(name = "CARGO")
    @Enumerated(EnumType.STRING)
    private TipoCargoEnum cargo;

    @Column(name = "DATAREGISTRO")
    private LocalDateTime dataRegistro;

    @OneToMany(mappedBy = "profId")
    private List<Turma> turmaList = new ArrayList<>(); ///RELAÇÃO adicionada

    @OneToMany(mappedBy = "professorId")
    private List<Disciplina> disciplinaList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "ALUNO_PROFESSORES",
            joinColumns = @JoinColumn(name = "ID_prof"),
            inverseJoinColumns = @JoinColumn(name = "ID_aluno"))
    @JsonIgnoreProperties("professores")
    private List<Aluno> alunos;

    public Professor() {}

    public Professor(ProfessorDTO professorDTO) {
        this.nome = professorDTO.nome();
        this.sobrenome = professorDTO.sobrenome();
        this.dataNascimento = professorDTO.dataNascimento();
        this.salario = professorDTO.salario();
        this.cargo = professorDTO.cargo();
        this.dataRegistro = LocalDateTime.now();
    }

}
