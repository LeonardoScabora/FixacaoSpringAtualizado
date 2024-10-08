package estagioCEPEIN.FixacaoSpring.Models.entidades;

import estagioCEPEIN.FixacaoSpring.Models.dto.EnderecoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_endereco")
    private Long id;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "BAIRRO")
    private String bairro;

    @OneToOne
    private Aluno alunoEndereco;

    public Endereco (){}

    public Endereco (EnderecoDTO endereco){
        this.rua = endereco.rua();
        this.bairro = endereco.bairro();
    }
}
