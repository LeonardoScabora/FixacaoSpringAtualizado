package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Aluno;
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
    public ResponseEntity<Aluno> NovoAluno (@RequestBody AlunoDTO aluno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoServise.save(aluno));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> ListaAlunos () {
        return ResponseEntity.status(HttpStatus.OK).body(alunoServise.getAll());
    }

    @PutMapping
    public ResponseEntity<Aluno> AtualizaAluno (@RequestParam Long id, @RequestBody AlunoDTO aluno) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alunoServise.update(id, aluno));
    }



    @DeleteMapping(path = "/{id}")
    public String ExcluirAluno (@RequestParam Long id) {
         alunoServise.delete(id);
        return "Aluno deletado com Sucesso!";
    }

}
