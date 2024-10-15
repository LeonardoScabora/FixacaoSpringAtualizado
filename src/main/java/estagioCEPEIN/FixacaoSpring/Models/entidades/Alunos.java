package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Aluno")
public class Alunos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_aluno")
    private Long id;

    @Column(name = "NOME_aluno")
    private String nome;

    @Column(name = "DATANASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "DATAREGISTRO")
    private LocalDateTime dataRegistro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_ID", referencedColumnName = "ID_endereco")
    private Enderecos endereco;

    @ManyToMany(mappedBy = "Turma")
    private List<Turmas> turmas = new ArrayList<>();

//    @OneToMany(mappedBy = "Alunos", cascade = CascadeType.ALL)
//    private Set<AlunoTurma> alunoTurmaSet = new HashSet<>();

    public Alunos() {}

    public Alunos(AlunoDTO alunodto) {
        this.nome = alunodto.nome();
        this.dataNascimento = alunodto.dataNascimento();
        this.dataRegistro = LocalDateTime.now();
        this.endereco = alunodto.endereco();
    }




}
