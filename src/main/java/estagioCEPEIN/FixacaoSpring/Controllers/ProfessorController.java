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

import java.util.List;

@RestController
@RequestMapping(path = "/api/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private ProfessorRepository professorRepository;

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
    @GetMapping(path = "/procurar/{cargo}")
    public Iterable<Professores> procuraProfessor(@PathVariable TipoCargoEnum cargo) {return professorRepository.findByCargo(cargo);}

    @GetMapping(path = "/procurar/primeiro")
    public Iterable<Professores> procuraPrimeiro() {return professorRepository.findFirstBy();}

    @GetMapping(path = "/procurar/{nome}/{cargo}")
    public Iterable<Professores> procuraPorNomeECargo (@PathVariable String nome, @PathVariable TipoCargoEnum cargo) {
        return professorRepository.findByNomeContainingIgnoreCaseAndCargo(nome,cargo);
    }
}
