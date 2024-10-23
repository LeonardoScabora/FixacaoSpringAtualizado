package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.DisciplinasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.DisciplinasDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplinas;
import estagioCEPEIN.FixacaoSpring.Models.entidades.DisciplinasID;
import estagioCEPEIN.FixacaoSpring.Models.servise.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplina")
public class DisciplinasController {

    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Disciplinas> novaDisciplina(@RequestBody DisciplinasDTO disciplina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.save(disciplina));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinasConsultaDTO>> listDisciplinas() {
     return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.getAll());
    }

    @PutMapping(path = "/id/{id}")
    public ResponseEntity<Disciplinas> editDisciplina(@PathVariable Long id, @RequestBody DisciplinasDTO disciplina) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(disciplinaService.update(id, disciplina));
    }

    @DeleteMapping(path = "/id/{id}")
    public String deletarDisciplina(@PathVariable Long id){
        return disciplinaService.delete(id);
    }

}
