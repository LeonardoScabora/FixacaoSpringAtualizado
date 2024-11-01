package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.disciplina.DisciplinasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.disciplina.DisciplinasDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplina;
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
    public ResponseEntity<DisciplinasConsultaDTO> novaDisciplina(@RequestBody DisciplinasDTO disciplina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.save(disciplina));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinasConsultaDTO>> listDisciplinas() {
     return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.getAll());
    }

    @PutMapping(path = "/id/{id}")
    public ResponseEntity<Disciplina> editDisciplina(@PathVariable Long id, @RequestBody DisciplinasDTO disciplina) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.update(id, disciplina));
    }

    @DeleteMapping(path = "/id/{id}")
    public String deletarDisciplina(@PathVariable Long id){
        return disciplinaService.deleteId(id);
    }

    @GetMapping(path = "/id/{id}")
    public DisciplinasConsultaDTO buscarPorId(@PathVariable Long id){
        return disciplinaService.getById(id);
    }
}
