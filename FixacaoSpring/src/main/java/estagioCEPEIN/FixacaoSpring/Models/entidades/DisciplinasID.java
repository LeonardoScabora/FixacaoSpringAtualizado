package estagioCEPEIN.FixacaoSpring.Models.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DisciplinasID implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_disciplina")
    private Long id;

    @Column(name = "ID_SEGUNDARIO")
    private String idSegundario;

}
