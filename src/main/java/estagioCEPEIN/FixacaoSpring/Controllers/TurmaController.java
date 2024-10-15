package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.TurmasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.TurmasDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Turmas;
import estagioCEPEIN.FixacaoSpring.Models.servise.TurmasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/turma")
public class TurmaController {

    @Autowired
    TurmasService turmasService;

    @PostMapping
    public ResponseEntity<Turmas> novaTurma (@RequestBody TurmasDTO turma) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turmasService.save(turma));
    }

    @GetMapping
    public ResponseEntity<List<TurmasConsultaDTO>> listarTurmas () {
        return ResponseEntity.status(HttpStatus.OK).body(turmasService.getAll());
    }

    @PutMapping
    public ResponseEntity<Turmas> alterarTurmas (@PathVariable Long id, @RequestBody TurmasDTO turma) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(turmasService.update(id, turma));
    }

    @DeleteMapping(path = "/{id}")
    public String removerTurmas (@PathVariable Long id) {
        return turmasService.delete(id);
    }

    @GetMapping(path = "/procurar/")
    public List<Turmas> BuscaPorSerie (@RequestParam List<String> serie) {
        return turmasService.buscarTurmasPorSerie(serie);
    }

}
