package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.DisciplinasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.ProfessorConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.ProfessorDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.TurmasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplinas;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professor;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Turmas;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public Professor getById(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não Encontrado"));
    }

    public List<ProfessorConsultaDTO> getAll() {
        List<ProfessorConsultaDTO> professores = VerProfessorAtualizado(professorRepository.findAll());

        return professores;
    }

    ///POST
    @Transactional
    public Professor save(ProfessorDTO professor) {
        return professorRepository.save(new Professor (professor));}

    ///PUT
    @Transactional
    public Professor update(Long id, ProfessorDTO professor) {
        Professor professorFound = getById(id);

        professorFound.setNome(professor.nome() != null ? professor.nome() : professorFound.getNome());
        professorFound.setDataNascimento(professor.dataNascimento() != null ? professor.dataNascimento() : professorFound.getDataNascimento());
        professorFound.setSalario(professor.salario() != null ? professor.salario() : professorFound.getSalario());
        professorFound.setCargo(professor.cargo() != null ? professor.cargo() : professorFound.getCargo());

        return professorRepository.save(professorFound);
    }

    ///DELETE
    @Transactional
    public String delete(Long id) {
        professorRepository.deleteById(id);
        return "Profissional deletado com sucesso";
    }

    private List<ProfessorConsultaDTO> VerProfessorAtualizado(List<Professor> professoresEntidade) {
        List<ProfessorConsultaDTO> professoresDTO = new ArrayList<ProfessorConsultaDTO>();

        for (Professor professor: professoresEntidade) {
            ProfessorConsultaDTO professorNovo = new ProfessorConsultaDTO(
                    professor.getId(),
                    professor.getNome(),
                    professor.getDataNascimento(),
                    professor.getSalario(),
                    professor.getCargo(),
                    VerTurmasAtualizado(professor.getTurmasList()),
                    VerDisciplinasAtualizada(professor.getDisciplinasList())
            );

            professoresDTO.add(professorNovo);
        }

        return professoresDTO;
    }

    private List<TurmasConsultaDTO> VerTurmasAtualizado(List<Turmas> turmas) {
        List<TurmasConsultaDTO> turmasDTO = new ArrayList<TurmasConsultaDTO>();

        for (Turmas turma: turmas) {
            TurmasConsultaDTO turmaNova = new TurmasConsultaDTO(
                    turma.getId(),
                    turma.getSerie());

            turmasDTO.add(turmaNova);
        }

        return turmasDTO;
    }

    private List<DisciplinasConsultaDTO> VerDisciplinasAtualizada(List<Disciplinas> disciplinas) {
        List<DisciplinasConsultaDTO> disciplinasDTO = new ArrayList<DisciplinasConsultaDTO>();

        for(Disciplinas disciplina : disciplinas) {
            DisciplinasConsultaDTO disciNova = new DisciplinasConsultaDTO(
                    disciplina.getId(),
                    disciplina.getNome()
            );
            disciplinasDTO.add(disciNova);
        }
        return disciplinasDTO;
    }
}
