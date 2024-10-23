package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.ProfessorConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.ProfessorDTO;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professores;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.ProfessorRepository;
import estagioCEPEIN.FixacaoSpring.Models.servise.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/api/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Professores> novoProfessor (@RequestBody ProfessorDTO professor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(professor));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorConsultaDTO>> listaProfessor() {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
    }

    @PutMapping
    public ResponseEntity<Professores> AlterarProfessor (@PathVariable Long id, @RequestBody ProfessorDTO professornovo) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(professorService.update(id, professornovo));
    }

    @DeleteMapping(path = "/{id}")
    public String ExcluirProfessorPorID (@PathVariable Long id) {
        return professorService.delete(id);}

    ///JPA QUERY
    ///findBy
    @GetMapping(path = "/id/{id}")
    public Professores procurarProfessorID(@PathVariable Long id) {return professorService.findById(id);}


    ///findFirstBy
    @GetMapping(path = "/primeiro")
    public Iterable<Professores> procuraPrimeiro() {return professorService.findFirstBy();}

    ///findByAnd
    @GetMapping(path = "/NomeECargo/{nome}/{cargo}")
    public Iterable<Professores> procuraPorNomeECargo (@PathVariable String nome, @PathVariable TipoCargoEnum cargo) {
        return professorService.findNomeECargo(nome,cargo);
    }

    ///findByOr
    @GetMapping(path = "/NomeOuSobrenome")
    public Iterable<Professores> procuraPorNomeOuSobrenome(@RequestParam String nome, @RequestParam String sobrenome) {
        return professorService.findByNomeOuSobrenome(nome, sobrenome);
    }

    ///findByOrderByDesc
    @GetMapping(path = "/MaiorSalario")
    public List<Professores> buscarPorMaiorSalario(BigDecimal salario){
        return professorService.findMaiorSalario(salario);
    }

//    ///findByBetween
//    @GetMapping(path = "/FaixaSalario")
//    public List<Professores> buscarPorFaixaSalarial(@RequestParam BigDecimal salarioMin, @RequestParam BigDecimal salarioMax){
//        return professorService.findFaixaSalario(salarioMin,salarioMax);
//    }
//
//    ///findByOrderBy
//    @GetMapping(path = "/SalarioCrescente")
//    public List<Professores> SalarioEmOrdemCrescente(){
//        return professorService.findSalarioCrescente();
//    }
    ///findByLessThan
    ///findByLessThanEqual
    ///findByGreaterThan
    ///findByGreaterThanEqual
}
