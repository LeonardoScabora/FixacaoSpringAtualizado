package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.ProfessorConsultaAluno;
import estagioCEPEIN.FixacaoSpring.Models.dto.ProfessorConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Alunos;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professores;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoDeMoradiaEnum;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.AlunoRepository;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    EnderecoService enderecoServise;



    public Alunos findById(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não Encontrado"));
    }

    public List<Alunos> buscarPorSobrenome(List<String> sobrenome){
        return alunoRepository.findBySobrenomeIn(sobrenome);
    }


    public Iterable<Alunos> findFirtBy() {
        Iterable<Alunos> aluno = alunoRepository.findFirstBy();
        return aluno;
    }

    public Iterable<Alunos> findByIdAndNome(Long id, String nome) {
        Iterable<Alunos> aluno = alunoRepository.findByIdAndNomeContainingIgnoreCase(id, nome);
        return aluno;
    }

    public Iterable<Alunos> findByNomeOrSobrenome(String nome, String sobrenome){
        Iterable<Alunos> aluno = alunoRepository.findByNomeContainingIgnoreCaseOrSobrenomeContainingIgnoreCase(nome, sobrenome);
        return aluno;
    }


    ///POST
    @Transactional
    public Alunos save(AlunoDTO aluno) {
        enderecoServise.save(aluno.endereco());

        Alunos alunoNovo = new Alunos(aluno);

        return alunoRepository.save(alunoNovo);
    }


    public List<AlunoConsultaDTO> getAll() {
        List<AlunoConsultaDTO> alunos = VerAlunos(alunoRepository.findAll());
        return alunos;
    }

    ///PUT
    @Transactional
    public Alunos update(Long id, AlunoDTO alunoNovo) {
        Alunos alunosFound = findById(id);

        alunosFound.setNome(alunoNovo.nome() != null ? alunoNovo.nome() : alunosFound.getNome());
        alunosFound.setSobrenome(alunoNovo.sobrenome() != null ? alunoNovo.sobrenome() : alunosFound.getSobrenome());
        alunosFound.setDataNascimento(alunoNovo.dataNascimento() != null ?alunoNovo.dataNascimento() : alunosFound.getDataNascimento());
        return alunoRepository.save(alunosFound);
    }

    ///DELETE
    @Transactional
    public String delete(Long id) {
        alunoRepository.delete(findById(id));
        return "Aluno removido com sucesso!";
    }


    ///FAZEER UM GET ESPECIFICO DO ALUNO
    private List<AlunoConsultaDTO> VerAlunos (List<Alunos> aluno){
        List<AlunoConsultaDTO> alunosDTOs = new ArrayList<AlunoConsultaDTO>();

        for(Alunos alunoBusca : aluno){
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
            return alunosDTOs;
    }

    private List<ProfessorConsultaAluno> VerProfessor(List<Professores> professoresEntidade) {
        List<ProfessorConsultaAluno> professoresDTO = new ArrayList<ProfessorConsultaAluno>();

        for (Professores professores : professoresEntidade) {
            ProfessorConsultaAluno professorNovo = new ProfessorConsultaAluno(
                    professores.getId(),
                    professores.getNome(),
                    professores.getSobrenome(),
                    professores.getDataNascimento(),
                    professores.getSalario(),
                    professores.getCargo()
            );

            professoresDTO.add(professorNovo);
        }

        return professoresDTO;
    }
}
