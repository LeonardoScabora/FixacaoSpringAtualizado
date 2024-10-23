package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoProfessorDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.AlunoProfessor;
import estagioCEPEIN.FixacaoSpring.Models.servise.AlunoProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/relacionamento")
public class AlunoProfessorController {

    @Autowired
    AlunoProfessorService alunoProfessorservice;

    @GetMapping
    public List<AlunoProfessor> VerAlunoProfessor() {
        return alunoProfessorservice.getAll();
    }

    @PostMapping
    public AlunoProfessor JuntarAlunoProfessor(@RequestBody AlunoProfessor alunoProfessor) {
        return alunoProfessorservice.save(alunoProfessor);
    }

    @PutMapping(path = "/{id}")
    public AlunoProfessor AtualizarAlunoProfessor(@PathVariable Long id, @RequestBody AlunoProfessorDTO AlunoProf){
        return alunoProfessorservice.update(id, AlunoProf);
    }

    @DeleteMapping(path = "/{id}")
    public String DeletarAlunoProfessor(@PathVariable Long id){
        return alunoProfessorservice.delete(id);
    }


    @GetMapping(path = "id/{id}")
    public AlunoProfessor buscarPorId(@PathVariable Long id){
        return alunoProfessorservice.getById(id);
    }


}
