package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Alunos;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Enderecos;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDeMoradiaEnum;
import estagioCEPEIN.FixacaoSpring.Models.servise.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Alunos> novoAluno(@RequestBody AlunoDTO aluno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(aluno));
    }

    @GetMapping
    public ResponseEntity<List<AlunoConsultaDTO>> ListaAlunos () {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.getAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Alunos> AtualizaAluno (@PathVariable Long id, @RequestBody AlunoDTO aluno) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alunoService.update(id, aluno));
    }

    @DeleteMapping(path = "/{id}")
    public String ExcluirAluno (@PathVariable Long id) {
         alunoService.delete(id);
        return "Aluno deletado com Sucesso!";
    }

    ///JPA QUERY
    ///findBy
    @GetMapping(path = "/id/{id}")
    public Alunos BuscaAlunoId (@PathVariable Long id) {
        return alunoService.findById(id);
    }

    ///findFirstBy
    @GetMapping(path = "/primeiro")
    public Iterable<Alunos> ListaPrimeiroAluno () {
        return alunoService.findFirtBy();
    }

    ///findByAnd
    @GetMapping(path = "/IdENome/{id}/{nome}")
    public Iterable<Alunos> BuscarAlunoPorIdENome (@RequestParam Long id, @RequestParam String nome) {
        return alunoService.findByIdAndNome(id,nome);
    }

    ///findByOr
    @GetMapping(path = "/NomeOuSobrenome")
    public Iterable<Alunos> BuscarAlunoPorNomeOuSobrenome (@RequestParam String nome, @RequestParam String sobrenome) {
        return alunoService.findByNomeOrSobrenome(nome, sobrenome);
    }

    ///findByIn
    @GetMapping(path = "BuscarPorSobrenome")
    public List<Alunos> moradiaAluno(@RequestParam List<String> sobrenome){
        return alunoService.buscarPorSobrenome(sobrenome);
    }



}

