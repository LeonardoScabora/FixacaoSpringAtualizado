package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Aluno;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServise {

    @Autowired
    AlunoRepository alunoRepository;

    public Aluno getById(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não Encontrado"));
    }

    public List<Aluno> getAll() {
         List<Aluno> alunos = alunoRepository.findAll();
        return alunos;
    }


    ///POST
    @Transactional
    public Aluno save(AlunoDTO alunoNovo) {
        return alunoRepository.save(new Aluno (alunoNovo));
    }

    ///PUT
    @Transactional
    public Aluno update(Long id, AlunoDTO alunoNovo) {
        Aluno alunoFound = getById(id);

        alunoFound.setNome(alunoNovo.nome() != null ? alunoNovo.nome() : alunoFound.getNome());
        alunoFound.setDataNascimento(alunoNovo.dataNascimento() != null ?alunoNovo.dataNascimento() : alunoFound.getDataNascimento());

        return alunoRepository.save(alunoFound);
    }

    ///DELETE
    @Transactional
    public String delete(Long id) {
        alunoRepository.delete(getById(id));
        return "Aluno removido com sucesso!";
    }
}
