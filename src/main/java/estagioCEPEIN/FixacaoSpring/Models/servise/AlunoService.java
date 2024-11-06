package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.aluno.AlunoConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.aluno.AlunoDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.aluno.ProfessorConsultaAluno;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Aluno;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professor;
import estagioCEPEIN.FixacaoSpring.Models.exceptions.DataNotFoundException;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    EnderecoService enderecoServise;

    ///findBy
    public AlunoConsultaDTO findById(Long id) {
        if (alunoRepository.existsById(id)) {
           AlunoConsultaDTO alunos = VerAlunosAtualizado(alunoRepository.findById(id).get());
            return alunos;
        }
        throw new DataNotFoundException("Aluno");
    }
    ///findByIn
    public List<Aluno> buscarPorSobrenome(List<String> sobrenome){
             return alunoRepository.findBySobrenomeIn(sobrenome);
    }
    ///findFirstBy
    public List<AlunoConsultaDTO>  findFirtBy() {
        List<AlunoConsultaDTO>  aluno = alunoRepository.findFirstBy();
        return aluno;
    }
    ///findByAnd
    public List<AlunoConsultaDTO> findByIdAndNome(Long id, String nome) {
        List<AlunoConsultaDTO> aluno = VerAlunos(alunoRepository.findByIdAndNomeContainingIgnoreCase(id, nome));
        return aluno;
    }
    ///findByOr
    public List<AlunoConsultaDTO> findByNomeOrSobrenome(String nome, String sobrenome){
        List<AlunoConsultaDTO> aluno = VerAlunos(alunoRepository.findByNomeContainingIgnoreCaseOrSobrenomeContainingIgnoreCase(nome, sobrenome));
        return aluno;
    }
    ///POST
    @Transactional
    public Aluno save(AlunoDTO aluno){
        enderecoServise.save(aluno.endereco());
        return alunoRepository.save(new Aluno(aluno));
    }

    ///GET
    public List<AlunoConsultaDTO> getAll() {
        List<AlunoConsultaDTO> alunos = VerAlunos(alunoRepository.findAll());

        return alunos;
    }

    ///PUT
    @Transactional
    public Aluno update(Long id, AlunoDTO alunoNovo) {
            if (alunoRepository.existsById(id)) {
                List<Aluno> alunoFound = alunoRepository.findAll();
                for(Aluno aluno : alunoFound){
                    if(aluno.getId().equals(id)){

                        aluno.setNome(alunoNovo.nome() != null ? alunoNovo.nome() : aluno.getNome());
                        aluno.setSobrenome(alunoNovo.sobrenome() != null ? alunoNovo.sobrenome() : aluno.getSobrenome());
                        aluno.setDataNascimento(alunoNovo.dataNascimento() != null ? alunoNovo.dataNascimento() : aluno.getDataNascimento());

                        return alunoRepository.save(aluno);
                    }
                }
            }
            throw new DataNotFoundException("Aluno");
    }
    ///DELETE
    @Transactional
    public String delete(Long id) {
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
            return "Aluno removido com sucesso!";
        }
        throw new DataNotFoundException("Aluno");
    }

    private AlunoConsultaDTO VerAlunosAtualizado (Aluno aluno){
            AlunoConsultaDTO alunoConsultaDTO = new AlunoConsultaDTO(
                    aluno.getId(),
                    aluno.getNome(),
                    aluno.getSobrenome(),
                    aluno.getDataNascimento(),
                    aluno.getDataRegistro(),
                    aluno.getEndereco(),
                    VerProfessor(aluno.getProfessores())
            );
            if(alunoConsultaDTO != null){
                return alunoConsultaDTO;
            }
            throw new DataNotFoundException("Aluno");
    }

    private List<AlunoConsultaDTO> VerAlunos (List<Aluno> aluno){
        List<AlunoConsultaDTO> alunosDTOs = new ArrayList<AlunoConsultaDTO>();

        for(Aluno alunoBusca : aluno){
            AlunoConsultaDTO alunoConsultaDTO = new AlunoConsultaDTO(
                    alunoBusca.getId(),
                    alunoBusca.getNome(),
                    alunoBusca.getSobrenome(),
                    alunoBusca.getDataNascimento(),
                    alunoBusca.getDataRegistro(),
                    alunoBusca.getEndereco(),
                    VerProfessor(alunoBusca.getProfessores())
            );
            alunosDTOs.add(alunoConsultaDTO);
        }

        if(alunosDTOs.isEmpty()){
            throw new DataNotFoundException("Aluno");
        }else{
        }
            return alunosDTOs;
    }

    private List<ProfessorConsultaAluno> VerProfessor(List<Professor> professorEntidade) {
        List<ProfessorConsultaAluno> professoresDTO = new ArrayList<ProfessorConsultaAluno>();

        for (Professor professor : professorEntidade) {
            ProfessorConsultaAluno professorNovo = new ProfessorConsultaAluno(
                    professor.getId(),
                    professor.getNome(),
                    professor.getSobrenome(),
                    professor.getDataNascimento(),
                    professor.getSalario(),
                    professor.getCargo()
            );

            professoresDTO.add(professorNovo);
        }

        return professoresDTO;
    }
}
