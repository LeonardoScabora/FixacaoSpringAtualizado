package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.professor.AlunoConsultaProfessor;
import estagioCEPEIN.FixacaoSpring.Models.dto.disciplina.DisciplinasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.professor.ProfessorConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.professor.ProfessorDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.turma.TurmasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Aluno;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplina;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professor;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Turma;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;
import estagioCEPEIN.FixacaoSpring.Models.exceptions.DataNotFoundException;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    ///findBy
    public ProfessorConsultaDTO findById(Long id) {
        if (professorRepository.existsById(id)) {
           return VerProfessor(professorRepository.findById(id).get());
        }
        throw new DataNotFoundException("Professor");
    }
    ///findFirstBy
    public List<ProfessorConsultaDTO> findFirstBy() {
        List<ProfessorConsultaDTO> professor = VerProfessorAtualizado(professorRepository.findFirstBy());
            return professor;
    }
    ///findByAnd
    public List<ProfessorConsultaDTO> findNomeECargo(String nome, TipoCargoEnum cargo) {
        List<ProfessorConsultaDTO> professor = VerProfessorAtualizado(professorRepository.findByNomeContainingIgnoreCaseAndCargo(nome,cargo));
        return professor;
    }
    ///findByOr
    public List<ProfessorConsultaDTO> findByNomeOuSobrenome(String nome, String sobrenome) {
        List<ProfessorConsultaDTO> professor = VerProfessorAtualizado(professorRepository.findByNomeContainingIgnoreCaseOrSobrenomeContainingIgnoreCase(nome, sobrenome));
        return professor;
    }
    ///findByOrderByDesc
    public List<ProfessorConsultaDTO> findMaiorSalario(BigDecimal salario){
        return VerProfessorAtualizado(professorRepository.findBySalarioGreaterThanOrderBySalarioDesc(salario));
    }
    ///findByBetween
    public List<ProfessorConsultaDTO> findFaixaSalario(BigDecimal salarioMin, BigDecimal salarioMax){
        return VerProfessorAtualizado(professorRepository.findBySalarioBetween(salarioMin, salarioMax));
    }
    ///findByOrderBy
    public List<ProfessorConsultaDTO> findSalarioCrescente() {
        return VerProfessorAtualizado(professorRepository.findByOrderBySalario());
    }
    ///findByLessThan
    public List<ProfessorConsultaDTO> findSalarioMenorQue(BigDecimal salarioMax){
        return VerProfessorAtualizado(professorRepository.findBySalarioLessThan(salarioMax));
    }
    ///findByLessThanEqual
    public List<ProfessorConsultaDTO> findSalarioMenorOuIgual(BigDecimal salarioMax){
        return VerProfessorAtualizado(professorRepository.findBySalarioLessThanEqual(salarioMax));
    }
    ///findByGreaterThan
    public List<ProfessorConsultaDTO> findSalarioMaiorQue(BigDecimal salarioMin){
        return VerProfessorAtualizado(professorRepository.findBySalarioGreaterThan(salarioMin));
    }
    ///findByGreaterThanEqual
    public List<ProfessorConsultaDTO> findSalarioMaiorOuIgual(BigDecimal salarioMin){
        return VerProfessorAtualizado(professorRepository.findBySalarioGreaterThanEqual(salarioMin));
    }

    public List<ProfessorConsultaDTO> getAll() {
        List<ProfessorConsultaDTO> professores = VerProfessorAtualizado(professorRepository.findAll());
        return professores;
    }

    ///POST
    @Transactional
    public Professor save(ProfessorDTO professor) {
        return professorRepository.save(new Professor(professor));}

    ///PUT
    @Transactional
    public ProfessorConsultaDTO update(Long id, ProfessorDTO professor) {
        if (professorRepository.existsById(id)) {
            List<Professor> professorFound = professorRepository.findAll();
            for (Professor prof : professorFound) {
                if (prof.getId().equals(id)) {
                        prof.setNome(professor.nome() != null ? professor.nome() : prof.getNome());
                        prof.setDataNascimento(professor.dataNascimento() != null ? professor.dataNascimento() : prof.getDataNascimento());
                        prof.setSalario(professor.salario() != null ? professor.salario() : prof.getSalario());
                        prof.setCargo(professor.cargo() != null ? professor.cargo() : prof.getCargo());
                    return VerProfessor(professorRepository.save(prof));
                }
            }
        }
            throw new DataNotFoundException("Professor");
    }

    ///DELETE
    @Transactional
    public String delete(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return "Profissional deletado com sucesso";
        }else{
            throw new DataNotFoundException("Professor");
        }
    }

    private ProfessorConsultaDTO VerProfessor(Professor professorEntidade) {
        ProfessorConsultaDTO professorNovo = new ProfessorConsultaDTO(
                professorEntidade.getId(),
                professorEntidade.getNome(),
                professorEntidade.getSobrenome(),
                professorEntidade.getDataNascimento(),
                professorEntidade.getSalario(),
                professorEntidade.getCargo(),
                professorEntidade.getDataRegistro(),
                VerTurmasAtualizado(professorEntidade.getTurmaList()),
                VerDisciplinasAtualizada(professorEntidade.getDisciplinaList()),
                VerAlunoAtualizado(professorEntidade.getAlunos())
        );
        if (professorNovo != null) {
            return professorNovo;
        }
        throw new DataNotFoundException("Professor");
    }

    private List<ProfessorConsultaDTO> VerProfessorAtualizado(List<Professor> professorEntidade) {
        List<ProfessorConsultaDTO> professoresDTO = new ArrayList<ProfessorConsultaDTO>();

        for (Professor professor : professorEntidade) {
            ProfessorConsultaDTO professorNovo = new ProfessorConsultaDTO(
                    professor.getId(),
                    professor.getNome(),
                    professor.getSobrenome(),
                    professor.getDataNascimento(),
                    professor.getSalario(),
                    professor.getCargo(),
                    professor.getDataRegistro(),
                    VerTurmasAtualizado(professor.getTurmaList()),
                    VerDisciplinasAtualizada(professor.getDisciplinaList()),
                    VerAlunoAtualizado(professor.getAlunos())
            );

            professoresDTO.add(professorNovo);
        }
        if (professoresDTO.isEmpty()) {
            throw new DataNotFoundException("Professor");
        } else {
            return professoresDTO;
        }
    }

    private List<TurmasConsultaDTO> VerTurmasAtualizado(List<Turma> turmas) {
        List<TurmasConsultaDTO> turmasDTO = new ArrayList<TurmasConsultaDTO>();

        for (Turma turma: turmas) {
            TurmasConsultaDTO turmaNova = new TurmasConsultaDTO(
                    turma.getId(),
                    turma.getSerie(),
                    turma.getDataRegistro());
            turmasDTO.add(turmaNova);
        }

        return turmasDTO;
    }

    private List<DisciplinasConsultaDTO> VerDisciplinasAtualizada(List<Disciplina> disciplinas) {
        List<DisciplinasConsultaDTO> disciplinasDTO = new ArrayList<DisciplinasConsultaDTO>();

        for(Disciplina disciplina : disciplinas) {
            DisciplinasConsultaDTO disciNova = new DisciplinasConsultaDTO(
                    disciplina.getIds(),
                    disciplina.getNome(),
                    disciplina.getSimbolo(),
                    disciplina.getEstagio(),
                    disciplina.getDataRegistro()
            );
            disciplinasDTO.add(disciNova);
        }
        return disciplinasDTO;
    }

    ///GET ESPECIFICO DO PROFESSOR DA TABELA ALUNO
    private List<AlunoConsultaProfessor> VerAlunoAtualizado(List<Aluno> alunos) {
        List<AlunoConsultaProfessor> alunoDTOList = new ArrayList<AlunoConsultaProfessor>();

        for(Aluno aluno : alunos) {
            AlunoConsultaProfessor alunoNovo = new AlunoConsultaProfessor(
                    aluno.getId(),
                    aluno.getNome(),
                    aluno.getSobrenome(),
                    aluno.getDataNascimento(),
                    aluno.getDataRegistro(),
                    aluno.getEndereco()
            );
            alunoDTOList.add(alunoNovo);
        }
        return alunoDTOList;
    }
}
