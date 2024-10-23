package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.*;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Alunos;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplinas;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Professores;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Turmas;
import estagioCEPEIN.FixacaoSpring.Models.enumered.TipoCargoEnum;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public Professores findById(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não Encontrado"));
    }
    ///findFirstBy
    public Iterable<Professores> findFirstBy() {
        Iterable<Professores> professor = professorRepository.findFirstBy();
        return professor;
    }
    ///findByAnd
    public Iterable<Professores> findNomeECargo(String nome, TipoCargoEnum cargo) {
        Iterable<Professores> professor = professorRepository.findByNomeContainingIgnoreCaseAndCargo(nome,cargo);
        return professor;
    }
    ///findByOr
    public Iterable<Professores> findByNomeOuSobrenome(String nome, String sobrenome) {
        Iterable<Professores> professor = professorRepository.findByNomeContainingIgnoreCaseOrSobrenomeContainingIgnoreCase(nome, sobrenome);
        return professor;
    }
    ///findByOrderByDesc
    public List<Professores> findMaiorSalario(BigDecimal salario){
        return professorRepository.findBySalarioGreaterThanOrderBySalarioDesc(salario);
    }
    ///findByBetween
    public List<Professores> findFaixaSalario(BigDecimal salarioMin, BigDecimal salarioMax){
        return professorRepository.findBySalarioBetween(salarioMin, salarioMax);
    }

    ///findByOrderBy
    public List<Professores> findSalarioCrescente() {
        return professorRepository.findByOrderBySalario();
    }
    ///findByLessThan
    ///findByLessThanEqual
    ///findByGreaterThan
    ///findByGreaterThanEqual

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
        Professores professoresFound = findById(id);

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
                    professores.getSobrenome(),
                    professores.getDataNascimento(),
                    professores.getSalario(),
                    professores.getCargo(),
                    professores.getDataRegistro(),
                    VerTurmasAtualizado(professores.getTurmasList()),
                    VerDisciplinasAtualizada(professores.getDisciplinasList()),
                    VerAlunoAtualizado(professores.getAlunos())
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
                    turma.getSerie(),
                    turma.getDataRegistro());
            turmasDTO.add(turmaNova);
        }

        return turmasDTO;
    }

    private List<DisciplinasConsultaDTO> VerDisciplinasAtualizada(List<Disciplinas> disciplinas) {
        List<DisciplinasConsultaDTO> disciplinasDTO = new ArrayList<DisciplinasConsultaDTO>();

        for(Disciplinas disciplina : disciplinas) {
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
    private List<AlunoConsultaProfessor> VerAlunoAtualizado(List<Alunos> alunos) {
        List<AlunoConsultaProfessor> alunoDTOList = new ArrayList<AlunoConsultaProfessor>();

        for(Alunos aluno : alunos) {
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
