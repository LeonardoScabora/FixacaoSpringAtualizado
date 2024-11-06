package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.alunoProfessor.AlunoProfessorDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.AlunoProfessor;
import estagioCEPEIN.FixacaoSpring.Models.servise.AlunoProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/relacionamento")
public class AlunoProfessorController {

    @Autowired
    AlunoProfessorService alunoProfessorService;

    @GetMapping
    public List<AlunoProfessor> VerAlunoProfessor() {
        return alunoProfessorService.getAll();
    }

    @PostMapping
    public AlunoProfessor JuntarAlunoProfessor(@RequestBody AlunoProfessor alunoProfessor) {
        return alunoProfessorService.save(alunoProfessor);
    }

    @PutMapping(path = "/id/{id}")
    public AlunoProfessor AtualizarAlunoProfessor(@PathVariable Long id, @RequestBody AlunoProfessorDTO AlunoProf){
        return alunoProfessorService.update(id, AlunoProf);
    }

    @DeleteMapping(path = "/id/{id}")
    public String DeletarAlunoProfessor(@PathVariable Long id){
        return alunoProfessorService.delete(id);
    }

    @GetMapping(path = "id/{id}")
    public AlunoProfessor buscarPorId(@PathVariable Long id){
        return alunoProfessorService.getById(id);
    }


}
