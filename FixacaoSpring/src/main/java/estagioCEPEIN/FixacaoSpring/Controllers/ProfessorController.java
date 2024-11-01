package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.professor.ProfessorConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.professor.ProfessorDTO;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professor;
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
    public ResponseEntity<Professor> novoProfessor (@RequestBody ProfessorDTO professor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(professor));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorConsultaDTO>> listaProfessor() {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
    }

    @PutMapping(path = "/id/{id}")
    public ResponseEntity<ProfessorConsultaDTO> AlterarProfessor (@PathVariable Long id, @RequestBody ProfessorDTO professornovo) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.update(id, professornovo));
    }

    @DeleteMapping(path = "/{id}")
    public String ExcluirProfessorPorID (@PathVariable Long id) {
        return professorService.delete(id);}

    //JPA QUERY
    ///findBy
    @GetMapping(path = "/id/{id}")
    public ProfessorConsultaDTO procurarProfessorID(@PathVariable Long id) {return professorService.findById(id);
    }
    ///findFirstBy
    @GetMapping(path = "/primeiro")
    public List<ProfessorConsultaDTO> procuraPrimeiro() {return professorService.findFirstBy();
    }
    ///findByAnd
    @GetMapping(path = "/NomeECargo")
    public List<ProfessorConsultaDTO> procuraPorNomeECargo (@RequestParam String nome, @RequestParam TipoCargoEnum cargo) {
        return professorService.findNomeECargo(nome,cargo);
    }
    ///findByOr
    @GetMapping(path = "/NomeOuSobrenome")
    public List<ProfessorConsultaDTO> procuraPorNomeOuSobrenome(@RequestParam String nome, @RequestParam String sobrenome) {
        return professorService.findByNomeOuSobrenome(nome, sobrenome);
    }
    ///findByOrderByDesc
    @GetMapping(path = "/AcimaDe")
    public List<ProfessorConsultaDTO> buscarPorMaiorSalario(@RequestParam BigDecimal salario){
        return professorService.findMaiorSalario(salario);
    }
    ///findByBetween
    @GetMapping(path = "/Entre")
    public List<ProfessorConsultaDTO> buscarPorFaixaSalarial(@RequestParam BigDecimal salarioMin, @RequestParam BigDecimal salarioMax){
        return professorService.findFaixaSalario(salarioMin,salarioMax);
    }
    ///findByOrderBy
    @GetMapping(path = "/SalarioCrescente")
    public List<ProfessorConsultaDTO> SalarioEmOrdemCrescente(){
        return professorService.findSalarioCrescente();
    }
    ///findByLessThan
    @GetMapping(path = "/SalarioMenorQ")
    public List<ProfessorConsultaDTO> SalarioMenorQue(@RequestParam BigDecimal salarioMax){
        return professorService.findSalarioMenorQue(salarioMax);
    }
    ///findByLessThanEqual
    @GetMapping(path = "/SalarioMenorIgual")
    public List<ProfessorConsultaDTO> SalarioMenorIgual(@RequestParam BigDecimal salarioMax){
        return professorService.findSalarioMenorOuIgual(salarioMax);
    }
    ///findByGreaterThan
    @GetMapping(path = "/SalarioMaiorQ")
    public List<ProfessorConsultaDTO> SalarioMaiorQue(@RequestParam BigDecimal salarioMin){
        return professorService.findSalarioMaiorQue(salarioMin);
    }
    ///findByGreaterThanEqual
    @GetMapping(path = "/SalarioMaiorIgual")
    public List<ProfessorConsultaDTO> SalarioMaiorIgual(@RequestParam BigDecimal salarioMin){
        return professorService.findSalarioMaiorOuIgual(salarioMin);
    }
}
