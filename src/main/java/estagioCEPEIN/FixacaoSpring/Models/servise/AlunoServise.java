package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.AlunoDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Alunos;
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
    @Autowired
    EnderecoService enderecoServise;


    ///POST
    @Transactional
    public Alunos save(AlunoDTO alunoNovo) {
        enderecoServise.save(alunoNovo.endereco());

       return alunoRepository.save(new Alunos(alunoNovo));
    }
    public Alunos getById(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não Encontrado"));
    }

    public List<Alunos> getAll() {
         List<Alunos> alunos = alunoRepository.findAll();
        return alunos;
    }

    ///PUT
    @Transactional
    public Alunos update(Long id, AlunoDTO alunoNovo) {
        Alunos alunosFound = getById(id);

        alunosFound.setNome(alunoNovo.nome() != null ? alunoNovo.nome() : alunosFound.getNome());
        alunosFound.setDataNascimento(alunoNovo.dataNascimento() != null ?alunoNovo.dataNascimento() : alunosFound.getDataNascimento());
//        alunosFound.setEndereco(alunoNovo.endereco().getBairro() != null ? alunoNovo.endereco().getBairro() : alunosFound.getEndereco().getBairro());
        ///FAZER update(PUT) PRA MODIFICAR O ENDERECO OU FAZER UM PUT NO EnderecoService

        return alunoRepository.save(alunosFound);
    }

    ///DELETE
    @Transactional
    public String delete(Long id) {
        alunoRepository.delete(getById(id));
        return "Aluno removido com sucesso!";
    }

}
