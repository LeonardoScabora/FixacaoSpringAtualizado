package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.DisciplinasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.ProfessorConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.ProfessorDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.TurmasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplinas;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professores;
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

    public Professores getById(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não Encontrado"));
    }

    public List<ProfessorConsultaDTO> getAll() {
        List<ProfessorConsultaDTO> professores = VerProfessorAtualizado(professorRepository.findAll());

        return professores;
    }

    ///POST
    @Transactional
    public Professores save(ProfessorDTO professor) {
        return professorRepository.save(new Professores(professor));}

    ///PUT
    @Transactional
    public Professores update(Long id, ProfessorDTO professor) {
        Professores professoresFound = getById(id);

        professoresFound.setNome(professor.nome() != null ? professor.nome() : professoresFound.getNome());
        professoresFound.setDataNascimento(professor.dataNascimento() != null ? professor.dataNascimento() : professoresFound.getDataNascimento());
        professoresFound.setSalario(professor.salario() != null ? professor.salario() : professoresFound.getSalario());
        professoresFound.setCargo(professor.cargo() != null ? professor.cargo() : professoresFound.getCargo());

        return professorRepository.save(professoresFound);
    }

    ///DELETE
    @Transactional
    public String delete(Long id) {
        professorRepository.deleteById(id);
        return "Profissional deletado com sucesso";
    }

    private List<ProfessorConsultaDTO> VerProfessorAtualizado(List<Professores> professoresEntidade) {
        List<ProfessorConsultaDTO> professoresDTO = new ArrayList<ProfessorConsultaDTO>();

        for (Professores professores : professoresEntidade) {
            ProfessorConsultaDTO professorNovo = new ProfessorConsultaDTO(
                    professores.getId(),
                    professores.getNome(),
                    professores.getDataNascimento(),
                    professores.getSalario(),
                    professores.getCargo(),
                    VerTurmasAtualizado(professores.getTurmasList()),
                    VerDisciplinasAtualizada(professores.getDisciplinasList())
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
