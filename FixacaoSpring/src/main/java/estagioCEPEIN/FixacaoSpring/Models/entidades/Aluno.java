package estagioCEPEIN.FixacaoSpring.Models.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import estagioCEPEIN.FixacaoSpring.Models.dto.aluno.AlunoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_aluno")
    private Long id;

    @Column(name = "NOME_aluno")
    @NotBlank(message = "Nome é obrigatorio")
    private String nome;

    @Column(name = "SOBRENOME_aluno")
    @NotBlank(message = "Sobrenome é obrigatorio")
    private String sobrenome;

    @Column(name = "DATANASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "DATAREGISTRO")
    private LocalDateTime dataRegistro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_ID", referencedColumnName = "ID_endereco")
    private Endereco endereco;

    @ManyToMany(mappedBy = "alunos")
    @JsonIgnoreProperties("alunos")
    private List<Professor> professores = new ArrayList<>();

    public Aluno() {}

    public Aluno(AlunoDTO alunodto) {
        this.nome = alunodto.nome();
        this.sobrenome = alunodto.sobrenome();
        this.dataNascimento = alunodto.dataNascimento();
        this.dataRegistro = LocalDateTime.now();
        this.endereco = alunodto.endereco();
    }




}
