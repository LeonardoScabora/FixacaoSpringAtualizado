package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoProfessorDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.ProfessorConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.AlunoProfessor;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.AlunoProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoProfessorService {

    @Autowired
    AlunoProfessorRepository alunoProfessorRepository;

    @Autowired
    ProfessorService professorService;

    @Autowired
    AlunoService alunoService;


    public AlunoProfessor getById(Long id){
        return alunoProfessorRepository.getById(id);
    }

    public List<AlunoProfessor> getAll() {
      List<AlunoProfessor> alunosProfessores = alunoProfessorRepository.findAll();
      return alunosProfessores;
    }

    @Transactional
    public AlunoProfessor save(AlunoProfessor Novo) {
        Long valorAluno = null;
        Long valorProfessor = null;

        List<ProfessorConsultaDTO> profBusca = professorService.getAll();
        List<AlunoConsultaDTO> alunoBusca = alunoService.getAll();

        for(AlunoConsultaDTO buscar : alunoBusca) {
            if(Novo.getIdAluno() == buscar.id()) {
                valorAluno = buscar.id();
            }
        }
            for(ProfessorConsultaDTO buscar : profBusca){
                if(Novo.getIdProf() == buscar.id()){
                    valorProfessor = buscar.id();
                }
            }
        AlunoProfessor alunoProfessor = new AlunoProfessor(
                 null,
                valorAluno,
                valorProfessor
        );
        return alunoProfessorRepository.save(alunoProfessor);
    }

    @Transactional
    public AlunoProfessor update(Long id, AlunoProfessorDTO AlunoProf) {
        AlunoProfessor AcharAlunoProf = getById(id);

        AcharAlunoProf.setIdAluno(AlunoProf.idAluno() != null ? AlunoProf.idAluno() : AcharAlunoProf.getIdAluno());
        AcharAlunoProf.setIdProf(AlunoProf.idProf() != null ? AlunoProf.idProf() : AcharAlunoProf.getIdProf());

        return alunoProfessorRepository.save(AcharAlunoProf);
    }

    @Transactional
    public String delete(Long id){
        alunoProfessorRepository.deleteById(id);
        return "Relacionamento Excluido com Sucesso";
    }


}
