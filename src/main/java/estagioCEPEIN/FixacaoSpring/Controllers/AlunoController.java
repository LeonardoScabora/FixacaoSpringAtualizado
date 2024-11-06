package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.aluno.AlunoConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.aluno.AlunoDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Aluno;
import estagioCEPEIN.FixacaoSpring.Models.servise.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> novoAluno(@RequestBody @Valid AlunoDTO aluno) {
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(aluno));
    }

    @GetMapping
    public ResponseEntity<List<AlunoConsultaDTO>> ListaAlunos () {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.getAll());
    }

    @PutMapping(path = "/id/{id}")
    public ResponseEntity<?> AtualizaAluno (@PathVariable Long id, @RequestBody AlunoDTO aluno) {
        Aluno alunoAtualizado = alunoService.update(id, aluno);
        return ResponseEntity.ok(alunoAtualizado);
    }

    @DeleteMapping(path = "/id/{id}")
    public String ExcluirAluno (@PathVariable Long id) {
        return alunoService.delete(id);
    }

    //JPA QUERY
    ///findBy
    @GetMapping(path = "/id/{id}")
    public AlunoConsultaDTO BuscaAlunoId (@PathVariable Long id) {
        return alunoService.findById(id);
    }

    ///findFirstBy
    @GetMapping(path = "/primeiro")
    public List<AlunoConsultaDTO>  ListaPrimeiroAluno () {
        return alunoService.findFirtBy();
    }

    ///findByAnd
    @GetMapping(path = "/IdENome")
    public List<AlunoConsultaDTO> BuscarAlunoPorIdENome (@RequestParam Long id, @RequestParam String nome) {
        return alunoService.findByIdAndNome(id,nome);
    }
    ///findByOr
    @GetMapping(path = "/NomeOuSobrenome")
    public List<AlunoConsultaDTO> BuscarAlunoPorNomeOuSobrenome (@RequestParam String nome, @RequestParam String sobrenome) {
        return alunoService.findByNomeOrSobrenome(nome, sobrenome);
    }
    ///findByIn
    @GetMapping(path = "/BuscarPorSobrenome")
    public List<Aluno> moradiaAluno(@RequestParam List<String> sobrenome){
        return alunoService.buscarPorSobrenome(sobrenome);
    }



}

