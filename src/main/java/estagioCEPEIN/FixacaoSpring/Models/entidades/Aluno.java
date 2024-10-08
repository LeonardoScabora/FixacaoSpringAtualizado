package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String nome;

    @Column(name = "DATANASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "DATAREGISTRO")
    private LocalDateTime dataRegistro;

    @OneToOne(mappedBy = "alunoEndereco", cascade = CascadeType.ALL)
    @JoinColumn(name = "Endereco_id")
    private Endereco enderecoAluno;

    public Aluno() {}

    public Aluno(AlunoDTO alunoNovo) {
        this.nome = alunoNovo.nome();
        this.dataNascimento = alunoNovo.dataNascimento();
        this.dataRegistro = LocalDateTime.now();
        this.enderecoAluno = alunoNovo.enderecoAluno();
    }

}
