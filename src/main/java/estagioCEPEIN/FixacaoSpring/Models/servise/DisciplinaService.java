package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.DisciplinasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.DisciplinasDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplinas;
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

    public Disciplinas getById(Long id) {
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
        Disciplinas disciFound = getById(id);

        disciFound.setNome(disciplinaNova.nome() != null ? disciplinaNova.nome() : disciFound.getNome());

        return disciplinaRepository.save(new Disciplinas(disciplinaNova));
    }

    @Transactional
    public String delete(Long id) {
        disciplinaRepository.deleteById(id);
        return "Disciplina removida com sucesso";
    }

    public List<DisciplinasConsultaDTO> VerDisciplinas(List<Disciplinas> disciplinasEntidade) {
        List<DisciplinasConsultaDTO> disciDTO = new ArrayList<>();

        for(Disciplinas disciplina : disciplinasEntidade) {
            DisciplinasConsultaDTO disciNova = new DisciplinasConsultaDTO(
                    disciplina.getId(),
                    disciplina.getNome()
            );
            disciDTO.add(disciNova);
        }
        return disciDTO;
    }


}
