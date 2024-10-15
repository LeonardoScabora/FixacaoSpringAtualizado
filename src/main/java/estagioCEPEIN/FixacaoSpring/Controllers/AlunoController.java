package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Alunos;
import estagioCEPEIN.FixacaoSpring.Models.servise.AlunoServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoServise alunoServise;

    @PostMapping
    public ResponseEntity<Alunos> novoAluno(@RequestBody AlunoDTO aluno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoServise.save(aluno));
    }

    @GetMapping
    public ResponseEntity<List<Alunos>> ListaAlunos () {
        return ResponseEntity.status(HttpStatus.OK).body(alunoServise.getAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Alunos> AtualizaAluno (@PathVariable Long id, @RequestBody AlunoDTO aluno) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alunoServise.update(id, aluno));
    }

    @DeleteMapping(path = "/{id}")
    public String ExcluirAluno (@PathVariable Long id) {
         alunoServise.delete(id);
        return "Aluno deletado com Sucesso!";
    }

}
