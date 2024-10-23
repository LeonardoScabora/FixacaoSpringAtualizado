package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.DisciplinasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.DisciplinasDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplinas;
import estagioCEPEIN.FixacaoSpring.Models.entidades.DisciplinasID;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.DisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;


    public Disciplinas getById(DisciplinasID id) {
        return disciplinaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não Encontrado"));
    }

    public List<DisciplinasConsultaDTO> getAll() {
        List<DisciplinasConsultaDTO> disciplinas = VerDisciplinas(disciplinaRepository.findAll());
        return disciplinas;
    }

    @Transactional
    public Disciplinas save(DisciplinasDTO disciplina) {
        return disciplinaRepository.save(new Disciplinas(disciplina));
    }

    @Transactional
    public Disciplinas update(Long id, DisciplinasDTO disciplinaNova) {
        List<Disciplinas> acharDisci = disciplinaRepository.findAll();
        Disciplinas disciFound = null;

        for(Disciplinas buscar : acharDisci){
            if(id == buscar.getIds().getId()){///verificar se ID esta correto
                disciFound = buscar; ///atribui o objeto a outro objeto "teste"
            }
        }
        disciFound.setNome(disciplinaNova.nome() != null ? disciplinaNova.nome() : disciFound.getNome());
        disciFound.setSimbolo(disciplinaNova.simbolo() != null ? disciplinaNova.simbolo() : disciFound.getSimbolo());
        disciFound.setEstagio(disciplinaNova.estagio() != null ? disciplinaNova.estagio() : disciFound.getEstagio());

        return (disciplinaRepository.save(disciFound));
    }

    @Transactional
    public String delete(Long id) {
        List<Disciplinas> acharDisci = disciplinaRepository.findAll();

        for(Disciplinas buscar : acharDisci){
            if(id == buscar.getIds().getId()){///verificar se ID esta correto
                disciplinaRepository.delete(buscar); ///atribui o objeto a outro objeto "teste"
            }
        }
            return "Disciplina removida com sucesso";
    }

    public List<DisciplinasConsultaDTO> VerDisciplinas(List<Disciplinas> disciplinasEntidade) {
        List<DisciplinasConsultaDTO> disciDTO = new ArrayList<>();

        for(Disciplinas disciplina : disciplinasEntidade) {
            DisciplinasConsultaDTO disciNova = new DisciplinasConsultaDTO(
                    disciplina.getIds(),
                    disciplina.getNome(),
                    disciplina.getSimbolo(),
                    disciplina.getEstagio(),
                    disciplina.getDataRegistro()
            );
            disciDTO.add(disciNova);
        }
        return disciDTO;
    }
}
